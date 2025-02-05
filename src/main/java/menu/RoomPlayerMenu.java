package menu;

import controller.PlayerController;
import controller.RoomController;
import controller.RoomPlayerController;
import service.RoomService;
import service.impl.RoomServiceImpl;
import utils.InputValidation;

public class RoomPlayerMenu {
    private final RoomPlayerController roomPlayerController;

    public RoomPlayerMenu(RoomPlayerController roomPlayerController) {
        this.roomPlayerController = roomPlayerController;
    }

    public void showMenu() {
        String menu = """
                \nSession Menu
                1. Add player session to a room
                2. Cancel player session in room
                3. End player session in room
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
                case 1 -> addPlayerToRoom();
                case 2 -> removePlayerFromRoom();
                case 3 -> playerEndsSessionInRoom();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
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

    private void playerEndsSessionInRoom() {
        int playerId = InputValidation.validateIdInput("Enter Player ID: ");
        int roomId = InputValidation.validateIdInput("Enter Room ID: ");
        boolean isSolved = InputValidation.validateBooleanInput("Is the room solved? (true/false): ");

        roomPlayerController.playerSolvedRoom(playerId, roomId, isSolved);
    }
}
