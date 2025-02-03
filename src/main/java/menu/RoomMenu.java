package menu;

import controller.RoomController;
import enums.*;
import model.Room;
import utils.InputValidation;

import java.util.Scanner;

public class RoomMenu {
    private final RoomController roomController;

    public RoomMenu(RoomController roomController) {
        this.roomController = roomController;
    }

    public void showMenu() {
        String menu = """
                \nRoom Menu
                1. Create room
                2. Show room
                3. Show all rooms
                6. Delete room
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
                case 1 -> create(scanner);
                case 2 -> read(scanner);
                case 3 -> readAll();
                case 6 -> delete(scanner);
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void create(Scanner scanner) {
        String name = InputValidation.validateStringInput("Enter room name: ");
        double price = InputValidation.validatePriceInput("Enter room price: ");

        String themeInput = InputValidation.validateStringInput("Enter room theme (MISTERY, FANTASY, CIFI): ");
        Theme theme;
        try {
            theme = Theme.valueOf(themeInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid theme. Room creation canceled.");
            return;
        }

        String difficultyInput = InputValidation.validateStringInput("Enter room difficulty (EASY, MEDIUM, HARD): ");
        DifficultyLevel difficultyLevel;
        try {
            difficultyLevel = DifficultyLevel.valueOf(difficultyInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty level. Room creation canceled.");
            return;
        }

        roomController.add(name, price, theme, difficultyLevel);
    }

    private void read(Scanner scanner) {
        int id = InputValidation.validateIntInput("Enter room id: ");

        Room room = roomController.getById(id);

        if (room == null) {
            System.out.println("Clue not found.");
        }
    }

    private void readAll() {
        roomController.showAll();
    }

    private void delete(Scanner scanner) {
        int id = InputValidation.validateIntInput("Enter room id: ");

        roomController.delete(id);
    }

    public void showTotalSalesAmount() {
        roomController.showStockInfo();
    }
}