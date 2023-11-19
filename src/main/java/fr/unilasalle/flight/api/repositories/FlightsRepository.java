package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Flight;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FlightsRepository implements PanacheRepositoryBase<Flight, Integer> {

    // Get all flights
    public List<Flight> findAllFlights() {
        return listAll();
    }

    // Get flight by number
    public Flight findFlightByNumber(String number) {
        return find("number", number).firstResult();
    }

    // Get flights by destination
    public List<Flight> findFlightsByDestination(String destination) {

        return find("destination", destination).list();
    }

    // Add flight in database
    public void addFlight(Flight flight) {
        persist(flight);
    }

    // delete flight in database by number
    public void deleteFlightByNumber(String number) {
        // delete associated reservations
        ReservationsRepository reservationsRepository = new ReservationsRepository();
        reservationsRepository.deleteReservationByFlightId(findFlightByNumber(number).getId());
        delete(findFlightByNumber(number));
    }

}
