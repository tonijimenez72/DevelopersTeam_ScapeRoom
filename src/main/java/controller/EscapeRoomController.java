package controller;

import exception.GlobalExceptionHandler;
import model.EscapeRoom;
import service.EscapeRoomService;

import java.util.List;

public class EscapeRoomController {
    private final EscapeRoomService escapeRoomService;

    public EscapeRoomController(EscapeRoomService escapeRoomService) {
        this.escapeRoomService = escapeRoomService;
    }

    public void add(String name) {
        try {
            escapeRoomService.create(name);
            System.out.println("Room Escape added successfully.");
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void showAll() {
        try {
            List<EscapeRoom> escapeRooms = escapeRoomService.getAll();
            if (escapeRooms.isEmpty()) {
                System.out.println("Please, create an Escape Room first.");
            } else {
                escapeRooms.forEach(System.out::println);
            }
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }
}
