package menu;

import controller.ClueController;
import enums.Theme;
import model.Clue;

import java.util.Scanner;

public class ClueMenu {
    private final ClueController clueController;

    public ClueMenu(ClueController clueController) {
        this.clueController = clueController;
    }

    public void showMenu() {
        String menu = """
                \nClue Menu
                1. Create clue
                2. Show clue
                3. Show all clues
                4. Show all available clues
                5. Update clue status
                6. Delete clue
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
                case 1 -> createClue(scanner);
                case 2 -> readClue(scanner);
                case 3 -> readAllClues();
                case 4 -> readAllAvailableClues();
                case 5 -> updateClueStatus(scanner);
                case 6 -> deleteClue(scanner);
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createClue(Scanner scanner) {
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

        System.out.print("Enter theme (MISTERY, FANTASY, CIFI): ");
        String themeInput = scanner.nextLine().toUpperCase();
        Theme theme;
        try {
            theme = Theme.valueOf(themeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid theme. Please try again.");
            return;
        }

        Clue clue = new Clue(name, price, theme);
        clueController.addClue(clue);
    }

    private void readClue(Scanner scanner) {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();

        Clue clue = clueController.getClueById(id);
        if (clue != null) {
            System.out.println("Clue found: " + clue);
        } else {
            System.out.println("Clue not found.");
        }
    }

    private void readAllClues() {
        clueController.showAllClues();
    }

    private void readAllAvailableClues() {
        clueController.showAllAvailableClues();
    }

    private void updateClueStatus(Scanner scanner) {
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
            clueController.updateClueStatus(id, newStatus);
            System.out.println("Status updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
    }

    private void deleteClue(Scanner scanner) {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();

        try {
            clueController.removeClue(id);
            System.out.println("Clue removed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting clue: " + e.getMessage());
        }
    }
}