package com.pluralsight;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Vehicle> inventory = new ArrayList<>();
        Dealership dealership = DealershipFileManager.getDealership();
        System.out.println("Welcome to " + dealership.getName());
        System.out.println("Address: " + dealership.getAddress());
        System.out.println("Phone: " + dealership.getPhone());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("~~~~~~~~~~ Car Dealership Menu ~~~~~~~~~~");
            System.out.println("1. Show all vehicles");
            System.out.println("2. Search by make or model");
            System.out.println("3. Search by price range");
            System.out.println("4. Exit");
            System.out.println("5. Add Vehicle");
            System.out.print("Please enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    dealership.showInventory();
                    break;

                case 2:
                    System.out.print("Enter make or model to search: ");
                    String term = scanner.nextLine().toLowerCase();

                    boolean foundMatch = false;
                    for (Vehicle v : dealership.getAllVehicles()) {
                        if (v.getMake().toLowerCase().contains(term) ||
                                v.getModel().toLowerCase().contains(term)) {
                            System.out.println(v);
                            foundMatch = true;
                        }
                    }
                    if (!foundMatch) {
                        System.out.println("No vehicles found matching that search.");
                    }
                    break;

                case 3:
                    System.out.print("Enter minimum price: ");
                    String minInput = scanner.nextLine()
                            .replace("$", "")
                            .replace(",", "")
                            .trim();
                    double min = Double.parseDouble(minInput);

                    System.out.print("Enter maximum price: ");
                    String maxInput = scanner.nextLine()
                            .replace("$", "")
                            .replace(",", "")
                            .trim();
                    double max = Double.parseDouble(maxInput);

                    boolean foundInRange = false;
                    for (Vehicle v : dealership.getAllVehicles()) {
                        if (v.getPrice() >= min && v.getPrice() <= max) {
                            System.out.println(v);
                            foundInRange = true;
                        }
                    }
                    if (!foundInRange) {
                        System.out.println("No vehicles found in that price range.");
                    }
                    break;
                case 4:
                    System.out.println("Goodbye! Thanks for visiting us :) ! " + dealership.getName() + ".");
                    break;

                default:
                    System.out.println("Please try again");

                case 5:
                    System.out.println("Enter new vehicle info:");

                    System.out.print("VIN: ");
                    int vin = Integer.parseInt(scanner.nextLine());
                    System.out.print("Year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Make: ");
                    String make = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Odometer: ");
                    int odometer = Integer.parseInt(scanner.nextLine());
                    System.out.print("Price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    dealership.addVehicle(newVehicle);
                    DealershipFileManager.saveDealership(dealership);

                    System.out.println("Vehicle added successfully!");
                    break;
            }
        }while  (choice != 4);

        scanner.close();
    }
}
