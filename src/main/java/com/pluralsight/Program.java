package com.pluralsight;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Vehicle> inventory = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("dealership.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|");
                if (data.length == 3) {
                    System.out.println("Skipping delearship info:" + line);
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
        System.out.println("Inventory loaded from dealership.csv:");
        for (Vehicle v : inventory) {
            System.out.println(v);


        }
    }
}
