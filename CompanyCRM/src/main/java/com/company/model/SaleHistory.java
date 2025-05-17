package com.company.model;

import java.util.*;

public class SaleHistory {
    private List<SaleRecord> records = new ArrayList<>();

    public void addSale(String storeId, String productId, int quantity, double totalPrice) {
        records.add(new SaleRecord(storeId, productId, quantity, totalPrice));
    }

    public List<SaleRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SaleRecord> records) {
        this.records = records != null ? new ArrayList<>(records) : new ArrayList<>();
    }

    public static class SaleRecord {
        private String storeId;
        private String productId;
        private int quantity;
        private double totalPrice;
        private Date timestamp;

        public SaleRecord() {}

        public SaleRecord(String storeId, String productId, int quantity, double totalPrice) {
            this.storeId = storeId;
            this.productId = productId;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
            this.timestamp = new Date();
        }

        public String getStoreId() { return storeId; }
        public void setStoreId(String storeId) { this.storeId = storeId; }

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        public double getTotalPrice() { return totalPrice; }
        public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

        public Date getTimestamp() { return timestamp; }
        public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

        @Override
        public String toString() {
            return "Магазин: " + storeId +
                    ", Товар: " + productId +
                    ", Кол-во: " + quantity +
                    ", Сумма: " + totalPrice +
                    ", Дата: " + timestamp;
        }
    }
}