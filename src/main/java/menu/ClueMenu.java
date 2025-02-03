package menu;

import controller.ClueController;
import controller.RoomController;
import utils.InputValidation;
import enums.Theme;
import model.Clue;

import java.util.Scanner;

public class ClueMenu {
    private final ClueController clueController;
    private final RoomController roomController;

    public ClueMenu(ClueController clueController, RoomController roomController) {
        this.clueController = clueController;
        this.roomController = roomController;
    }

    public void showMenu() {
        String menu = """
                \nClue Menu
                1. Create clue
                2. Show clue
                3. Show all clues
                4. Delete clue
                0. Back to Main Menu
                """;
        System.out.print(menu);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

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
                case 1 -> create();
                case 2 -> read();
                case 3 -> readAll();
                case 4 -> delete();
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void create() {
        String name = InputValidation.validateStringInput("Enter name: ");
        double price = InputValidation.validatePriceInput("Enter price: ");
        String themeInput = InputValidation.validateStringInput("Enter theme (MISTERY, FANTASY, CIFI): ");
        int roomId = InputValidation.validateIdInput("Enter room ID: ");

        Theme theme;

        try {
            theme = Theme.valueOf(themeInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid theme. Please try again.");
            return;
        }

        clueController.add(name, price, theme, roomId);
    }

    private void read() {
        int id = InputValidation.validateIntInput("Enter id: ");
        Clue clue = clueController.getById(id);
        if (clue == null) {
            System.out.println("Clue not found.");
        }
    }

    private void readAll() {
        clueController.showAll();
    }

    private void delete() {
        int id = InputValidation.validateIntInput("Enter id: ");
        try {
            clueController.delete(id);
            System.out.println("Clue removed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting clue: " + e.getMessage());
        }
    }
}