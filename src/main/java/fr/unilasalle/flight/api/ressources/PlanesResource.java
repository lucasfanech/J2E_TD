package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Plane;
import fr.unilasalle.flight.api.repositories.PlanesRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/planes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanesResource {

    @Inject
    PlanesRepository planesRepository;

    @GET
    public List<Plane> getAllPlanes() {
        return planesRepository.findAllPlanes();
    }

    @GET
    @Path("/operator/{operator}")
    public List<Plane> getPlanesByOperator(@PathParam("operator") String operator) {
        return planesRepository.findPlanesByOperator(operator);
    }

    @GET
    @Path("/registration/{registration}")
    public Response getPlaneByRegistration(@PathParam("registration") String registration) {
        Plane plane = planesRepository.findPlaneByRegistration(registration);
        // if no plane found, return 400 error
        if (plane == null) {
            return Response.status(400).entity("No plane found with registration " + registration).build();
        }
        return Response.ok(plane).status(200).build();
    }

    @POST
    @Transactional
    public Response addPlane(Plane plane) {
        // Check if all fields are not null and not empty
        if (plane.getRegistration() == null || plane.getRegistration().isEmpty()) {
            return Response.status(400).entity("Registration field is empty").build();
        }
        if (plane.getOperator() == null || plane.getOperator().isEmpty()) {
            return Response.status(400).entity("Operator field is empty").build();
        }
        if (plane.getCapacity() == null) {
            return Response.status(400).entity("Capacity field is empty").build();
        }
        if (plane.getCapacity() <= 0) {
            return Response.status(400).entity("Capacity field is not valid").build();
        }
        // Check if plane already exists
        if (planesRepository.findPlaneByRegistration(plane.getRegistration()) != null) {
            return Response.status(400).entity("Plane already exists with registration " + plane.getRegistration()).build();
        }
        planesRepository.addPlane(plane);
        return Response.ok(plane).status(201).build();
    }

}
