package com.company.model;

public class RequestStorage {
    private String storageId;
    private String productId;
    private int quantity;
    private boolean approved;

    public RequestStorage() {}

    public RequestStorage(String storageId, String productId, int quantity) {
        if (storageId == null || productId == null || quantity <= 0) {
            throw new IllegalArgumentException("Некорректные данные заявки на склад.");
        }
        this.storageId = storageId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getStorageId() { return storageId; }
    public void setStorageId(String storageId) { this.storageId = storageId; }

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
        return "Запрос на склад ID: " + storageId +
                ", Товар ID: " + productId +
                ", Кол-во: " + quantity +
                ", Одобрен: " + (approved ? "Да" : "Нет");
    }
}