package edu.gatech.cs6310;

import java.util.*;

public class Store {
    private String name;
    private double revenue;
    private TreeMap<String, Integer> catalog;
    private TreeMap<String, Drone> fleet;
    private TreeMap<String, Order> orders;
    private int countOrders;
    private int transfers;

    Store(String name, double revenue){
        this.name = name;
        this.revenue = revenue;
        this.catalog = new TreeMap<>();
        this.fleet = new TreeMap<>();
        this.orders = new TreeMap<>();

        this.countOrders = 0;
        this.transfers = 0;
    }
    public int getCountOrders() {
        return countOrders;
    }

    public int getTransfers() {
        return transfers;
    }

    public void addItem(String name, int weight) {
        catalog.put(name, weight);
    }

    public TreeMap<String, Integer> getCatalog() {
        return catalog;
    }

    public TreeMap<String, Order> getOrders() {
        return orders;
    }

    public void addOrder(String orderID, Order list) {
        orders.put(orderID, list);
    }

    public TreeMap<String, Drone> getFleet() {
        return fleet;
    }
    public void addDrone(String id, Drone drone) {
        fleet.put(id, drone);
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.countOrders += 1;
        this.revenue += revenue;
    }

    public void deductDroneHitRevenueLoss(Order order){
        this.revenue -= (100 + order.getTotal());
    }

    public String getName() {
        return name;
    }

    public void displayOrders() {

        Set<String> keys = orders.keySet();
        for(String order : keys) {
            String output = String.format("orderID:%s", order);
            System.out.println(output);
            if(!orders.get(order).getList().isEmpty()) {
                orders.get(order).displayOrder();
            }
        }
        System.out.println("OK:display_completed");
    }

    public void displayItems() {
        Set<String> keys = catalog.keySet();
        for(String name : keys) {
            System.out.println(name + "," + catalog.get(name));
        }
        System.out.println("OK:display_completed");
    }

    public void displayDrones(TreeMap<String, Pilot> pilots) {
        String output = "";
        Set<String> keys = fleet.keySet();
        for(String drone : keys) {
            Drone aircraft = fleet.get(drone);
            if(aircraft.getPilot() != null) {
                output = String.format("droneID:%s,total_cap:%d,num_orders:%d,remaining_cap:%d,trips_left:%d,flown_by:%s",
                        aircraft.getDroneID(),aircraft.getTotalCap(),
                        aircraft.getNumOrders(),aircraft.getRemainingCap(),
                        aircraft.getTripsLeft(),aircraft.getPilot());
            } else {
                output = String.format("droneID:%s,total_cap:%d,num_orders:%d,remaining_cap:%d,trips_left:%d",
                        aircraft.getDroneID(),aircraft.getTotalCap(),
                        aircraft.getNumOrders(),aircraft.getRemainingCap(),
                        aircraft.getTripsLeft());
            }
            System.out.println(output);
        }
        System.out.println("OK:display_completed");
    }

    public void initiateTransfer(Drone old, Drone transfer, Order order) {
        if(!old.getDroneID().equals(transfer.getDroneID())) {
//            System.out.println(order.getTotalWeight());
            int weight = (int) order.getTotalWeight();
            // Transfer weights
            old.transferReduceWeight(weight);
            transfer.transferAddWeight(weight);
            // Update new drone with new order
            transfer.addOrder(order.getOrderID(), order);
            // Update order with new drone
            order.setDrone(transfer.getDroneID());
            // Remove old order
            old.removeOrder(order.getOrderID());
            this.transfers += 1;
        }

    }
}
