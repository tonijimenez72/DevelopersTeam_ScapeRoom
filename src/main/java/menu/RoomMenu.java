package menu;

import controller.RoomController;
import enums.DifficultyLevel;
import enums.Theme;
import model.Room;
import utils.InputValidation;

public class RoomMenu {
    private final RoomController roomController;

    public RoomMenu(RoomController roomController) {
        this.roomController = roomController;
    }

    public void showMenu() {
        String menu = """
                \nRoom Menu
                1. Create room
                2. Add clues to room
                3. Add decoration to room
                4. Show room total price
                5. Show all rooms
                6. Show all available rooms
                7. Update room availability
                8. Remove clue from room
                9. Remove decoration from room
                10. Delete room
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
                case 1 -> createRoom();
                case 2 -> addClueToRoom();
                case 3 -> addDecorationToRoom();
                case 4 -> showRoomTotalPrice();
                case 5 -> showAllRooms();
                case 6 -> showAllAvailableRooms();
                case 7 -> updateRoomStatus();
                case 8 -> removeClueFromRoom();
                case 9 -> removeDecorationFromRoom();
                case 10 -> deleteRoom();
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createRoom() {
        String name = InputValidation.validateStringInput("Enter room name: ");

        String themeInput = InputValidation.validateStringInput("Enter room theme (MISTERY, FANTASY, CIFI): ");
        Theme theme;
        try {
            theme = Theme.valueOf(themeInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid theme. Room creation canceled.");
            return;
        }

        String difficultyInput = InputValidation.validateStringInput("Enter room difficulty (EASY, MEDIUM, HARD): ");
        DifficultyLevel difficultyLevel;
        try {
            difficultyLevel = DifficultyLevel.valueOf(difficultyInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty level. Room creation canceled.");
            return;
        }

        double price = InputValidation.validatePriceInput("Enter room price: ");

        Room room = new Room(name, theme, difficultyLevel, price);
        roomController.createRoom(room);
    }

    private void addClueToRoom() {
        int roomId = InputValidation.validateIntInput("Enter room id: ");
        int clueId = InputValidation.validateIntInput("Enter clue id: ");
        roomController.addClueToRoom(roomId, clueId);
    }

    private void addDecorationToRoom() {
        int roomId = InputValidation.validateIntInput("Enter room id: ");
        int decorationId = InputValidation.validateIntInput("Enter decoration id: ");
        roomController.addDecorationToRoom(roomId, decorationId);
    }

    private void showRoomTotalPrice() {
        int roomId = InputValidation.validateIntInput("Enter room id: ");
        roomController.showRoomTotalPrice(roomId);
    }

    private void showAllRooms() {
        roomController.showAllRooms();
    }

    private void showAllAvailableRooms() {
        roomController.showAllAvailableRooms();
    }

    private void updateRoomStatus() {
        int id = InputValidation.validateIntInput("Enter room id: ");
        boolean available = Boolean.parseBoolean(InputValidation.validateStringInput("Enter new status (true/false): "));

        roomController.updateRoomStatus(id, available);
    }

    private void removeClueFromRoom() {
        int roomId = InputValidation.validateIntInput("Enter room id: ");
        int clueId = InputValidation.validateIntInput("Enter clue id: ");
        roomController.removeClueFromRoom(roomId, clueId);
    }

    private void removeDecorationFromRoom() {
        int roomId = InputValidation.validateIntInput("Enter room id: ");
        int decorationId = InputValidation.validateIntInput("Enter decoration id: ");
        roomController.removeDecorationFromRoom(roomId, decorationId);
    }

    private void deleteRoom() {
        int roomId = InputValidation.validateIntInput("Enter room id: ");
        roomController.deleteRoom(roomId);
    }

    public void showTotalSalesAmount() {
        roomController.calculateTotalSalesAmount();
    }
}
