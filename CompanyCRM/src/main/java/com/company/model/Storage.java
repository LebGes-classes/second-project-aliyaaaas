package com.company.model;

import java.util.*;

public class Storage {
    private String id;
    private String address;
    private Map<String, Cell> cells = new HashMap<>();

    public Storage() {}

    public Storage(String id, String address) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(" ID склада не может быть пустым.");
        }
        this.id = id;
        this.address = address;
    }

    public void addCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(" Нельзя добавить пустую ячейку.");
        }
        cells.put(cell.getId(), cell);
    }

    public Cell getCellById(String cellId) {
        return cells.get(cellId);
    }

    public Collection<Cell> getAllCells() {
        return cells.values();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(" ID склада не может быть пустым.");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "Склад ID: " + id + ", Адрес: " + address + ", Ячеек: " + cells.size();
    }
}