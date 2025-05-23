package com.company.model;

public class Employee {
    private String id;
    private String name;
    private String role;
    private String assignedTo;

    public Employee() {}

    public Employee(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    @Override
    public String toString() {
        return "ID: " + id + ", Имя: " + name + ", Роль: " + role + ", Назначен: " + assignedTo;
    }
}