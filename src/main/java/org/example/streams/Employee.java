package org.example.streams;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String id, name, department;
    private double salary;
    private LocalDate hireDate;

    public Employee(String id, String name, String dept, double salary, LocalDate hireDate) {
        this.id = id; this.name = name; this.department = dept; this.salary = salary; this.hireDate = hireDate;
    }
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public LocalDate getHireDate() { return hireDate; }
    @Override public String toString() { return name + " (" + id + ")"; }
}

class Order {
    private String id, customerId;
    private List<OrderItem> items;
    private LocalDate orderDate;

    public Order(String id, String customerId, List<OrderItem> items, LocalDate orderDate) {
        this.id = id; this.customerId = customerId; this.items = items; this.orderDate = orderDate;
    }
    // Getters
    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public List<OrderItem> getItems() { return items; }
    public LocalDate getOrderDate() { return orderDate; }
    @Override public String toString() { return "Order " + id; }
}

class OrderItem {
    private String productId;
    private int quantity;
    private double price;

    public OrderItem(String productId, int quantity, double price) {
        this.productId = productId; this.quantity = quantity; this.price = price;
    }
    // Getters
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getTotal() { return quantity * price; }
    @Override public String toString() { return productId + " x" + quantity; }
}