package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Passenger;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PassengersRepository implements PanacheRepositoryBase<Passenger, Integer> {

    // Get all passengers
    public List<Passenger> findAllPassengers() {
        return listAll();
    }


    // Get passengers by email
    public Passenger findPassengerByEmail(String email_address) {
        return find("email_address", email_address).firstResult();
    }

    // Add passenger in database
    public void addPassenger(Passenger passenger) {
        persist(passenger);
    }

    // delete passenger in database by email
    public void deletePassengerByEmail(String email_address) {
        delete(findPassengerByEmail(email_address));
    }

    // update passenger in database by email
    public void updatePassengerByEmail(String email_address, Passenger passenger) {
        Passenger passengerToUpdate = findPassengerByEmail(email_address);
        if (passenger.getFirstname() != null) {
            passengerToUpdate.setFirstname(passenger.getFirstname());
        }
        if (passenger.getSurname() != null) {
            passengerToUpdate.setSurname(passenger.getSurname());
        }
        if (passenger.getEmail_address() != null) {
            passengerToUpdate.setEmail_address(passenger.getEmail_address());
        }
        persist(passengerToUpdate);
    }

}
