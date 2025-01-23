package controller;

import model.Clue;
import model.Decoration;
import model.Room;
import service.RoomService;

public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void addRoom(Room room) {
        try {
            roomService.addRoom(room);
        } catch (Exception e) {
            System.out.println("Failed to add room: " + e.getMessage());
        }
    }

    public void addClueToRoom(Room room, Clue clue) {
        try {
            roomService.addClueToRoom(room, clue);
        } catch (Exception e) {
            System.out.println("Failed to add clue to room: " + e.getMessage());
        }
    }

    public void addDecorationToRoom(Room room, Decoration decoration) {
        try {
            roomService.addDecorationToRoom(room, decoration);
        } catch (Exception e) {
            System.out.println("Failed to add decoration to room: " + e.getMessage());
        }
    }

    public void showAllRooms() {
        roomService.getAllRooms().forEach(System.out::println);
    }
}