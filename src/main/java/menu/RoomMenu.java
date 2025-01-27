package menu;

import controller.RoomController;
import enums.DifficultyLevel;
import enums.Theme;
import model.Room;

import java.util.Scanner;

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
                6. Update room availability
                7. Remove clue from room
                8. Remove decoration from room
                9. Delete room
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
                case 1 -> createRoom(scanner);
                case 2 -> addClueToRoom(scanner);
                case 3 -> addDecorationToRoom(scanner);
                case 4 -> showRoomTotalPrice(scanner);
                case 5 -> showAllRooms();
                case 6 -> updateRoomStatus(scanner);
                case 7 -> removeClueFromRoom(scanner);
                case 8 -> removeDecorationFromRoom(scanner);
                case 9 -> deleteRoom(scanner);
                case 0 -> {return;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createRoom(Scanner scanner) {
        System.out.print("Enter room name: ");
        String name = scanner.nextLine();

        System.out.print("Enter room theme (MISTERY, FANTASY, CIFI): ");
        Theme theme;
        try {
            theme = Theme.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid theme. Room creation canceled.");
            return;
        }

        System.out.print("Enter room difficulty (EASY, MEDIUM, HARD): ");
        DifficultyLevel difficultyLevel;
        try {
            difficultyLevel = DifficultyLevel.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty level. Room creation canceled.");
            return;
        }

        System.out.print("Enter room price: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Room creation canceled.");
            return;
        }

        Room room = new Room(name, theme, difficultyLevel, price);
        roomController.createRoom(room);
    }

    private void addClueToRoom(Scanner scanner) {
        System.out.print("Enter room id: ");
        int roomId = scanner.nextInt();

        System.out.print("Enter clue id: ");
        int clueId = scanner.nextInt();

        roomController.addClueToRoom(roomId, clueId);
    }

    private void addDecorationToRoom(Scanner scanner) {
        System.out.print("Enter room id: ");
        int roomId = scanner.nextInt();

        System.out.print("Enter decoration id: ");
        int decorationId = scanner.nextInt();

        roomController.addDecorationToRoom(roomId, decorationId);
    }

    private void showRoomTotalPrice(Scanner scanner) {
        System.out.print("Enter room id: ");
        int roomId = scanner.nextInt();
        roomController.showRoomTotalPrice(roomId);
    }

    private void showAllRooms(){
        roomController.showAllRooms();
    }

    private void showAllAvailableRooms(){
        roomController.showAllAvailableRooms();
    }

    private void updateRoomStatus(Scanner scanner) {
        System.out.print("Enter room id: ");
        int id = scanner.nextInt();

        System.out.print("Enter new status (true/false): ");
        boolean available;
        try {
            available = Boolean.parseBoolean(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input. Room status update canceled.");
            return;
        }

        roomController.updateRoomStatus(id, available);
    }

    private void removeClueFromRoom(Scanner scanner) {
        System.out.print("Enter room id: ");
        int roomId = scanner.nextInt();

        System.out.print("Enter clue id: ");
        int clueId = scanner.nextInt();

        roomController.removeClueFromRoom(roomId, clueId);
    }

    private void removeDecorationFromRoom(Scanner scanner) {
        System.out.print("Enter room id: ");
        int roomId = scanner.nextInt();

        System.out.print("Enter decoration name: ");
        int decorationId = scanner.nextInt();

        roomController.removeDecorationFromRoom(roomId, decorationId);
    }

    private void deleteRoom(Scanner scanner) {
        System.out.print("Enter room id: ");
        int roomId = scanner.nextInt();
        roomController.deleteRoom(roomId);
    }

    public void showTotalSalesAmount(){
        roomController.calculateTotalSalesAmount();
    }
}