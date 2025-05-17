package com.company.model;

import java.util.*;

public class Cell {
    private String id;
    private Map<String, Product> products = new HashMap<>();

    public Cell() {}

    public Cell(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID ячейки не может быть пустым.");
        }
        this.id = id;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Невозможно добавить пустой товар в ячейку.");
        }
        if (products.containsKey(product.getId())) {
            Product existing = products.get(product.getId());
            existing.setQuantity(existing.getQuantity() + product.getQuantity());
        } else {
            products.put(product.getId(), new Product(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity()
            ));
        }
    }

    public Product getProductById(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException(" ID товара не может быть пустым.");
        }
        return products.get(productId);
    }

    public void removeProduct(String productId, int quantity) {
        Product p = products.get(productId);
        if (p != null) {
            if (p.getQuantity() < quantity) {
                throw new IllegalArgumentException(" Недостаточно товара в ячейке для удаления.");
            }
            p.setQuantity(p.getQuantity() - quantity);
            if (p.getQuantity() <= 0) {
                products.remove(productId);
            }
        } else {
            throw new IllegalArgumentException(" Товар не найден в ячейке: " + productId);
        }
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products != null ? products : new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(" ID ячейки не может быть пустым.");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ячейка ID: " + id + ", Товаров: " + products.size();
    }
}