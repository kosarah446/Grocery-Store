package edu.gatech.cs6310;

import java.util.TreeMap;

public class Drone {
    private String store;
    private String droneID;
    private int totalCap;
    private int remainingCap;
    private int tripsLeft;
    private int fuel;
    private int numOrders;
    private String pilot;
    private String pilotID;
    private TreeMap<String, Order> orders;
    private int overload;
    private int health;
    private boolean active;
    public Drone(String store, String droneID, int totalCap, int fuel) {
        this.store = store;
        this.droneID = droneID;
        this.totalCap = totalCap;
        this.tripsLeft = fuel;

        this.remainingCap = totalCap;
        this.numOrders = 0;

        this.pilot = null;
        this.pilotID = null;
        this.orders = new TreeMap<>();

        this.overload = 0;
        this.health = 10;
        this.active = true;
    }

    public boolean damaged(int dmg) {
        // Determine if drone is too damaged to continue flying and must be repaired
        System.out.println("The drone took " + dmg + " damage!");
        this.health -= dmg;
        if(this.health < 7) {
            this.active = false;
            return true;
        } else {
            return false;
        }
    }

    public int getOverload() { return overload; }

    public void cancel(Order order) {
        // Eliminates and reset num of orders and carrying capacity
        this.numOrders -= 1;
        this.remainingCap += order.getTotalWeight();
    }
    public boolean transferCheck(Drone newDrone, String order) {
        // Determine if there is carrying capacity for the items into the new drone
        int capacity = newDrone.getRemainingCap();
        int orderWeight = (int) this.orders.get(order).getTotalWeight();
        return capacity < orderWeight;
    }

    public String getPilotID() {
        return pilotID;
    }
    public boolean fuelCheck() { return this.tripsLeft <= 0; }
    public void addOrder(String orderID, Order list) {
        orders.put(orderID, list);
        this.numOrders += 1;
    }

    public void removeOrder(String orderID) {
        this.numOrders -=1;
        orders.remove(orderID);
    }
    public void deliver(String order) {
        int unload = (int) this.orders.get(order).getTotalWeight();
        this.numOrders -= 1;
        this.tripsLeft -= 1;
        this.remainingCap += unload;
        if(this.numOrders >= 0) {
            this.overload += this.numOrders;
        }
    }
    public boolean weightCheck(int weight, int quantity) {
        int total = weight * quantity;
        return total > this.remainingCap;
    }
    public void deductWeight(int weight, int quantity) {
        int total = weight * quantity;
        this.remainingCap -= total;
    }

    public void transferAddWeight(int weight) { this.remainingCap -= weight; }
    public void transferReduceWeight(int weight) { this.remainingCap += weight; }
    public String getPilot() { return pilot; }

    public void setPilot(String pilot, String pilotID) {
        this.pilot = pilot;
        this.pilotID = pilotID;
    }

    public void resetPilot() {
        this.pilot = null;
        this.pilotID = null;
    }

    public String getStore() { return store; }

    public String getDroneID() { return droneID; }

    public int getTotalCap() { return totalCap; }

    public int getRemainingCap() { return remainingCap; }

    public int getTripsLeft() { return tripsLeft; }

    public int getNumOrders() { return numOrders; }
}
