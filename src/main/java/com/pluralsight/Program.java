package com.pluralsight;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Vehicle> inventory = new ArrayList<>();
        Dealership dealership = DealershipFileManager.getDealership();

        System.out.println("Welcome to " + dealership.getName());
        System.out.println("Address: " + dealership.getAddress());
        System.out.println("Phone: " + dealership.getPhone());
        System.out.println("--------------------------------------");

        dealership.showInventory();

        try (BufferedReader reader = new BufferedReader(new FileReader("dealership.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|");
                if (data.length == 3) {
                    String name = data[0];
                    String address = data[1];
                    String phone = data[2];
                    dealership = new Dealership(name, address, phone);
                    System.out.println("Dealership : " + name + " | " + address + " | " + phone);
                    continue;
                }
                if (data.length == 8) {
                    int vin = Integer.parseInt(data[0]);
                    int year = Integer.parseInt(data[1]);
                    String make = data[2];
                    String model = data[3];
                    String type = data[4];
                    String color = data[5];
                    int odometer = Integer.parseInt(data[6]);
                    double price = Double.parseDouble(data[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    inventory.add(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        if (dealership != null) {
            for (Vehicle v : inventory) {
                dealership.addVehicle(v);
            }

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Cars Available ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            dealership.showInventory();
        } else {
            System.out.println("No dealership information found in file.");
        }

    }
}
