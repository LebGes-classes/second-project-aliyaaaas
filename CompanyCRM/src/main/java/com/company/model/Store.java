package com.company.model;

import java.util.*;
import java.util.stream.Collectors;

public class Store {
    private String id;
    private String name;
    private List<String> addresses = new ArrayList<>();
    private ProductStore productStore = new ProductStore();
    private double profit = 0.0;
    private boolean isOpen = true;

    public Store() {}

    public Store(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID магазина не может быть пустым.");
        }
        this.id = id;
        this.name = name;
    }

    public void addAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Адрес не может быть пустым.");
        }
        addresses.add(address);
    }

    public void sellProduct(String productId, int quantity, double price) {
        productStore.decreaseQuantity(productId, quantity);
        profit += price * quantity;
    }

    public void returnProduct(String productId, int quantity) {
        productStore.increaseQuantity(productId, quantity);
    }

    public Product getProductById(String productId) {
        return productStore.getProduct(productId);
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses != null ? addresses : new ArrayList<>();
    }

    public ProductStore getProductStore() {
        return productStore;
    }

    public void setProductStore(ProductStore productStore) {
        if (productStore == null) {
            throw new IllegalArgumentException("Нельзя установить пустой productStore.");
        }
        this.productStore = productStore;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID магазина не может быть пустым.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getAvailableProducts() {
        return productStore.getAvailableProducts();
    }

    public void receiveProduct(Product product) {
        productStore.addProduct(product);
    }

    @Override
    public String toString() {
        return "Пункт продаж ID: " + id +
                ", Название: " + name +
                ", Открыт: " + (isOpen ? "Да" : "Нет") +
                ", Адресов: " + addresses.size();
    }
}