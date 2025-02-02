package menu;

import controller.DecorationController;
import model.Decoration;
import utils.InputValidation;

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
        int choice = -1;

        while (true) {
            showMenu();
            choice = InputValidation.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createDecoration();
                case 2 -> readDecoration();
                case 3 -> showAllDecorations();
                case 4 -> showAllAvailableDecorations();
                case 5 -> updateDecorationStatus();
                case 6 -> deleteDecoration();
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createDecoration() {
        String name = InputValidation.validateStringInput("Enter name: ");
        double price = InputValidation.validatePriceInput("Enter price: ");
        String material = InputValidation.validateStringInput("Enter material: ");

        Decoration decoration = new Decoration(name, price, material);
        decorationController.addDecoration(decoration);
    }

    private void readDecoration() {
        int id = InputValidation.validateIntInput("Enter decoration id: ");
        Decoration decoration = decorationController.getDecorationById(id);
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

    private void updateDecorationStatus() {
        int id = InputValidation.validateIntInput("Enter id: ");
        boolean newStatus = InputValidation.validateStringInput("Enter new status (true/false): ").equalsIgnoreCase("true");

        try {
            decorationController.updateDecorationStatus(id, newStatus);
            System.out.println("Status updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
    }

    private void deleteDecoration() {
        int id = InputValidation.validateIntInput("Enter id: ");
        try {
            decorationController.removeDecoration(id);
            System.out.println("Decoration removed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting decoration: " + e.getMessage());
        }
    }
}