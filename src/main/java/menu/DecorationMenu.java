package menu;

import controller.DecorationController;
import controller.RoomController;
import utils.InputValidation;
import model.Decoration;

import java.util.Scanner;

public class DecorationMenu {
    private final DecorationController decorationController;
    private final RoomController roomController;

    public DecorationMenu(DecorationController decorationController, RoomController roomController) {
        this.decorationController = decorationController;
        this.roomController = roomController;
    }

    public void showMenu() {
        String menu = """
                \nDecoration Menu
                1. Create decoration
                2. Show decoration
                3. Show all decorations
                4. Delete decoration
                0. Back to Main Menu
                """;
        System.out.print(menu);
    }

    public void run() {
        int choice;

        while (true) {
            showMenu();

            choice = InputValidation.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> create();
                case 2 -> read();
                case 3 -> readAll();
                case 4 -> delete();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void create() {
        String name = InputValidation.validateStringInput("Enter name: ");
        double price = InputValidation.validatePriceInput("Enter price: ");
        String material = InputValidation.validateStringInput("Enter material: ");

        roomController.showAll();

        int roomId = InputValidation.validateIdInput("Enter room ID: ");

        decorationController.add(name, price, material, roomId);
    }

    private void read() {
        int id = InputValidation.validateIntInput("Enter decoration id: ");

        decorationController.getById(id);

    }

    private void readAll() {
        decorationController.showAll();
    }

    private void delete() {
        int id = InputValidation.validateIntInput("Enter id: ");

        decorationController.delete(id);
    }
}