package menu;

import controller.PlayerController;
import model.Player;

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
                case 1 -> createPlayer(scanner);
                case 2 -> addSubscription(scanner);
                case 3 -> showPlayer(scanner);
                case 4 -> showAllPlayers();
                case 5 -> showAllSubscribers();
                case 6 -> deleteSubscription(scanner);
                case 7 -> deletePlayer(scanner);
                case 8 -> sendNotification(scanner);
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void sendNotification(Scanner scanner) {
        System.out.print("Enter notification message: ");
        String message = scanner.nextLine();
        playerController.sendNotification(message);
    }

    private void createPlayer(Scanner scanner) {
        System.out.print("Enter player name: ");
        String name = scanner.nextLine();

        System.out.print("Enter player email: ");
        String email = scanner.nextLine();

        try {
            Player player = new Player(name, email);
            playerController.createPlayer(player);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating player: " + e.getMessage());
        }
    }

    private void addSubscription(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();

        playerController.addSubscription(id);
    }

    private void showPlayer(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();

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

    private void deleteSubscription(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        if (id == -1) return;

        playerController.deleteSubscription(id);
    }

    private void deletePlayer(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        if (id == -1) return;

        playerController.deletePlayer(id);
    }
}
