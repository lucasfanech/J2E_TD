package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Planes;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlanesRepository implements PanacheRepositoryBase<Planes, Integer> {

    // Get all planes
    public List<Planes> findAllPlanes() {
        return listAll();
    }

    // Get planes by operator
    public List<Planes> findPlanesByOperator(String operator) {
        return find("operator", operator).list();
    }

    // Get planes by registration
    public Planes findPlaneByRegistration(String registration) {
        return find("registration", registration).firstResult();
    }

    // Add plane in database
    public void addPlane(Planes plane) {
        persist(plane);
    }

}
