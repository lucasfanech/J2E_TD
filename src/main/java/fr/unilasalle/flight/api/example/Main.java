package fr.unilasalle.flight.api.example;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("Vehicle", 1000, 10000, 100);
        Car car = new Car("Car", 1000, 10000, 100, 5);
        Plane plane = new Plane("Plane", 1000, 10000, 100, 1000);

        System.out.println(vehicle.getName());
        System.out.println(car.getName());
        System.out.println(plane.getName());

        vehicle.accelerate();
        car.accelerate();
        plane.accelerate();

        car.start();
    }
}
