package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.repositories.PassengersRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/passengers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PassengersResource {

    @Inject
    PassengersRepository passengersRepository;

    @GET
    public List<Passenger> getAllPassengers() {
        return passengersRepository.findAllPassengers();
    }


    @GET
    @Path("/email_address/{email_address}")
    public Passenger getPassengerByEmail(@PathParam("email_address") String email_address) {
        return passengersRepository.findPassengerByEmail(email_address);
    }

    @POST
    @Transactional
    public Response addPassenger(Passenger passenger) {
        passengersRepository.addPassenger(passenger);
        return Response.ok(passenger).status(201).build();
    }

    @DELETE
    @Path("/email_address/{email_address}")
    @Transactional
    public Response deletePassengerByEmail(@PathParam("email_address") String email_address) {
        passengersRepository.deletePassengerByEmail(email_address);
        return Response.ok().status(204).build();
    }

    @PUT
    @Path("/email_address/{email_address}")
    @Transactional
    public Response updatePassengerByEmail(@PathParam("email_address") String email_address, Passenger passenger) {
        passengersRepository.updatePassengerByEmail(email_address, passenger);
        // try catch Return Response with status 200 and updated passenger
        return Response.ok(passenger).status(200).build();
    }

}
