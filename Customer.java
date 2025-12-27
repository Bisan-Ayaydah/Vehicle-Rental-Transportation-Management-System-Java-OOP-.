
package assignment_2;
import java.util.Arrays;
public class Customer {
    private String name;
    private int id;
    private String licenseNumber;
    private int numberOfCurrentRented;
    private Vehicle[] vehiclesRented;

    public Customer() {
        this.name = "Unknown";
        this.id = 0;
        this.licenseNumber = "Unknown";
        this.numberOfCurrentRented = 0;
        this.vehiclesRented = new Vehicle[5];
    }

    public Customer(String name, int id, String licenseNumber, int maxVehicles) {
        this.name = name;
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.vehiclesRented = new Vehicle[maxVehicles];
    }

    public void rentVehicle(Vehicle vehicle, int days) {

        vehicle.setRentDays(days);
        vehiclesRented[numberOfCurrentRented] = vehicle;
        numberOfCurrentRented ++ ;
    }

    public void returnVehicle(Vehicle vehicle) {
        for (int i = 0; i < vehiclesRented.length; i++) {
            if (vehiclesRented[i] != null && vehiclesRented[i].getRegistrationNumber().equals(vehicle.getRegistrationNumber())) {
                vehiclesRented[i] = null;
                numberOfCurrentRented--;
                vehicle.setAvailable(true); 
                System.out.println("Vehicle returned successfully.");
                return;
            }
        }
        System.out.println("Vehicle not found in customer's rented vehicles.");
    }

    public double calculateRent() {
        double totalRent = 0;
        for (Vehicle vehicle : vehiclesRented) {
            if (vehicle != null) {
                totalRent += vehicle.getRentalRatePerDay() * vehicle.getRentDays();
            }
        }
        return totalRent;
    }

    public double calculateRent(String type) {
        double totalRent = 0;
        for (Vehicle vehicle : vehiclesRented) {
            if (vehicle != null && vehicle.getType().equalsIgnoreCase(type)) {
                totalRent += vehicle.getRentalRatePerDay() * vehicle.getRentDays();
            }
        }
        return totalRent;
    }

    public int countVehiclesByType(String type) {
        int count = 0;
        for (Vehicle vehicle : vehiclesRented) {
            if (vehicle != null && vehicle.getType().equalsIgnoreCase(type)) {
                count++;
            }
        }
        return count;
    }

    public void printInfo() {
        System.out.println("Customer Information:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("License Number: " + licenseNumber);
        System.out.println("Number of Current Rented Vehicles: " + numberOfCurrentRented);
        System.out.println("Vehicles Rented: ");
        for (Vehicle vehicle : vehiclesRented) {
            if (vehicle != null) {
               vehicle.printInfo();
            }
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getNumberOfCurrentRented() {
        return numberOfCurrentRented;
    }

    public void setNumberOfCurrentRented(int numberOfCurrentRented) {
        this.numberOfCurrentRented = numberOfCurrentRented;
    }

    public Vehicle[] getVehiclesRented() {
        return vehiclesRented;
    }

    public void setVehiclesRented(Vehicle[] vehiclesRented) {
        this.vehiclesRented = vehiclesRented;
    }


}
