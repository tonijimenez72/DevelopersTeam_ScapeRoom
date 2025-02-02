package menu;

import controller.ClueController;
import enums.Theme;
import model.Clue;
import utils.InputValidation;

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
        int choice = -1;

        while (true) {
            showMenu();
            choice = InputValidation.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createClue();
                case 2 -> readClue();
                case 3 -> readAllClues();
                case 4 -> readAllAvailableClues();
                case 5 -> updateClueStatus();
                case 6 -> deleteClue();
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createClue() {
        String name = InputValidation.validateStringInput("Enter name: ");
        double price = InputValidation.validatePriceInput("Enter price: ");
        String themeInput = InputValidation.validateStringInput("Enter theme (MISTERY, FANTASY, CIFI): ");
        Theme theme;
        try {
            theme = Theme.valueOf(themeInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid theme. Please try again.");
            return;
        }

        Clue clue = new Clue(name, price, theme);
        clueController.addClue(clue);
    }

    private void readClue() {
        int id = InputValidation.validateIntInput("Enter id: ");
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

    private void updateClueStatus() {
        int id = InputValidation.validateIntInput("Enter id: ");
        boolean newStatus = InputValidation.validateStringInput("Enter new status (true/false): ").equalsIgnoreCase("true");

        try {
            clueController.updateClueStatus(id, newStatus);
            System.out.println("Status updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
    }

    private void deleteClue() {
        int id = InputValidation.validateIntInput("Enter id: ");
        try {
            clueController.removeClue(id);
            System.out.println("Clue removed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting clue: " + e.getMessage());
        }
    }
}
