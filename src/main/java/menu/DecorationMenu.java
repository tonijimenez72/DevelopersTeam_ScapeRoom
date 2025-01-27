package menu;

import controller.DecorationController;
import model.Decoration;

import java.util.Scanner;

public class DecorationMenu {
    private final DecorationController decorationController;

    public DecorationMenu(DecorationController decorationController) {
        this.decorationController = decorationController;
    }

    public void showMenu() {
        String menu = """
                \nDecoration Menu
                1. Create decoration
                2. Show decoration
                3. Show all decorations
                4. Show all available decorations
                5. Update decoration status
                6. Delete decoration
                0. Back to Main Menu
                """;
        System.out.print(menu);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            showMenu();
            System.out.print("Enter your choice: ");

            try {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                continue;
            }

            switch (choice) {
                case 1 -> createDecoration(scanner);
                case 2 -> readDecoration(scanner);
                case 3 -> showAllDecorations();
                case 4 -> showAllAvailableDecorations();
                case 5 -> updateDecorationStatus(scanner);
                case 6 -> deleteDecoration(scanner);
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createDecoration(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Please try again.");
            return;
        }

        System.out.print("Enter material: ");
        String material = scanner.nextLine();

        Decoration decoration = new Decoration(name, price, material);
        decorationController.addDecoration(decoration);
    }

    private void readDecoration(Scanner scanner) {
        System.out.print("Enter decoration id: ");
        int id = scanner.nextInt();

        Decoration decoration= decorationController.getDecorationById(id);
        if (decoration != null) {
            System.out.println("Decoration found: " + decoration);
        } else {
            System.out.println("Decoration not found.");
        }
    }

    private void showAllDecorations() {
        decorationController.showAllDecorations();
    }

    private void showAllAvailableDecorations() {
        decorationController.showAllAvailableDecorations();
    }

    private void updateDecorationStatus(Scanner scanner) {
        System.out.print("Enter id: ");
       int id = scanner.nextInt();

        System.out.print("Enter new status (true/false): ");
        boolean newStatus;
        try {
            newStatus = Boolean.parseBoolean(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid status input. Please enter true or false.");
            return;
        }

        try {
            decorationController.updateDecorationStatus(id, newStatus);
            System.out.println("Status updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
    }

    private void deleteDecoration(Scanner scanner) {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();

        try {
            decorationController.removeDecoration(id);
            System.out.println("Decoration removed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting decoration: " + e.getMessage());
        }
    }

}
