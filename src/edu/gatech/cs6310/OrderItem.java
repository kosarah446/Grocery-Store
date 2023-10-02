package edu.gatech.cs6310;

public class OrderItem {
    private String itemName;
    private int totalQuantity;
    private int totalCost;
    private int totalWeight;

    public OrderItem(String itemName, int totalQuantity, int totalCost, int totalWeight) {
        this.itemName = itemName;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
    }

    public String getItemName() {
        return itemName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getTotalWeight() {
        return totalWeight;
    }
}
