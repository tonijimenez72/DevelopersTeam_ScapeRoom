package controller;

import model.Clue;
import model.Decoration;
import model.EscapeRoom;
import model.Room;
import service.EscapeRoomService;

public class EscapeRoomController {
    private final EscapeRoomService escapeRoomService;

    public EscapeRoomController(EscapeRoomService escapeRoomService) {
        this.escapeRoomService = escapeRoomService;
    }

    public void createEscapeRoom(String name) {
        try {
            escapeRoomService.createEscapeRoom(name);
            System.out.println("Escape Room successfully added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding escape room: " + e.getMessage());
        }
    }


    public void showEscapeRoomInventory(boolean onlyAvailable) {
        System.out.println(escapeRoomService.displayEscapeRoomInventory(onlyAvailable));
    }

    public void addRoom(Room room) {
        try {
            escapeRoomService.addRoom(room);
            System.out.println("Room successfully added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }

    public void addClue(Clue clue) {
        try {
            escapeRoomService.addClue(clue);
            System.out.println("Clue successfully added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding clue: " + e.getMessage());
        }
    }

    public void addDecoration(Decoration decoration) {
        try {
            escapeRoomService.addDecoration(decoration);
            System.out.println("Decoration successfully added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding decoration: " + e.getMessage());
        }
    }

    public void showInventoryValue() {
        double value = escapeRoomService.calculateInventoryValue();
        System.out.printf("Total inventory value: %.2f euros\n", value);
    }
}