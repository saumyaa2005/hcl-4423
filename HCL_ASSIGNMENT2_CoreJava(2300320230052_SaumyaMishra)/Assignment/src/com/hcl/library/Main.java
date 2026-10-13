package com.hcl.library;

import com.hcl.library.exception.*;
import com.hcl.library.model.*;
import com.hcl.library.service.LibraryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static final LibraryService service = new LibraryService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Seed initial data
        service.addItem(new Book("Clean Code", 2008, "Robert Martin", "978-0132350884"));
        service.addItem(new Book("Effective Java", 2018, "Joshua Bloch", "978-0134685991"));
        service.addItem(new Magazine("Java Magazine", 2023, 42));
        service.addItem(new DVD("Inception", 2010, 148));

        service.registerMember(new Member(1, "Aman", "aman@example.com"));
        service.registerMember(new Member(2, "Ravi", "ravi@example.com"));

        while (true) {
            System.out.println("===== LIBRARY MENU =====");
            System.out.println("1. Add item     2. Register member     3. Borrow     4. Return");
            System.out.println("5. Waitlist     6. List items          7. Search     8. Export report     0. Exit");
            System.out.print("Choice: ");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    System.out.println("Exiting Library Management System. Goodbye!");
                    break;
                }

                switch (choice) {
                    case 1:
                        addItemMenu();
                        break;
                    case 2:
                        registerMemberMenu();
                        break;
                    case 3:
                        borrowMenu();
                        break;
                    case 4:
                        returnMenu();
                        break;
                    case 5:
                        viewWaitlistMenu();
                        break;
                    case 6:
                        listItemsMenu();
                        break;
                    case 7:
                        searchMenu();
                        break;
                    case 8:
                        exportReportMenu();
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (ItemNotFoundException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (ItemNotAvailableException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (BorrowLimitExceededException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (LibraryException e) {
                System.out.println("Library Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    private static void addItemMenu() {
        System.out.print("Type (1.Book, 2.Magazine, 3.DVD): ");
        int type = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        if (type == 1) {
            System.out.print("Author: ");
            String author = scanner.nextLine().trim();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine().trim();
            service.addItem(new Book(title, year, author, isbn));
            System.out.println("Book added successfully.");
        } else if (type == 2) {
            System.out.print("Issue Number: ");
            int issue = Integer.parseInt(scanner.nextLine().trim());
            service.addItem(new Magazine(title, year, issue));
            System.out.println("Magazine added successfully.");
        } else if (type == 3) {
            System.out.print("Duration (mins): ");
            int duration = Integer.parseInt(scanner.nextLine().trim());
            service.addItem(new DVD(title, year, duration));
            System.out.println("DVD added successfully.");
        }
    }

    private static void registerMemberMenu() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        service.registerMember(new Member(name, email));
        System.out.println("Member registered successfully.");
    }

    private static void borrowMenu() {
        System.out.print("Member id: ");
        int memberId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Item id: ");
        int itemId = Integer.parseInt(scanner.nextLine().trim());

        try {
            LibraryItem item = service.borrowItem(memberId, itemId);
            System.out.println("Issued: " + item);
        } catch (ItemNotAvailableException e) {
            System.out.print("ERROR: " + e.getMessage() + " Added to waitlist? (y/n): ");
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
                service.addToWaitlist(itemId, memberId);
            }
        }
    }

    private static void returnMenu() {
        System.out.print("Item id: ");
        int itemId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Late by (days): ");
        int days = Integer.parseInt(scanner.nextLine().trim());

        service.returnItem(itemId, days);
    }

    private static void viewWaitlistMenu() {
        Map<Integer, Queue<Member>> map = service.getWaitlistMap();
        if (map.isEmpty()) {
            System.out.println("No waitlists active.");
            return;
        }
        for (Map.Entry<Integer, Queue<Member>> entry : map.entrySet()) {
            System.out.println("Item " + entry.getKey() + " Waitlist: " + entry.getValue());
        }
    }

    private static void listItemsMenu() {
        System.out.print("Sort by (1. Title, 2. Year Descending): ");
        int sort = Integer.parseInt(scanner.nextLine().trim());

        List<LibraryItem> items = (sort == 2)
                ? service.getItemsSortedByYearDescThenTitle()
                : service.getItemsSortedByTitle();

        for (LibraryItem item : items) {
            String status = item.isAvailable() ? "Available" : "Borrowed";
            System.out.println(" - " + item + " [" + status + "]");
        }

        System.out.println("Stats: " + service.getItemTypeStatistics());
    }

    private static void searchMenu() {
        System.out.print("Title to search: ");
        String title = scanner.nextLine().trim();
        Optional<LibraryItem> res = service.searchByTitle(title);
        if (res.isPresent()) {
            System.out.println("Found: " + res.get());
        } else {
            System.out.println("No item found with title: " + title);
        }
    }

    private static void exportReportMenu() {
        service.exportReport("library_report.txt");
        System.out.println("Report exported to library_report.txt");
    }
}
