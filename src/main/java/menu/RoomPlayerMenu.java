package menu;

import controller.RoomPlayerController;

import java.util.Scanner;

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
        int choice= -1;
        Scanner scanner = new Scanner(System.in);

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
                case 1 -> addPlayerToRoom(scanner);
                case 2 -> endRoomSession(scanner);
                case 3 -> showPlayersByRoom(scanner);
                case 4 -> showRoomsByPlayer(scanner);
                case 5 -> showRoomsSolvedByPlayer(scanner);
                case 0 -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addPlayerToRoom(Scanner scanner) {
        try {
            System.out.print("Enter player ID: ");
            int playerId = scanner.nextInt();
            System.out.print("Enter room ID: ");
            int roomId = scanner.nextInt();
            roomPlayerController.addPlayerToRoom(playerId, roomId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numeric IDs.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showPlayersByRoom(Scanner scanner) {
        try {
            System.out.print("Enter room ID: ");
            int roomId = scanner.nextInt();
            roomPlayerController.showPlayersByRoom(roomId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid room ID.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showRoomsByPlayer(Scanner scanner) {
        try {
            System.out.print("Enter player ID: ");
            int playerId = scanner.nextInt();
            roomPlayerController.showRoomsPlayedByPlayer(playerId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid player ID.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showRoomsSolvedByPlayer(Scanner scanner) {
        try {
            System.out.print("Enter player ID: ");
            int playerId = scanner.nextInt();
            roomPlayerController.showRoomsSolvedByPlayer(playerId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid player ID.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void endRoomSession(Scanner scanner) {
        String response;
        boolean isSolved;

        try {
            System.out.print("Enter player ID: ");
            int playerId = scanner.nextInt();
            System.out.print("Enter room ID: ");
            int roomId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Room is solved? (true/false): ");
            response = scanner.nextLine().toLowerCase();
            isSolved = Boolean.parseBoolean(response);

            roomPlayerController.endRoomSession(playerId, roomId, isSolved);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numeric IDs or a boolean value.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}