package fr.unilasalle.flight.api.example;

import lombok.Getter;
import lombok.Setter;

public class Vehicle {
    // attributes name, weight, price, speed
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private double weight;

    @Getter
    @Setter
    private double price;
    @Getter
    @Setter
    private double speed;

    // constructor
    public Vehicle(String name, double weight, double price, double speed) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.speed = speed;
    }

    // accelerate method
    public void accelerate() {
        System.out.println("Accelerating...");
    }

}
