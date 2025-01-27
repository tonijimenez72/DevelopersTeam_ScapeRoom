package controller;

import model.Clue;
import model.Room;
import service.RoomService;

import java.util.List;

public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void createRoom(Room room) {
        try {
            roomService.createRoom(room);
            System.out.println("Room created successfully: " + room.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating room: " + e.getMessage());
        }
    }

    public void addClueToRoom(int roomId, int clueId) {
        if (roomId <= 0 || clueId <= 0) {
            System.out.println("Room ID and Clue ID must be greater than 0.");
            return;
        }

        try {
            roomService.addClueToRoom(roomId, clueId);
            System.out.println("Clue added to room.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error adding clue to room: " + e.getMessage());
        }
    }


    public void addDecorationToRoom(int roomId, int decorationId) {
        if (roomId <= 0 || decorationId <= 0) {
            System.out.println("IDs must be greater than 0.");
            return;
        }
        try {
            roomService.addDecorationToRoom(roomId, decorationId);
            System.out.println("Decoration added to room.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error adding decoration to room: " + e.getMessage());
        }
    }

    public void showAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("Created rooms: None.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    public void showAllAvailableRooms() {
        List<Room> rooms = roomService.getAvailableRooms();
        int countAvailables = rooms.size();
        if (rooms.isEmpty()) {
            System.out.println("Available rooms: None.");
        } else {
            System.out.printf("Available rooms: %s", countAvailables);
            rooms.forEach(System.out::println);
        }
    }

    public Room getRoomById(int id) {
        try {
            return roomService.getRoomById(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void updateRoomStatus(int id, boolean available) {
        try {
            roomService.updateRoomStatus(id, available);
            System.out.printf("Room status updated to: %s%n", available ? "available" : "not available");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating room status: " + e.getMessage());
        }
    }

    public void removeClueFromRoom(int roomId, int clueId) {
        try {
            roomService.removeClueFromRoom(roomId, clueId);
            System.out.println("Clue removed from room.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing clue from room: " + e.getMessage());
        }
    }

    public void removeDecorationFromRoom(int roomId, int decorationId) {
        try {
            roomService.removeDecorationFromRoom(roomId, decorationId);
            System.out.println("Decoration removed from room.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing decoration from room: " + e.getMessage());
        }
    }

    public void deleteRoom(int id) {
        try {
            roomService.deleteRoom(id);
            System.out.println("Room deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting room: " + e.getMessage());
        }
    }

    public void showRoomTotalPrice(int id) {
        try {
            Room room = roomService.getRoomById(id);
            double totalPrice = roomService.calculateRoomTotalPrice(room);
            System.out.printf("Room total price: %.2f%n", totalPrice);
        } catch (IllegalArgumentException e) {
            System.out.println("Error calculating room total price: " + e.getMessage());
        }
    }

    public void calculateTotalSalesAmount(){
        try {
            double totalAmount = roomService.calculateTotalSalesAmount();
            System.out.printf("Room Escape total sales amount: %.2f%n", totalAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("Error calculating total sales amount: " + e.getMessage());
        }
    }
}