package fr.unilasalle.flight.api.example;

import lombok.Getter;
import lombok.Setter;

public class Plane extends Vehicle{

    // attribute bay_volume
    @Getter
    @Setter
    private double bay_volume;

    // constructor
    public Plane(String name, double weight, double price, double speed, double bay_volume) {
        super(name, weight, price, speed);
        this.bay_volume = bay_volume;
    }

    // accelerate method
    public void accelerate() {
        System.out.println("Accelerating...");
    }

}
