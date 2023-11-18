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
    public Plane getPlaneByRegistration(@PathParam("registration") String registration) {
        return planesRepository.findPlaneByRegistration(registration);
    }

    @POST
    @Transactional
    public Response addPlane(Plane plane) {
        planesRepository.addPlane(plane);
        return Response.ok(plane).status(201).build();
    }

}
