package menu;

import controller.RoomPlayerController;
import utils.InputValidation;

public class RoomPlayerMenu {
    private final RoomPlayerController roomPlayerController;

    public RoomPlayerMenu(RoomPlayerController roomPlayerController) {
        this.roomPlayerController = roomPlayerController;
    }

    public void showMenu() {
        String menu = """
                \nSession Menu
                1. Create session
                2. End session
                3. Show players by room
                4. Show rooms by player
                5. Show rooms solved by player
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
                case 1 -> addPlayerToRoom();
                case 2 -> endRoomSession();
                case 3 -> showPlayersByRoom();
                case 4 -> showRoomsByPlayer();
                case 5 -> showRoomsSolvedByPlayer();
                case 0 -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addPlayerToRoom() {
        int playerId = InputValidation.validateIntInput("Enter player ID: ");
        int roomId = InputValidation.validateIntInput("Enter room ID: ");
        roomPlayerController.addPlayerToRoom(playerId, roomId);
    }

    private void showPlayersByRoom() {
        int roomId = InputValidation.validateIntInput("Enter room ID: ");
        roomPlayerController.showPlayersByRoom(roomId);
    }

    private void showRoomsByPlayer() {
        int playerId = InputValidation.validateIntInput("Enter player ID: ");
        roomPlayerController.showRoomsPlayedByPlayer(playerId);
    }

    private void showRoomsSolvedByPlayer() {
        int playerId = InputValidation.validateIntInput("Enter player ID: ");
        roomPlayerController.showRoomsSolvedByPlayer(playerId);
    }

    private void endRoomSession() {
        int playerId = InputValidation.validateIntInput("Enter player ID: ");
        int roomId = InputValidation.validateIntInput("Enter room ID: ");
        String response = InputValidation.validateStringInput("Room is solved? (true/false): ");
        boolean isSolved = Boolean.parseBoolean(response);

        roomPlayerController.endRoomSession(playerId, roomId, isSolved);
    }
}