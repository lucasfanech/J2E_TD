package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.beans.Reservation;
import fr.unilasalle.flight.api.repositories.PassengersRepository;
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
        if (reservation.getPassenger() != null) {
            // Case 1: Passenger already exists
            reservationsRepository.addReservation(reservation);
        } else {
            // Case 2: Create a new passenger
            // Check if all required fields are present
            if (reservation.getPassenger() != null
                    && reservation.getPassenger().getSurname() != null
                    && reservation.getPassenger().getFirstname() != null
                    && reservation.getPassenger().getEmail_address() != null) {
                Passenger newPassenger = reservation.getPassenger();
                PassengersRepository passengersRepository = new PassengersRepository();
                passengersRepository.addPassenger(newPassenger);

                // Add the new passenger to the reservation and persist it
                reservation.setPassenger(newPassenger);
                reservationsRepository.addReservation(reservation);

                return Response.ok(reservation).status(201).build();
            } else {
                // Return an error if a required field is missing
                return Response.status(400).entity("Les champs nécessaires pour créer un nouveau passager sont manquants.").build();
            }
        }

        return Response.ok(reservation).status(201).build();
    }


    @DELETE
    @Path("/flight_id/{flight_id}")
    @Transactional
    public Response deleteReservationByFlightId(@PathParam("flight_id") Integer flight_id) {
        reservationsRepository.deleteReservationByFlightId(flight_id);
        return Response.ok().status(204).build();
    }


}
