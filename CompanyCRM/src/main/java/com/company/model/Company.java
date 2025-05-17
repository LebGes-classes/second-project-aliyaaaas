package com.company.model;

import java.util.*;

public class Company {
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Employee> employees = new HashMap<>();
    private Map<String, Storage> storages = new HashMap<>();
    private Map<String, Store> stores = new HashMap<>();
    private List<RequestStore> requestStores = new ArrayList<>();
    private List<RequestStorage> requestStorages = new ArrayList<>();
    private SaleHistory saleHistory = new SaleHistory();
    private Map<String, Customer> customers = new HashMap<>();

    public Company() {}

    // PRODUCTS
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Невозможно добавить пустой товар.");
        }
        products.put(product.getId(), product);
    }

    public Product getProductById(String id) {
        return products.get(id);
    }

    public boolean isProductIdExists(String id) {
        return products.containsKey(id);
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products != null ? new HashMap<>(products) : new HashMap<>();
    }

    // EMPLOYEES
    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Невозможно добавить пустого сотрудника.");
        }
        employees.put(employee.getId(), employee);
    }

    public Employee getEmployeeById(String id) {
        return employees.get(id);
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<String, Employee> employees) {
        this.employees = employees != null ? new HashMap<>(employees) : new HashMap<>();
    }

    public void assignEmployeeToStore(String empId, String storeId) {
        Employee e = employees.get(empId);
        if (e != null) {
            e.setAssignedTo("store:" + storeId);
        } else {
            throw new IllegalArgumentException("Сотрудник не найден: " + empId);
        }
    }

    public void assignEmployeeToStorage(String empId, String storageId) {
        Employee e = employees.get(empId);
        if (e != null) {
            e.setAssignedTo("storage:" + storageId);
        } else {
            throw new IllegalArgumentException("Сотрудник не найден: " + empId);
        }
    }

    // STORAGES
    public void addStorage(Storage storage) {
        if (storage == null) {
            throw new IllegalArgumentException("Невозможно добавить пустой склад.");
        }
        storages.put(storage.getId(), storage);
    }

    public Storage getStorageById(String id) {
        return storages.get(id);
    }

    public Map<String, Storage> getStorages() {
        return storages;
    }

    public void setStorages(Map<String, Storage> storages) {
        this.storages = storages != null ? new HashMap<>(storages) : new HashMap<>();
    }

    // STORES
    public void addStore(Store store) {
        if (store == null) {
            throw new IllegalArgumentException("Невозможно добавить пустой магазин.");
        }
        stores.put(store.getId(), store);
    }

    public Store getStoreById(String id) {
        return stores.get(id);
    }

    public Map<String, Store> getStores() {
        return stores;
    }

    public void setStores(Map<String, Store> stores) {
        this.stores = stores != null ? new HashMap<>(stores) : new HashMap<>();
    }

    // REQUESTS
    public void addRequestStore(RequestStore req) {
        if (req == null) {
            throw new IllegalArgumentException("Запрос на магазин не может быть пустым.");
        }
        requestStores.add(req);
    }

    public void approveRequestStore(String storeId, String productId) {
        requestStores.stream()
                .filter(r -> r.getStoreId().equals(storeId) && r.getProductId().equals(productId))
                .findFirst()
                .ifPresent(r -> r.setApproved(true));
    }

    public void addRequestStorage(RequestStorage req) {
        if (req == null) {
            throw new IllegalArgumentException("Запрос на склад не может быть пустым.");
        }
        requestStorages.add(req);
    }

    public void approveRequestStorage(String storageId, String productId) {
        requestStorages.stream()
                .filter(r -> r.getStorageId().equals(storageId) && r.getProductId().equals(productId))
                .findFirst()
                .ifPresent(r -> r.setApproved(true));
    }

    // MOVE PRODUCT FROM STORAGE TO STORE
    public void processMoveFromStorageToStore(String productId, int quantity, String storeId) {
        for (Storage storage : storages.values()) {
            for (Cell cell : storage.getAllCells()) {
                Product p = cell.getProductById(productId);
                if (p != null && p.getQuantity() >= quantity) {
                    cell.removeProduct(productId, quantity);
                    Store store = stores.get(storeId);
                    if (store != null) {
                        store.receiveProduct(new Product(productId, p.getName(), p.getPrice(), quantity));
                    } else {
                        throw new IllegalArgumentException("Магазин не найден: " + storeId);
                    }
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Товар не найден на складе: " + productId);
    }

    // SALE HISTORY
    public SaleHistory getSaleHistory() {
        return saleHistory;
    }

    public void setSaleHistory(SaleHistory saleHistory) {
        this.saleHistory = saleHistory != null ? saleHistory : new SaleHistory();
    }

    // CUSTOMERS
    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Невозможно добавить пустого покупателя.");
        }
        customers.put(customer.getId(), customer);
    }

    public Customer getCustomerById(String id) {
        return customers.get(id);
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<String, Customer> customers) {
        this.customers = customers != null ? new HashMap<>(customers) : new HashMap<>();
    }

    // BUYING LOGIC
    public void sellToCustomer(String customerId, String storeId, String productId, int quantity) {
        Customer customer = customers.get(customerId);
        Store store = stores.get(storeId);
        Product product = null;

        if (store == null) {
            throw new IllegalArgumentException("Магазин не найден: " + storeId);
        }

        Product storeProduct = store.getProductById(productId);
        if (storeProduct != null && storeProduct.getQuantity() >= quantity) {
            product = storeProduct;
        } else {
            boolean found = false;
            for (Storage storage : storages.values()) {
                for (Cell cell : storage.getAllCells()) {
                    product = cell.getProductById(productId);
                    if (product != null && product.getQuantity() >= quantity) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            if (!found) {
                throw new IllegalArgumentException("Товар не найден на складе или его недостаточно: " + productId);
            }

            processMoveFromStorageToStore(productId, quantity, storeId);
            storeProduct = store.getProductById(productId);
        }

        if (storeProduct == null || storeProduct.getQuantity() < quantity) {
            throw new IllegalArgumentException("Недостаточно товара в магазине для покупки.");
        }

        store.sellProduct(productId, quantity, storeProduct.getPrice());
        if (customer != null) {
            customer.makePurchase(storeId, productId, quantity, storeProduct.getPrice(), saleHistory);
        }
    }
}