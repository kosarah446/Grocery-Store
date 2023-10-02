package edu.gatech.cs6310;

import java.util.*;

public class Order {
    private String store;
    private String orderID;
    private String drone;
    private String customer;
    private TreeMap<String, OrderItem> list;
    private double total;
    private double totalWeight;

    public Order(String store, String orderID, String drone, String customer) {
        this.store = store;
        this.orderID = orderID;
        this.drone = drone;
        this.customer = customer;
        this.list = new TreeMap<>();
        this.total = 0;
        this.totalWeight = 0;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public String getOrderID() {
        return orderID;
    }

    public double getTotal() {
        return total;
    }
    public TreeMap<String, OrderItem> getList() {
        return list;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public void displayOrder() {
        Set<String> keys = list.keySet();
        for(String item : keys) {
            OrderItem current = list.get(item);
            System.out.println(String.format("item_name:%s,total_quantity:%d,total_cost:%d,total_weight:%d",
                    current.getItemName(), current.getTotalQuantity(), current.getTotalCost(), current.getTotalWeight()));
        }

    }
    public void addItem(String item, int quantity, int unitPrice, int weight) {
        int totalWeight = weight * quantity;
        int totalCost = unitPrice * quantity;
        this.list.put(item, new OrderItem(item, quantity, totalCost, totalWeight));
        this.total += totalCost;
        this.totalWeight += totalWeight;
    }

    public String getCustomer() {
        return customer;
    }

    public String getDrone() {
        return drone;
    }
}
