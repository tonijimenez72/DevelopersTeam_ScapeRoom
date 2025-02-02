package menu;

import controller.PlayerController;
import model.Player;
import utils.InputValidation;

import java.util.Scanner;

public class PlayerMenu {
    private final PlayerController playerController;

    public PlayerMenu(PlayerController playerController) {
        this.playerController = playerController;
    }

    public void showMenu() {
        String menu = """
                \nPlayer Menu
                1. Create player
                2. Add subscription
                3. Show player
                4. Show all players
                5. Show all subscribers
                6. Delete subscription
                7. Delete player
                8. Send Notification
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
                case 1 -> createPlayer();
                case 2 -> addSubscription();
                case 3 -> showPlayer();
                case 4 -> showAllPlayers();
                case 5 -> showAllSubscribers();
                case 6 -> deleteSubscription();
                case 7 -> deletePlayer();
                case 8 -> sendNotification();
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void sendNotification() {
        String message = InputValidation.validateStringInput("Enter notification message: ");
        playerController.sendNotification(message);
    }

    private void createPlayer() {
        String name = InputValidation.validateStringInput("Enter player name: ");
        String email = InputValidation.validateEmailInput("Enter player email: ");

        try {
            Player player = new Player(name, email);
            playerController.createPlayer(player);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating player: " + e.getMessage());
        }
    }

    private void addSubscription() {
        int id = InputValidation.validateIntInput("Enter ID: ");
        playerController.addSubscription(id);
    }

    private void showPlayer() {
        int id = InputValidation.validateIntInput("Enter ID: ");
        try {
            Player player = playerController.getPlayerById(id);
            System.out.println(player);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showAllPlayers() {
        playerController.showAllPlayers();
    }

    private void showAllSubscribers() {
        playerController.showAllSubscribers();
    }

    private void deleteSubscription() {
        int id = InputValidation.validateIntInput("Enter ID: ");
        if (id == -1) return;

        playerController.deleteSubscription(id);
    }

    private void deletePlayer() {
        int id = InputValidation.validateIntInput("Enter ID: ");
        if (id == -1) return;

        playerController.deletePlayer(id);
    }
}