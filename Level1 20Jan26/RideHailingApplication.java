import java.util.*;

abstract class Vehicle {
    private final String vehicleId;
    private final String driverName;
    private final double ratePerKm;
    private String currentLocation;

    public Vehicle(String vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.currentLocation = "Unknown";
    }
    public String getVehicleId() { return vehicleId; }
    public String getDriverName() { return driverName; }
    public double getRatePerKm() { return ratePerKm; }
    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String location) { this.currentLocation = location; }
    public abstract double calculateFare(double distance);
    public String getVehicleDetails() {
        return "Vehicle ID: " + vehicleId + ", Driver: " + driverName + ", Rate/km: " + ratePerKm;
    }
}

interface GPS {
    String getCurrentLocation();
    void updateLocation(String location);
}

class Car extends Vehicle implements GPS {
    public Car(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }
    @Override
    public double calculateFare(double distance) { return getRatePerKm() * distance; }
    @Override
    public String getCurrentLocation() { return super.getCurrentLocation(); }
    @Override
    public void updateLocation(String location) { setCurrentLocation(location); }
}

class Bike extends Vehicle implements GPS {
    public Bike(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }
    @Override
    public double calculateFare(double distance) { return getRatePerKm() * distance * 0.8; }
    @Override
    public String getCurrentLocation() { return super.getCurrentLocation(); }
    @Override
    public void updateLocation(String location) { setCurrentLocation(location); }
}

class Auto extends Vehicle implements GPS {
    public Auto(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }
    @Override
    public double calculateFare(double distance) { return getRatePerKm() * distance * 0.9; }
    @Override
    public String getCurrentLocation() { return super.getCurrentLocation(); }
    @Override
    public void updateLocation(String location) { setCurrentLocation(location); }
}

public class RideHailingApplication {
    public static void main(String[] args) {
        List<Vehicle> rides = new ArrayList<>();
        Car car = new Car("CAR001", "Alice", 15);
        Bike bike = new Bike("BIKE002", "Bob", 10);
        Auto auto = new Auto("AUTO003", "Charlie", 12);
        car.updateLocation("Downtown");
        bike.updateLocation("Uptown");
        auto.updateLocation("Station");
        rides.add(car);
        rides.add(bike);
        rides.add(auto);
        for (Vehicle v : rides) {
            System.out.println(v.getVehicleDetails());
            System.out.println("Current Location: " + v.getCurrentLocation());
            System.out.println("Fare for 10km: " + v.calculateFare(10));
            System.out.println();
        }
    }
}
