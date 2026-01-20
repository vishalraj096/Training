interface Refuelable {
    void refuel();
}

class Vehicle {
    String model;

    Vehicle(int maxSpeed, String model) {
        this.model = model;
    }
}

class ElectricVehicle extends Vehicle {
    ElectricVehicle(int maxSpeed, String model) {
        super(maxSpeed, model);
    }

    void charge() {
        System.out.println(model + " is charging.");
    }
}

class PetrolVehicle extends Vehicle implements Refuelable {
    PetrolVehicle(int maxSpeed, String model) {
        super(maxSpeed, model);
    }

    @Override
    public void refuel() {
        System.out.println(model + " is refueling.");
    }
}

public class VehicleManagementHybrid {
    public static void main(String[] args) {
        ElectricVehicle ev = new ElectricVehicle(160, "Tesla Model 3");
        PetrolVehicle pv = new PetrolVehicle(180, "Toyota Corolla");
        ev.charge();
        pv.refuel();
    }
}
