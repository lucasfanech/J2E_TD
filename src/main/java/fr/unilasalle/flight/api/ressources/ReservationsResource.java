package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Flight;
import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.beans.Plane;
import fr.unilasalle.flight.api.beans.Reservation;
import fr.unilasalle.flight.api.repositories.FlightsRepository;
import fr.unilasalle.flight.api.repositories.PassengersRepository;
import fr.unilasalle.flight.api.repositories.PlanesRepository;
import fr.unilasalle.flight.api.repositories.ReservationsRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationsResource {

    @Inject
    ReservationsRepository reservationsRepository;

    @Inject
    FlightsRepository flightsRepository;

    @Inject
    PlanesRepository planesRepository;

    @Inject
    PassengersRepository passengersRepository;

    @GET
    public List<Reservation> getAllReservations() {
        return reservationsRepository.findAllReservations();
    }

    @GET
    @Path("/id/{id}")
    public Reservation getReservationById(@PathParam("id") Integer id) {
        return reservationsRepository.findReservationById(id);
    }

    @GET
    @Path("/flight_id/{flight_id}")
    public List<Reservation> getReservationsByFlightId(@PathParam("flight_id") Integer flight_id) {
        return reservationsRepository.findReservationsByFlightId(flight_id);
    }

    @POST
    @Transactional
    public Response addReservation(Reservation reservation) {
        if (reservation.getFlight_id() != null) {
            Flight flight = flightsRepository.findFlightById(reservation.getFlight_id());

            if (flight == null) {
                return Response.status(400).entity("Le vol n'existe pas.").build();
            } else {
                Plane plane = planesRepository.findPlaneById(flight.getPlane_id());
                if (plane.getCapacity() <= reservationsRepository.findReservationsByFlightId(reservation.getFlight_id()).size()) {
                    return Response.status(400).entity("Le vol est complet.").build();
                }
            }
        } else {
            return Response.status(400).entity("Le champ flight_id est manquant.").build();
        }

        if (reservation.getPassenger() != null) {
            Passenger existingPassenger = passengersRepository.findPassengerByEmail(reservation.getPassenger().getEmail_address());
            if (existingPassenger != null) {
                reservation.setPassenger(existingPassenger);
            } else {
                if (validatePassenger(reservation.getPassenger())) {
                    passengersRepository.addPassenger(reservation.getPassenger());
                } else {
                    return Response.status(400).entity("Les champs nécessaires pour créer un nouveau passager sont manquants.").build();
                }
            }
        } else {
            return Response.status(400).entity("Le champ passenger est manquant.").build();
        }

        // Check if reservation already exists by flight_id and passenger_email_address
        Reservation checkReservation = reservationsRepository.findReservationsByFlightId(reservation.getFlight_id()).stream().filter(reservation1 -> reservation1.getPassenger().getEmail_address().equals(reservation.getPassenger().getEmail_address())).findFirst().orElse(null);
        if (checkReservation != null) {
            return Response.status(400).entity("La réservation existe déjà.").build();
        }
        reservationsRepository.addReservation(reservation);
        return Response.ok(reservation).status(201).build();
    }

    private boolean validatePassenger(Passenger passenger) {
        return passenger.getSurname() != null && passenger.getFirstname() != null && passenger.getEmail_address() != null;
    }

    @DELETE
    @Path("/flight_id/{flight_id}")
    @Transactional
    public Response deleteReservationByFlightId(@PathParam("flight_id") Integer flight_id) {
        // if no reservation found, return 400 error Response
        if (reservationsRepository.findReservationsByFlightId(flight_id) == null || reservationsRepository.findReservationsByFlightId(flight_id).isEmpty()) {
            return Response.status(400).entity("No reservation found with flight id " + flight_id).build();
        }
        reservationsRepository.deleteReservationByFlightId(flight_id);
        return Response.ok().status(204).build();
    }
}
