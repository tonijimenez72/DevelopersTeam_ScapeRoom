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

    public void createEscapeRoom(String name, int id ) {
        try {
            escapeRoomService.createEscapeRoom(name,id);
            System.out.println("Escape Room successfully added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding escape room: " + e.getMessage());
        }
    }


    public void showEscapeRoomInventory(boolean onlyAvailable) {
        System.out.println(escapeRoomService.displayEscapeRoomInventory(onlyAvailable));
    }

    public void showInventoryValue() {
        double value = escapeRoomService.calculateInventoryValue();
        System.out.printf("Total inventory value: %.2f euros\n", value);
    }
}