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
                \nRoom Player Menu
                1. Add player session to a room
                2. Cancel player session in room
                3. Show rooms played by a player
                4. Show players who played in a room
                5. End player session in room
                0. Back to Main Menu
                """;
        System.out.print(menu);
    }

    public void run() {
        int choice;
        do {
            showMenu();
            choice = InputValidation.validateIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> addPlayerToRoom();
                case 2 -> removePlayerFromRoom();
                case 3 -> playerPlayedRooms();
                case 4 -> roomPlayedByPlayers();
                case 5 -> playerEndsSessionInRoom();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
    }

    private void addPlayerToRoom() {
        int playerId = InputValidation.validateIdInput("Enter Player ID: ");
        int roomId = InputValidation.validateIdInput("Enter Room ID: ");
        boolean isSolved = false;

        roomPlayerController.addPlayerToRoom(playerId, roomId, isSolved);
    }

    private void removePlayerFromRoom() {
        int playerId = InputValidation.validateIdInput("Enter Player ID: ");
        int roomId = InputValidation.validateIdInput("Enter Room ID: ");
        roomPlayerController.removePlayerFromRoom(playerId, roomId);
    }

    private void playerPlayedRooms() {
        int playerId = InputValidation.validateIdInput("Enter Player ID: ");
        roomPlayerController.playerPlayedRooms(playerId);
    }

    private void roomPlayedByPlayers() {
        int roomId = InputValidation.validateIdInput("Enter Room ID: ");
        roomPlayerController.roomPlayedByPlayers(roomId);
    }

    private void playerEndsSessionInRoom() {
        int playerId = InputValidation.validateIdInput("Enter Player ID: ");
        int roomId = InputValidation.validateIdInput("Enter Room ID: ");
        boolean isSolved = InputValidation.validateBooleanInput("Is the room solved? (true/false): ");
        roomPlayerController.playerSolvedRoom(playerId, roomId, isSolved);
    }
}
