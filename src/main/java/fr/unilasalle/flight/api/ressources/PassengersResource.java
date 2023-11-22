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
    public Response getPassengerByEmail(@PathParam("email_address") String email_address) {
        // if no passenger found, return 400 error Response
        if (passengersRepository.findPassengerByEmail(email_address) == null) {
            return Response.status(400).entity("No passenger found with email address " + email_address).build();
        }
        Passenger passenger = passengersRepository.findPassengerByEmail(email_address);
        return Response.ok(passenger).status(200).build();
    }

    @POST
    @Transactional
    public Response addPassenger(Passenger passenger) {
        // Check if all fields are not null and not empty
        if (passenger.getEmail_address() == null || passenger.getEmail_address().isEmpty()) {
            return Response.status(400).entity("Email address field is empty").build();
        }
        if (passenger.getFirstname() == null || passenger.getFirstname().isEmpty()) {
            return Response.status(400).entity("First name field is empty").build();
        }
        if (passenger.getEmail_address() == null || passenger.getEmail_address().isEmpty()) {
            return Response.status(400).entity("Email_address field is empty").build();
        }

        // Check if passenger already exists by email address
        if (passengersRepository.findPassengerByEmail(passenger.getEmail_address()) != null) {
            return Response.status(400).entity("Passenger already exists with email address " + passenger.getEmail_address()).build();
        }
        passengersRepository.addPassenger(passenger);
        return Response.ok(passenger).status(201).build();
    }

    @DELETE
    @Path("/email_address/{email_address}")
    @Transactional
    public Response deletePassengerByEmail(@PathParam("email_address") String email_address) {
        // if no passenger found, return 400 error Response
        if (passengersRepository.findPassengerByEmail(email_address) == null) {
            return Response.status(400).entity("No passenger found with email address " + email_address).build();
        }
        passengersRepository.deletePassengerByEmail(email_address);
        return Response.ok().status(204).build();
    }

    @PUT
    @Path("/email_address/{email_address}")
    @Transactional
    public Response updatePassengerByEmail(@PathParam("email_address") String email_address, Passenger passenger) {
        // if no passenger found, return 400 error Response
        if (passengersRepository.findPassengerByEmail(email_address) == null) {
            return Response.status(400).entity("No passenger found with email address " + email_address).build();
        }
        passengersRepository.updatePassengerByEmail(email_address, passenger);
        return Response.ok(passenger).status(200).build();
    }

}
