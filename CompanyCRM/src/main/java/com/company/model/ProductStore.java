package com.company.model;

import java.util.*;

public class ProductStore {
    private Map<String, Product> products = new HashMap<>();

    public ProductStore() {}

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

    public void decreaseQuantity(String productId, int quantity) {
        Product p = products.get(productId);
        if (p != null) {
            if (p.getQuantity() < quantity) {
                throw new IllegalArgumentException("Недостаточно товара в магазине для уменьшения.");
            }
            p.setQuantity(p.getQuantity() - quantity);
            if (p.getQuantity() <= 0) {
                products.remove(productId);
            }
        } else {
            throw new IllegalArgumentException("Товар не найден в пункте продаж: " + productId);
        }
    }

    public void increaseQuantity(String productId, int quantity) {
        if (products.containsKey(productId)) {
            products.get(productId).setQuantity(products.get(productId).getQuantity() + quantity);
        } else {
            throw new IllegalArgumentException("Товар не найден в пункте продаж: " + productId);
        }
    }

    public Map<String, Integer> getAvailableProducts() {
        Map<String, Integer> result = new HashMap<>();
        for (Product p : products.values()) {
            result.put(p.getId(), p.getQuantity());
        }
        return result;
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