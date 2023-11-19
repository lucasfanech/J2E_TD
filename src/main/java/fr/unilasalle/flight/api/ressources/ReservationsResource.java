package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Reservation;
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
        reservationsRepository.addReservation(reservation);
        return Response.ok(reservation).status(201).build();
    }

    @DELETE
    @Path("/id/{id}")
    @Transactional
    public Response deleteReservationById(@PathParam("id") Integer id) {
        reservationsRepository.deleteReservationById(id);
        return Response.ok().status(204).build();
    }


}
