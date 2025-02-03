package menu;

import controller.*;
import utils.InputValidation;

import java.util.Scanner;

public class MainMenu {
    private final EscapeRoomMenu escapeRoomMenu;
    private final ClueMenu clueMenu;
    private final DecorationMenu decorationMenu;
    private final RoomMenu roomMenu;
    private final PlayerMenu playerMenu;
    private final RoomPlayerMenu roomPlayerMenu;
    private final TicketController ticketController;

    public MainMenu(EscapeRoomController escapeRoomController, ClueController clueController,
                    DecorationController decorationController, RoomController roomController,
                    PlayerController playerController, RoomPlayerController roomPlayerController,
                    TicketController ticketController) {
        this.escapeRoomMenu = new EscapeRoomMenu(escapeRoomController);
        this.clueMenu = new ClueMenu(clueController, roomController);
        this.decorationMenu = new DecorationMenu(decorationController, roomController);
        this.roomMenu = new RoomMenu(roomController);
        this.playerMenu = new PlayerMenu(playerController);
        this.roomPlayerMenu = new RoomPlayerMenu(roomPlayerController);
        this.ticketController = ticketController;
    }

    public void showMenu() {
        String menu = """
                \nMain Menu
                1. Manage Escape Room
                2. Manage Rooms
                3. Manage Clues
                4. Manage Decorations
                5. Manage Players
                6. Manage Session
                7. Show total stock info
                8. Show total income info
                0. Exit
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
                case 1 -> escapeRoomMenu.run();
                case 2 -> roomMenu.run();
                case 3 -> clueMenu.run();
                case 4 -> decorationMenu.run();
                case 5 -> playerMenu.run();
                case 6 -> roomPlayerMenu.run();
                case 7 -> roomMenu.showTotalSalesAmount();
                case 8 -> ticketController.showTotalIncome();
                case 0 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
