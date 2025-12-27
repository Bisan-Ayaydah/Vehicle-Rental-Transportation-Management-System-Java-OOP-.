

package assignment_2;
import java.util.*;
public class Driver {
    
    public static void displayAvailableVehicles(Vehicle[] vehicles) {
        System.out.println("\n");

        System.out.println("Available Vehicles for Rent:");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-10s\n", "Type", "Registration", "Brand", "Rate/Day");
        System.out.println("-----------------------------------------------------");

        boolean found = false;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                System.out.printf("%-10s %-15s %-15s $%-10.2f\n", vehicle.getType(), vehicle.getRegistrationNumber(),
                        vehicle.getBrand(), vehicle.getRentalRatePerDay());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles available for rent.");
        }
    }

    public static boolean numberOfCurrentRented(int maxNum, int rentNum) {
        return rentNum < maxNum;
    }

   
    public static Customer findCustomerById(Customer[] customers, int customerId) {
    	// Make sure the customers array is not null
        if (customers != null && customers.length > 0) {  
            for (Customer customer : customers) {
                if (customer.getId() == customerId) {
                    return customer;
                }
            }
        }
        return null;
    }

    public static Vehicle findVehicleByRegistrationNumber(Vehicle[] vehicles, String regNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegistrationNumber().equals(regNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public static void displayVehiclesByPrice(Vehicle[] vehicles) {
        int n = vehicles.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (vehicles[j].getRentalRatePerDay() > vehicles[j + 1].getRentalRatePerDay()) {
                    Vehicle temp = vehicles[j];
                    vehicles[j] = vehicles[j + 1];
                    vehicles[j + 1] = temp;
                }
            }
        }

        for (Vehicle vehicle : vehicles) {
        	if (vehicle.isAvailable())
        	{
        	System.out.printf("%-10s %-15s %-15s $%-10.2f\n",
                    vehicle.getType(),
                    vehicle.getRegistrationNumber(),
                    vehicle.getBrand(),
                    vehicle.getRentalRatePerDay());
        }
    }
    }

    public static void displayVehiclesByType(Vehicle[] vehicles) {
        int n = vehicles.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (vehicles[j].getType().compareTo(vehicles[j + 1].getType()) > 0) {
                    Vehicle temp = vehicles[j];
                    vehicles[j] = vehicles[j + 1];
                    vehicles[j + 1] = temp;
                }
            }
        }

        for (Vehicle vehicle : vehicles) {
        	if (vehicle.isAvailable())
        	{
        	System.out.printf("%-10s %-15s %-15s $%-10.2f\n",
                    vehicle.getType(),
                    vehicle.getRegistrationNumber(),
                    vehicle.getBrand(),
                    vehicle.getRentalRatePerDay());
        }}
    }

    public static void main(String[] args) {
        Vehicle[] availableVehicles = {
                new Vehicle("Car", "ABC123", "Toyota", 50.0, true),
                new Vehicle("Bike", "DEF456", "Honda", 20.0, true),
                new Vehicle("Truck", "GHI789", "Ford", 80.0, true),
                new Vehicle("Car", "JKL012", "Hyundai", 55.0, true),
                new Vehicle("Bike", "MNO345", "Yamaha", 160.0, true)
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter The Number Of Customers: ");
        int n = sc.nextInt();
        Customer[] customers = new Customer[n];

            for (int i = 0; i < n ;++i){
            System.out.printf("Enter details for customer %d:\n", i + 1);
            sc.nextLine();
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            System.out.print("\nEnter license number: ");
            String licenseNumber = sc.next();
            System.out.print("\nEnter maximum number of vehicles can rent by this customer: ");
            int maxNum = sc.nextInt();
            System.out.print("\nEnter the number of vehicles to rent now: ");
            int rentNum = sc.nextInt();

            customers[i] = new Customer(name, id, licenseNumber, maxNum);

            //Initialize the numberOfCurrentRented field to Zero for each customer.
            customers[i].setNumberOfCurrentRented(0);

            displayAvailableVehicles(availableVehicles);

           
            int vehiclesRentedCount=0;
            while (vehiclesRentedCount < rentNum && numberOfCurrentRented(maxNum, customers[i].getNumberOfCurrentRented()))
            { 
            	System.out.print("Enter a vehicle's registration number to rent or type 'exit' to stop renting: ");
            	String input = sc.next();
            	if ("exit".equals(input))
            	{ 
            		System.out.println("Rental process ended by customer."); 
            		break;
            		} 
            	boolean found = false;
            	for (int j = 0; j < availableVehicles.length; j++) 
            	{ 
            		Vehicle vehicle = availableVehicles[j];
            		if (vehicle.getRegistrationNumber().equals(input)) 
            		{ 
            			found = true; if (!vehicle.isAvailable())
            			{
            				System.out.println("Vehicle not available, please choose another.");
            			} 
            			else 
            			{ 
            				System.out.print("Enter the number of days you want to rent the vehicle: ");
            				int days = sc.nextInt();
            				vehicle.setAvailable(false);
            				customers[i].rentVehicle(vehicle, days);
            				vehiclesRentedCount++;
            				System.out.printf("Vehicle %s rented successfully.\n", vehicle.getRegistrationNumber()); 
            				} break; } 
            		} 
            	if (!found) { System.out.println("Vehicle not found, please try again.");
            	}
            	} if (vehiclesRentedCount == rentNum)
            	{
            		//System.out.println("You have reached your rental limit for this session.");
            		}
        }

            while (true) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Print Customer Information");
                System.out.println("2. Display Total Rental Cost for a Customer");
                System.out.println("3. Count Rented Vehicles by Type");
                System.out.println("4. Rent a New Vehicle");
                System.out.println("5. Return a Vehicle");
                System.out.println("6. Display All Available Vehicles in Ascending Order of Price");
                System.out.println("7. Display All Available Vehicles in Alphabetical Order of Type");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter Customer ID: ");
                        int customerId = sc.nextInt();
                        Customer foundCustomer = findCustomerById(customers, customerId);
                        if (foundCustomer != null) {
                            foundCustomer.printInfo();
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;
                   
                    case 2:
                    	System.out.print("Enter Customer ID: ");
                    	 customerId = sc.nextInt();
                    	foundCustomer = findCustomerById(customers, customerId);
                    	if (foundCustomer != null) 
                    	{ 
                    		double totalCost = foundCustomer.calculateRent();
                    		System.out.printf("Total rental cost for customer %d is: $%.2f\n", customerId, totalCost);
                    		} 
                    	else { 
                    		System.out.println("Customer not found.");
                    		} 
                    	break;
                    case 3:
                        System.out.print("Enter Customer ID: ");
                        customerId = sc.nextInt();
                        foundCustomer = findCustomerById(customers, customerId);
                        if (foundCustomer != null) {
                            System.out.print("Enter vehicle type: ");
                            String type = sc.next();
                            int count = foundCustomer.countVehiclesByType(type);
                            System.out.printf("Customer %d has rented %d vehicle(s) of type %s.\n", customerId, count, type);
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;
               
                    case 4:
                        System.out.print("Enter Customer ID: ");
                        customerId = sc.nextInt();
                         foundCustomer = findCustomerById(customers, customerId);
                        if (foundCustomer != null) {
                            System.out.print("Enter the type of vehicle you wish to rent: ");
                            String type = sc.next();
                            System.out.println("Available vehicles of type " + type + ":");
                            for (Vehicle vehicle : availableVehicles) {
                                if (vehicle.isAvailable() && vehicle.getType().equalsIgnoreCase(type)) {
                                    System.out.printf("%-10s %-15s %-15s $%-10.2f\n",
                                            vehicle.getType(),
                                            vehicle.getRegistrationNumber(),
                                            vehicle.getBrand(),
                                            vehicle.getRentalRatePerDay());
                                }
                            }
                            if (foundCustomer.getNumberOfCurrentRented() >= foundCustomer.getVehiclesRented().length) {
                                System.out.println("You have reached your rental limit. The process for this customer is stopped.");
                            } else {
                                System.out.print("Enter the registration number of the vehicle you wish to rent: ");
                                String regNumber = sc.next();
                                Vehicle vehicle = findVehicleByRegistrationNumber(availableVehicles, regNumber);
                                if (vehicle != null && vehicle.isAvailable() && vehicle.getType().equalsIgnoreCase(type)) {
                                    System.out.print("Enter the number of rental days: ");
                                    int days = sc.nextInt();
                                    vehicle.setAvailable(false);
                                    foundCustomer.rentVehicle(vehicle, days);
                                    System.out.printf("Vehicle %s rented successfully. Total rental cost: $%.2f\n",
                                            vehicle.getRegistrationNumber(), vehicle.getRentalRatePerDay() * days);
                                } else {
                                    System.out.println("Vehicle not available or type mismatch.");
                                }
                            }
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;

                        
            
                    case 5:
                        System.out.print("Enter Customer ID: ");
                        customerId = sc.nextInt();
                        foundCustomer = findCustomerById(customers, customerId);
                        if (foundCustomer != null) {
                            System.out.println("Vehicles currently rented by customer " + customerId + ":");
                            for (Vehicle vehicle : foundCustomer.getVehiclesRented()) {
                                if (vehicle != null) {
                                    System.out.printf("%-10s %-15s %-15s $%-10.2f\n",
                                            vehicle.getType(),
                                            vehicle.getRegistrationNumber(),
                                            vehicle.getBrand(),
                                            vehicle.getRentalRatePerDay());
                                }
                            }
                            System.out.print("Enter the registration number of the vehicle you wish to return: ");
                            String regNumber = sc.next();
                            Vehicle vehicle = findVehicleByRegistrationNumber(availableVehicles, regNumber);
                            if (vehicle != null && !vehicle.isAvailable()) {
                                foundCustomer.returnVehicle(vehicle);
                              //  System.out.println("Vehicle returned successfully.");
                            } else {
                                System.out.println("Vehicle not found or already available.");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Displaying all available vehicles in ascending order of price:");
                        displayVehiclesByPrice(availableVehicles);
                        break;
                    case 7:
                        System.out.println("Displaying all available vehicles in alphabetical order of type:");
                        displayVehiclesByType(availableVehicles);
                        break;
                    case 8:
                        System.out.println("Exiting program...");
                       return;
                       
                       default :
                       System.out.println("Unavailable Option");
                }
            } 

    }
}


