package com.company.model;

public class RequestStore {
    private String storeId;
    private String productId;
    private int quantity;
    private boolean approved;

    public RequestStore() {}

    public RequestStore(String storeId, String productId, int quantity) {
        if (storeId == null || productId == null || quantity <= 0) {
            throw new IllegalArgumentException("Некорректные данные заявки на магазин.");
        }
        this.storeId = storeId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getStoreId() { return storeId; }
    public void setStoreId(String storeId) { this.storeId = storeId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным.");
        }
        this.quantity = quantity;
    }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    @Override
    public String toString() {
        return "Запрос на пункт продаж ID: " + storeId +
                ", Товар ID: " + productId +
                ", Кол-во: " + quantity +
                ", Одобрен: " + (approved ? "Да" : "Нет");
    }
}