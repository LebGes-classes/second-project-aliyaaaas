package com.company.model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private boolean availableForPurchase;

    public Product() {}

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.availableForPurchase = true;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isAvailableForPurchase() { return availableForPurchase; }
    public void setAvailableForPurchase(boolean availableForPurchase) { this.availableForPurchase = availableForPurchase; }

    @Override
    public String toString() {
        return "ID: " + id + ", Название: " + name + ", Цена: " + price + ", Кол-во: " + quantity;
    }
}