package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Plane;
import fr.unilasalle.flight.api.beans.Reservation;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ReservationsRepository implements PanacheRepositoryBase<Reservation, Integer> {

    // Get all reservations
    public List<Reservation> findAllReservations() {
        return listAll();
    }

    // Get reservation by id
    public Reservation findReservationById(Integer id) {
        return find("id", id).firstResult();
    }

    // get reservations by flight_id
    public List<Reservation> findReservationsByFlightId(Integer flight_id) {
        return find("flight_id", flight_id).list();
    }

    // Add reservation in database
    public void addReservation(Reservation reservation) {
        persist(reservation);
    }

    // delete reservation in database by id
    public void deleteReservationById(Integer id) {
        delete(findReservationById(id));
    }

}
