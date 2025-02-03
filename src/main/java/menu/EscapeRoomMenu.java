package menu;

import controller.EscapeRoomController;
import model.EscapeRoom;
import utils.InputValidation;

import java.util.Scanner;

public class EscapeRoomMenu {
    private final EscapeRoomController escapeRoomController;

    public EscapeRoomMenu(EscapeRoomController escapeRoomController) {
        this.escapeRoomController = escapeRoomController;
    }

    public void showMenu() {
        String menu = """
                \nEscape Room Menu
                1. Create escape room
                2. Show all escape rooms
                0. Back to Main Menu
                """;
        System.out.print(menu);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            showMenu();
            choice = InputValidation.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> create();
                case 2 -> showAll();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void create() {
        String name = InputValidation.validateStringInput("Enter name: ");
        escapeRoomController.add(name);
    }

    private void showAll() {
        escapeRoomController.showAll();
    }

}
