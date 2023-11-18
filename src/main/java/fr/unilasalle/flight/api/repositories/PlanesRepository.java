package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Plane;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlanesRepository implements PanacheRepositoryBase<Plane, Integer> {

    // Get all planes
    public List<Plane> findAllPlanes() {
        return listAll();
    }

    // Get planes by operator
    public List<Plane> findPlanesByOperator(String operator) {
        return find("operator", operator).list();
    }

    // Get planes by registration
    public Plane findPlaneByRegistration(String registration) {
        return find("registration", registration).firstResult();
    }

    // Add plane in database
    public void addPlane(Plane plane) {
        persist(plane);
    }

}
