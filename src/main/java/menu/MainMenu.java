package menu;

import controller.*;
import utils.InputValidation;

import java.util.Scanner;

public class MainMenu {

    private final ClueMenu clueMenu;
    private final DecorationMenu decorationMenu;
    private final RoomMenu roomMenu;
    private final PlayerMenu playerMenu;
    private final RoomPlayerMenu roomPlayerMenu;
    private final Scanner scanner;

    public MainMenu(ClueController clueController, DecorationController decorationController, RoomController roomController, PlayerController playerController, RoomPlayerController roomPlayerController) {
        this.clueMenu = new ClueMenu(clueController);
        this.decorationMenu = new DecorationMenu(decorationController);
        this.roomMenu = new RoomMenu(roomController);
        this.playerMenu = new PlayerMenu(playerController);
        this.roomPlayerMenu = new RoomPlayerMenu(roomPlayerController);
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        String menu = """
                \nMain Menu
                1. Manage Clues
                2. Manage Decorations
                3. Manage Rooms
                4. Manage Players
                5. Manage Session
                6. Send Notification
                7. Show total sales amount
                0. Exit
                """;
        System.out.print(menu);
    }

    public void run() {
        int choice = -1;

        while (true) {
            showMenu();
            choice = InputValidation.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> clueMenu.run();
                case 2 -> decorationMenu.run();
                case 3 -> roomMenu.run();
                case 4 -> playerMenu.run();
                case 5 -> roomPlayerMenu.run();
                case 6 -> playerMenu.sendNotification();
                case 7 -> roomMenu.showTotalSalesAmount();
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}