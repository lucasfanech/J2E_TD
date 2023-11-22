package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Flight;
import fr.unilasalle.flight.api.repositories.FlightsRepository;
import fr.unilasalle.flight.api.repositories.PlanesRepository;
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

    @Inject
    PlanesRepository planesRepository;

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
    public Response getFlightByNumber(@PathParam("number") String number) {
        Flight flight = flightsRepository.findFlightByNumber(number);
        // if no flight found, return 400 error
        if (flight == null) {
            return Response.status(400).entity("No flight found with number " + number).build();
        }
        return Response.ok(flight).status(200).build();
    }

    @POST
    @Transactional
    public Response addFlight(Flight flight) {
        // Check if all fields are not null and not empty
        if (flight.getNumber() == null || flight.getNumber().isEmpty()) {
            return Response.status(400).entity("Number field is empty").build();
        }
        if (flight.getOrigin() == null || flight.getOrigin().isEmpty()) {
            return Response.status(400).entity("Origin field is empty").build();
        }
        if (flight.getDestination() == null || flight.getDestination().isEmpty()) {
            return Response.status(400).entity("Destination field is empty").build();
        }
        if (flight.getDeparture_date() == null) {
            return Response.status(400).entity("Departure_date field is empty").build();
        }
        if (flight.getArrival_date() == null) {
            return Response.status(400).entity("Arrival_date field is empty").build();
        }
        if (flight.getDeparture_time() == null) {
            return Response.status(400).entity("Departure_time field is empty").build();
        }
        if (flight.getArrival_time() == null) {
            return Response.status(400).entity("Arrival_time field is empty").build();
        }
        if (flight.getPlane_id() == null) {
            return Response.status(400).entity("Plane_id field is empty").build();
        }

        // check if flight exists
        if (flightsRepository.findFlightByNumber(flight.getNumber()) != null) {
            return Response.status(400).entity("Flight already exists with number " + flight.getNumber()).build();
        }

        // check if plane_id exists in planes table
        if (planesRepository.findPlaneById(flight.getPlane_id()) == null) {
            return Response.status(400).entity("Plane with id " + flight.getPlane_id() + " does not exist").build();
        }

        flightsRepository.addFlight(flight);
        return Response.ok(flight).status(201).build();
    }

    @DELETE
    @Path("/number/{number}")
    @Transactional
    public Response deleteFlightByNumber(@PathParam("number") String number) {
        // check if flight exists
        if (flightsRepository.findFlightByNumber(number) == null) {
            return Response.status(400).entity("Flight with number " + number + " does not exist").build();
        }
        flightsRepository.deleteFlightByNumber(number);
        return Response.ok().status(204).build();
    }

}
