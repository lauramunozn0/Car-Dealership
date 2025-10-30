package com.pluralsight;


import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public Dealership() {
        this("Unknown Dealer", "No Address", "No Phone");
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory for " + name);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "Inventory for Car World");
            System.out.println("Nothing found in the inventory");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "Inventory for Car World");
            return;
        }

        System.out.println("Inventory for " + name);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%-8s | %-6s | %-10s | %-10s |  %-10s | %-10s | %10s | %12s%n",
                "VIN", "YEAR", "MAKE", "MODEL", "TYPE", "COLOR", "MILES", "PRICE");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        for (Vehicle v : inventory) {
            System.out.printf(("%-8d | %-6d | %-10s | %-10s | %-10s | %-10s | %,10d | $%,12.2f%n"),
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getType(), v.getColor(), v.getOdometer(), v.getPrice());
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
