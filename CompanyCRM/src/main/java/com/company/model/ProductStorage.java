package com.company.model;

import java.util.*;

public class ProductStorage {
    private Map<String, Product> products = new HashMap<>();

    public ProductStorage() {}

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Нельзя добавить пустой товар.");
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

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public void removeProduct(String productId, int quantity) {
        Product p = products.get(productId);
        if (p != null) {
            p.setQuantity(p.getQuantity() - quantity);
            if (p.getQuantity() <= 0) {
                products.remove(productId);
            }
        } else {
            throw new IllegalArgumentException("Товар не найден на складе: " + productId);
        }
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products != null ? new HashMap<>(products) : new HashMap<>();
    }
}