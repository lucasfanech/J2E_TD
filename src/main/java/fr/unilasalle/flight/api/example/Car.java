package fr.unilasalle.flight.api.example;

import lombok.Getter;
import lombok.Setter;

public class Car extends Vehicle{

    // attribute number_seats
    @Getter
    @Setter
    private double number_seats;

    // constructor

    public Car(String name, double weight, double price, double speed, double number_seats) {
        super(name, weight, price, speed);
        this.number_seats = number_seats;
    }

    // start method
    public void start() {
        System.out.println("Starting...");
    }


}
