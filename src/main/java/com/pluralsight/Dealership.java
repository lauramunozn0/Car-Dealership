package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership() {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public  ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }
    public void showInventory() {
        for (Vehicle v : inventory) {
            System.out.println(v);
        }
    }
}
