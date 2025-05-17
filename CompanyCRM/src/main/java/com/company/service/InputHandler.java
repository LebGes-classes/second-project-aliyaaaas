package com.company.service;

import com.company.model.*;
import java.util.*;

public class InputHandler {

    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Введите корректное число: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double getValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Введите корректное число: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public static void handleProductMenu(Scanner scanner, Company company) {
        System.out.println("\n--- Управление товарами ---");
        System.out.println("1. Добавить товар");
        System.out.println("2. Показать все товары");
        System.out.print("Выберите действие: ");
        int choice = getValidInt(scanner);

        switch (choice) {
            case 1 -> {
                try {
                    System.out.print("Введите ID товара: ");
                    String id = scanner.nextLine().trim();
                    if (company.isProductIdExists(id)) {
                        throw new IllegalArgumentException("Товар с таким ID уже существует.");
                    }
                    System.out.print("Введите название: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Введите цену: ");
                    double price = getValidDouble(scanner);
                    System.out.print("Введите количество: ");
                    int quantity = getValidInt(scanner);

                    Product product = new Product(id, name, price, quantity);
                    company.addProduct(product);
                    System.out.println("Товар добавлен.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 2 -> {
                if (company.getProducts().isEmpty()) {
                    System.out.println("Нет доступных товаров.");
                } else {
                    company.getProducts().values().forEach(System.out::println);
                }
            }
            default -> System.out.println("Неверный выбор.");
        }
    }

    public static void handleEmployeeMenu(Scanner scanner, Company company) {
        System.out.println("\n--- Управление сотрудниками ---");
        System.out.println("1. Добавить сотрудника");
        System.out.println("2. Назначить на пункт продаж");
        System.out.println("3. Назначить на склад");
        System.out.println("4. Показать всех сотрудников");
        System.out.print("Выберите действие: ");
        int choice = getValidInt(scanner);

        switch (choice) {
            case 1 -> {
                try {
                    System.out.print("Введите ID сотрудника: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Введите роль (например, MANAGER): ");
                    String role = scanner.nextLine().trim();

                    Employee emp = new Employee(id, name, role);
                    company.addEmployee(emp);
                    System.out.println("Сотрудник добавлен.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 2 -> {
                try {
                    System.out.print("Введите ID сотрудника: ");
                    String empId = scanner.nextLine().trim();
                    System.out.print("Введите ID пункта продаж: ");
                    String storeId = scanner.nextLine().trim();
                    company.assignEmployeeToStore(empId, storeId);
                    System.out.println("Сотрудник назначен на пункт продаж.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 3 -> {
                try {
                    System.out.print("Введите ID сотрудника: ");
                    String empId = scanner.nextLine().trim();
                    System.out.print("Введите ID склада: ");
                    String storageId = scanner.nextLine().trim();
                    company.assignEmployeeToStorage(empId, storageId);
                    System.out.println(" Сотрудник назначен на склад.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 4 -> {
                if (company.getEmployees().values().isEmpty()) {
                    System.out.println("Нет зарегистрированных сотрудников.");
                } else {
                    company.getEmployees().values().forEach(System.out::println);
                }
            }
            default -> System.out.println(" Неверный выбор.");
        }
    }

    public static void handleStorageMenu(Scanner scanner, Company company) {
        System.out.println("\n--- Управление складами ---");
        System.out.println("1. Добавить склад");
        System.out.println("2. Показать все склады");
        System.out.println("3. Добавить товар на склад");
        System.out.print("Выберите действие: ");
        int choice = getValidInt(scanner);

        switch (choice) {
            case 1 -> {
                try {
                    System.out.print("Введите ID склада: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Введите адрес: ");
                    String address = scanner.nextLine().trim();
                    company.addStorage(new Storage(id, address));
                    System.out.println(" Склад добавлен.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 2 -> {
                if (company.getStorages().isEmpty()) {
                    System.out.println("Нет доступных складов.");
                } else {
                    company.getStorages().values().forEach(System.out::println);
                }
            }
            case 3 -> {
                try {
                    System.out.print("Введите ID склада: ");
                    String storageId = scanner.nextLine().trim();
                    System.out.print("Введите ID товара: ");
                    String productId = scanner.nextLine().trim();
                    System.out.print("Введите количество: ");
                    int qty = getValidInt(scanner);

                    Storage storage = company.getStorageById(storageId);
                    Product product = company.getProductById(productId);

                    if (storage == null || product == null) {
                        throw new IllegalArgumentException("Склад или товар не найдены.");
                    }

                    Cell cell = new Cell("CELL_" + productId);
                    cell.addProduct(new Product(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            qty
                    ));
                    storage.addCell(cell);
                    System.out.println(" Товар добавлен на склад.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            default -> System.out.println(" Неверный выбор.");
        }
    }

    public static void handleStoreMenu(Scanner scanner, Company company) {
        System.out.println("\n--- Управление пунктами продаж ---");
        System.out.println("1. Добавить пункт продаж");
        System.out.println("2. Добавить адрес к пункту продаж");
        System.out.println("3. Показать все пункты продаж");
        System.out.print("Выберите действие: ");
        int choice = getValidInt(scanner);

        switch (choice) {
            case 1 -> {
                try {
                    System.out.print("Введите ID пункта продаж: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Введите название: ");
                    String name = scanner.nextLine().trim();
                    Store store = new Store(id, name);
                    company.addStore(store);
                    System.out.println(" Пункт продаж добавлен.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 2 -> {
                try {
                    System.out.print("Введите ID пункта продаж: ");
                    String storeId = scanner.nextLine().trim();
                    System.out.print("Введите адрес: ");
                    String address = scanner.nextLine().trim();

                    Store store = company.getStoreById(storeId);
                    if (store == null) {
                        throw new IllegalArgumentException("Пункт продаж не найден.");
                    }

                    store.addAddress(address);
                    System.out.println(" Адрес добавлен к пункту продаж.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 3 -> {
                if (company.getStores().isEmpty()) {
                    System.out.println("Нет доступных пунктов продаж.");
                } else {
                    company.getStores().values().forEach(System.out::println);
                }
            }
            default -> System.out.println(" Неверный выбор.");
        }
    }

    public static void handleOrderMenu(Scanner scanner, Company company) {
        System.out.println("\n--- Заказы и перемещения ---");
        System.out.println("1. Переместить товар со склада в магазин");
        System.out.print("Выберите действие: ");
        int choice = getValidInt(scanner);

        switch (choice) {
            case 1 -> {
                try {
                    System.out.print("Введите ID пункта продаж: ");
                    String storeId = scanner.nextLine().trim();
                    System.out.print("Введите ID товара: ");
                    String productId = scanner.nextLine().trim();
                    System.out.print("Введите количество: ");
                    int quantity = getValidInt(scanner);

                    company.processMoveFromStorageToStore(productId, quantity, storeId);
                    System.out.println(" Перемещение выполнено.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            default -> System.out.println(" Неверный выбор.");
        }
    }

    public static void handleCustomerMenu(Scanner scanner, Company company) {
        System.out.println("\n--- Управление покупателями ---");
        System.out.println("1. Добавить покупателя");
        System.out.println("2. Совершить покупку");
        System.out.println("3. Вернуть товар");
        System.out.println("4. Показать историю покупок");
        System.out.print("Выберите действие: ");
        int choice = getValidInt(scanner);

        switch (choice) {
            case 1 -> {
                try {
                    System.out.print("Введите ID покупателя: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Введите имя покупателя: ");
                    String name = scanner.nextLine().trim();
                    company.addCustomer(new Customer(id, name));
                    System.out.println(" Покупатель добавлен.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 2 -> {
                try {
                    System.out.print("Введите ID покупателя: ");
                    String custId = scanner.nextLine().trim();
                    System.out.print("Введите ID магазина: ");
                    String storeId = scanner.nextLine().trim();
                    System.out.print("Введите ID товара: ");
                    String prodId = scanner.nextLine().trim();
                    System.out.print("Введите количество: ");
                    int qty = getValidInt(scanner);

                    company.sellToCustomer(custId, storeId, prodId, qty);
                    System.out.println(" Покупка совершена.");
                } catch (IllegalArgumentException e) {
                    System.err.println(" Ошибка покупки: " + e.getMessage());
                }
            }
            case 3 -> {
                try {
                    System.out.print("Введите ID покупателя: ");
                    String custId = scanner.nextLine().trim();
                    System.out.print("Введите ID магазина: ");
                    String storeId = scanner.nextLine().trim();
                    System.out.print("Введите ID товара: ");
                    String prodId = scanner.nextLine().trim();
                    System.out.print("Введите количество: ");
                    int qty = getValidInt(scanner);

                    Customer customer = company.getCustomerById(custId);
                    Store store = company.getStoreById(storeId);
                    if (customer != null && store != null) {
                        customer.returnProduct(storeId, prodId, qty, store);
                        System.out.println(" Товар возвращён.");
                    } else {
                        System.err.println(" Покупатель или магазин не найдены.");
                    }
                } catch (Exception e) {
                    System.err.println(" Ошибка возврата: " + e.getMessage());
                }
            }
            case 4 -> {
                if (company.getCustomers().isEmpty()) {
                    System.out.println("Нет зарегистрированных покупателей.");
                } else {
                    for (Customer c : company.getCustomers().values()) {
                        System.out.println(c);
                        if (c.getPurchaseHistory().isEmpty()) {
                            System.out.println("  История покупок отсутствует.");
                        } else {
                            c.getPurchaseHistory().forEach(p -> System.out.println("  " + p));
                        }
                    }
                }
            }
            default -> System.out.println(" Неверный выбор.");
        }
    }

    public static void handleInfoMenu(Scanner scanner, Company company) {
        System.out.println("\n--- Информация о системе ---");
        System.out.println("1. Информация о складах");
        System.out.println("2. Информация о пунктах продаж");
        System.out.println("3. Информация о прибыли");
        System.out.println("4. Информация о товарах");
        System.out.print("Выберите действие: ");
        int choice = getValidInt(scanner);

        switch (choice) {
            case 1 -> {
                if (company.getStorages().isEmpty()) {
                    System.out.println("Нет доступных складов.");
                } else {
                    company.getStorages().values().forEach(System.out::println);
                }
            }
            case 2 -> {
                if (company.getStores().isEmpty()) {
                    System.out.println("Нет доступных пунктов продаж.");
                } else {
                    company.getStores().values().forEach(System.out::println);
                }
            }
            case 3 -> {
                if (company.getStores().isEmpty()) {
                    System.out.println("Нет данных о прибыли.");
                } else {
                    for (Store s : company.getStores().values()) {
                        System.out.println("Магазин: " + s.getName() + ", Прибыль: " + s.getProfit());
                    }
                }
            }
            case 4 -> {
                if (company.getProducts().isEmpty()) {
                    System.out.println("Нет доступных товаров.");
                } else {
                    company.getProducts().values().forEach(System.out::println);
                }
            }
            default -> System.out.println(" Неверный выбор.");
        }
    }
}