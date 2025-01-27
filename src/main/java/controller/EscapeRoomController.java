package controller;

import model.EscapeRoom;
import service.EscapeRoomService;

import java.util.List;

public class EscapeRoomController {
    private final EscapeRoomService escapeRoomService;

    public EscapeRoomController(EscapeRoomService escapeRoomService) {
        this.escapeRoomService = escapeRoomService;
    }

    public void createEscapeRoom(EscapeRoom escapeRoom) {
        try {
            escapeRoomService.addEscapeRoom(escapeRoom);
            System.out.println("Escape Room created successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public EscapeRoom getEscapeRoomById(int id) {
        EscapeRoom escapeRoom = escapeRoomService.getEscapeRoomById(id);
        if (escapeRoom == null) {
            System.out.println("Escape Room with ID " + id + " not found.");
        }
        return escapeRoom;
    }

    public List<EscapeRoom> getAllEscapeRooms() {
        List<EscapeRoom> escapeRooms = escapeRoomService.getAllEscapeRooms();
        if (escapeRooms.isEmpty()) {
            System.out.println("No Escape Rooms available.");
        }
        return escapeRooms;
    }

    public void updateEscapeRoom(EscapeRoom escapeRoom) {
        EscapeRoom existingEscapeRoom = escapeRoomService.getEscapeRoomById(escapeRoom.getId());
        if (existingEscapeRoom == null) {
            System.out.println("Escape Room with ID " + escapeRoom.getId() + " not found.");
            return;
        }
        escapeRoomService.updateEscapeRoom(escapeRoom);
        System.out.println("Escape Room updated successfully.");
    }

    public void deleteEscapeRoom(int id) {
        EscapeRoom existingEscapeRoom = escapeRoomService.getEscapeRoomById(id);
        if (existingEscapeRoom == null) {
            System.out.println("Escape Room with ID " + id + " not found.");
            return;
        }
        escapeRoomService.deleteEscapeRoom(id);
        System.out.println("Escape Room deleted successfully.");
    }

    public boolean escapeRoomExists() {
        return escapeRoomService.escapeRoomExists();
    }
}
