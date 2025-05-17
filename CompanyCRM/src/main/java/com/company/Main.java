package com.company;

import com.company.model.Company;
import com.company.service.FileService;
import com.company.service.InputHandler;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Company company = FileService.loadCompany();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n===== Главное меню =====");
                System.out.println("1. Управление товарами");
                System.out.println("2. Управление сотрудниками");
                System.out.println("3. Управление складами");
                System.out.println("4. Управление пунктами продаж");
                System.out.println("5. Управление заказами");
                System.out.println("6. Управление покупателями");
                System.out.println("7. Информация о системе");
                System.out.println("0. Выход");
                System.out.print("Выберите действие: ");

                int choice = -1;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Введите число.");
                    continue;
                }

                switch (choice) {
                    case 1 -> InputHandler.handleProductMenu(scanner, company);
                    case 2 -> InputHandler.handleEmployeeMenu(scanner, company);
                    case 3 -> InputHandler.handleStorageMenu(scanner, company);
                    case 4 -> InputHandler.handleStoreMenu(scanner, company);
                    case 5 -> InputHandler.handleOrderMenu(scanner, company);
                    case 6 -> InputHandler.handleCustomerMenu(scanner, company);
                    case 7 -> InputHandler.handleInfoMenu(scanner, company);
                    case 0 -> {
                        FileService.saveCompany(company);
                        System.out.println("Данные сохранены. До свидания!");
                        return;
                    }
                    default -> System.out.println("Неверный выбор.");
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка работы с файлом: " + e.getMessage());
        }
    }
}