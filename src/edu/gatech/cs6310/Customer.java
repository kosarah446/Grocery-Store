package edu.gatech.cs6310;

import java.util.TreeMap;

public class Customer extends Users{
    private int rating;
    private int credits;
    private TreeMap<String, Order> orders;
    private int pending;

    public Customer(String accountID, String firstName, String lastName, String phoneNum,
                    int rating, int credits) {
        super(accountID, firstName, lastName, phoneNum);
        this.rating = rating;
        this.credits = credits;
        this.orders = new TreeMap<>();
        this.pending = 0;
    }

    public boolean creditCheck(int quantity, int unitPrice) {
        int expense = quantity * unitPrice;
        return this.credits - (this.pending + expense) < 0;
    }
    // Complete the order; actualize the expense and reset pending amount
    public void execute(String orderID) {
        int expense = (int) this.orders.get(orderID).getTotal();
        this.credits -= expense;
        this.pending = 0;
    }
    // Temp hold of list of items in the order
    public void pending(int quantity, int unitPrice, String orderID) {
        this.orders.get(orderID);
        int expense = quantity * unitPrice;
        this.pending += expense;
    }

    public TreeMap<String, Order> getOrders() {
        return orders;
    }

    public void display() {
        String output = String.format("name:%s,phone:%s,rating:%d,credit:%d",
                this.getName(), this.getPhoneNum(), this.rating, this.credits);
        System.out.println(output);
    }

    public void addOrder(String orderID, Order list) {
        orders.put(orderID, list);
    }

}
