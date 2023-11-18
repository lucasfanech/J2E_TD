package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Flight;
import fr.unilasalle.flight.api.repositories.FlightsRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightsResource {

    @Inject
    FlightsRepository flightsRepository;

    @GET
    public List<Flight> getAllFlights() {
        return flightsRepository.findAllFlights();
    }

    @GET
    @Path("/destination/{destination}")
    public List<Flight> getFlightsByDestination(@PathParam("destination") String destination) {
        return flightsRepository.findFlightsByDestination(destination);
    }

    @GET
    @Path("/number/{number}")
    public Flight getFlightByNumber(@PathParam("number") String number) {
        return flightsRepository.findFlightByNumber(number);
    }

    @POST
    @Transactional
    public Response addFlight(Flight plane) {
        flightsRepository.addFlight(plane);
        return Response.ok(plane).status(201).build();
    }

    @DELETE
    @Path("/number/{number}")
    @Transactional
    public Response deleteFlightByNumber(@PathParam("number") String number) {
        flightsRepository.deleteFlightByNumber(number);
        return Response.ok().status(204).build();
    }

}
