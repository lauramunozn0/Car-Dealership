package com.pluralsight;

public class Program {
    public static void main(String[] args) {
        Dealership dealership = new Dealership();

        Vehicle car1 = new Vehicle();

        dealership.addVehicle(car1);

        dealership.showInventory();


    }
}
