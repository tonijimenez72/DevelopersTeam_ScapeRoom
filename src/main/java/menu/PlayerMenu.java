package menu;

import controller.PlayerController;
import model.Player;
import utils.InputValidation;

public class PlayerMenu {
    private final PlayerController playerController;

    public PlayerMenu(PlayerController playerController) {
        this.playerController = playerController;
    }

    public void showMenu() {
        String menu = """
                \nPlayer Menu
                1. Create player
                2. Show player
                3. Show all players
                4. Add subscription
                5. Show all subscribers
                6. Delete subscription
                7. Send Notification
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
                case 1 -> createPlayer();
                case 2 -> showPlayer();
                case 3 -> showAllPlayers();
                case 4 -> addSubscription();
                case 5 -> showAllSubscribers();
                case 6 -> deleteSubscription();
                case 7 -> sendNotification();
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createPlayer() {
        String name = InputValidation.validateStringInput("Enter player name: ");
        String email = InputValidation.validateEmailInput("Enter player email: ");

        playerController.add(name, email);
    }

    private void showPlayer() {
        int id = InputValidation.validateIntInput("Enter ID: ");

        playerController.getById(id);

    }

    private void showAllPlayers() {
        playerController.showAll();
    }
  
    private void addSubscription() {
        playerController.showAll();

        int id = InputValidation.validateIntInput("Enter ID: ");
        playerController.addSubscription(id);
    }

    private void showAllSubscribers() {
        playerController.showAllSubscribers();
    }

    private void deleteSubscription() {
        playerController.showAllSubscribers();

        int id = InputValidation.validateIntInput("Enter ID: ");

        playerController.deleteSubscription(id);
    }

    private void sendNotification(){
        String message = InputValidation.validateStringInput("Enter message: ");

        playerController.sendNotification(message);
    }
}