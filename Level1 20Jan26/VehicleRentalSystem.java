import java.util.*;

abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getType() { return type; }
    public double getRentalRate() { return rentalRate; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public void setType(String type) { this.type = type; }
    public void setRentalRate(double rentalRate) { this.rentalRate = rentalRate; }
    public abstract double calculateRentalCost(int days);
}

interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

class Car extends Vehicle implements Insurable {
    private final String insurancePolicyNumber;
    public Car(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Car", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }
    @Override
    public double calculateRentalCost(int days) { return getRentalRate() * days; }
    @Override
    public double calculateInsurance() { return 200; }
    @Override
    public String getInsuranceDetails() { return "Car Insurance Policy: " + insurancePolicyNumber; }
}

class Bike extends Vehicle implements Insurable {
    private final String insurancePolicyNumber;
    public Bike(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Bike", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }
    @Override
    public double calculateRentalCost(int days) { return getRentalRate() * days * 0.8; }
    @Override
    public double calculateInsurance() { return 50; }
    @Override
    public String getInsuranceDetails() { return "Bike Insurance Policy: " + insurancePolicyNumber; }
}

class Truck extends Vehicle implements Insurable {
    private final String insurancePolicyNumber;
    public Truck(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Truck", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }
    @Override
    public double calculateRentalCost(int days) { return getRentalRate() * days * 1.5; }
    @Override
    public double calculateInsurance() { return 500; }
    @Override
    public String getInsuranceDetails() { return "Truck Insurance Policy: " + insurancePolicyNumber; }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car("CAR123", 100, "C-INS-001"));
        vehicles.add(new Bike("BIKE456", 40, "B-INS-002"));
        vehicles.add(new Truck("TRUCK789", 200, "T-INS-003"));
        for (Vehicle v : vehicles) {
            System.out.println(v.getType() + " " + v.getVehicleNumber());
            System.out.println("Rental for 3 days: " + v.calculateRentalCost(3));
            if (v instanceof Insurable insurable) {
                System.out.println(insurable.getInsuranceDetails());
                System.out.println("Insurance Cost: " + insurable.calculateInsurance());
            }
            System.out.println();
        }
    }
}
