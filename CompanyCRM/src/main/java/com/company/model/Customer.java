package com.company.model;

import java.util.*;

public class Customer {
    private String id;
    private String name;
    private List<SaleHistory.SaleRecord> purchaseHistory = new ArrayList<>();

    public Customer() {}

    public Customer(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID покупателя не может быть пустым.");
        }
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void makePurchase(String storeId, String productId, int quantity, double pricePerUnit, SaleHistory saleHistory) {
        if (storeId == null || productId == null || quantity <= 0 || pricePerUnit <= 0) {
            throw new IllegalArgumentException("Некорректные данные покупки.");
        }
        double totalPrice = quantity * pricePerUnit;
        SaleHistory.SaleRecord record = new SaleHistory.SaleRecord(storeId, productId, quantity, totalPrice);
        purchaseHistory.add(record);
        saleHistory.addSale(storeId, productId, quantity, totalPrice);
    }

    public void returnProduct(String storeId, String productId, int quantity, Store store) {
        if (store == null) {
            throw new IllegalArgumentException("Магазин не найден.");
        }
        store.returnProduct(productId, quantity);
    }

    public List<SaleHistory.SaleRecord> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<SaleHistory.SaleRecord> purchaseHistory) {
        this.purchaseHistory = purchaseHistory != null ? new ArrayList<>(purchaseHistory) : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Покупатель ID: " + id + ", Имя: " + name + ", Покупок: " + purchaseHistory.size();
    }
}