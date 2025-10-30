package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

    public static Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("dealership.csv"))) {
            String line;
            boolean dealershipInfoRead = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split("\\|");

                // Read dealership info line (3 items)
                if (data.length == 3 && !dealershipInfoRead) {
                    String name = data[0];
                    String address = data[1];
                    String phone = data[2];
                    dealership = new Dealership( name, address, phone);
                    dealershipInfoRead = true;
                    continue;
                }

                // Read vehicle lines (8 items)
                if (data.length == 8 && dealership != null) {
                    int vin = Integer.parseInt(data[0]);
                    int year = Integer.parseInt(data[1]);
                    String make = data[2];
                    String model = data[3];
                    String type = data[4];
                    String color = data[5];
                    int odometer = Integer.parseInt(data[6]);
                    double price = Double.parseDouble(data[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (dealership == null) {
            dealership = new Dealership("Unknown Dealer", "No Address", "No Phone");
        }

        return dealership;
    }

    public static void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dealership.csv"))) {

            // First line: dealership info
            writer.write(String.format("%s|%s|%s",
                    dealership.getName(),
                    dealership.getAddress(),
                    dealership.getPhone()));
            writer.newLine();

            // Remaining lines: vehicle info
            for (Vehicle v : dealership.getAllVehicles()) {
                String line = String.format(
                        "%d|%d|%s|%s|%s|%s|%d|%.2f",
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice());
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
