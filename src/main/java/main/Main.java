package main;

import controller.EscapeRoomController;
import controller.RoomController;
import model.Clue;
import model.Decoration;
import model.Room;
import enums.*;
import service.EscapeRoomService;
import service.EscapeRoomServiceImpl;
import service.RoomService;
import service.RoomServiceImpl;

public class Main {
    public static void main(String[] args) {
        EscapeRoomService escapeRoomService = new EscapeRoomServiceImpl();
        RoomService roomService = new RoomServiceImpl();

        EscapeRoomController escapeRoomController = new EscapeRoomController(escapeRoomService);
        RoomController roomController = new RoomController(roomService);


        Clue clue1 = new Clue("The last letter received", 20,Theme.MISTERY);
        Clue clue2 = new Clue("The last letter received", 20,Theme.MISTERY);
        Decoration deco1 = new Decoration("Red lamp", 10, "lamp");
        Decoration deco2 = new Decoration("Blue lamp", 10, "lamp");
        Room room1 = new Room("The Old Library of Arkham", Theme.MISTERY, DifficultyLevel.MEDIUM, 50.0);
        Room room2 = new Room("The New Library of Arkham", Theme.MISTERY, DifficultyLevel.MEDIUM, 50.0);


        escapeRoomController.createEscapeRoom("Escape from IT Academy");
        escapeRoomController.createEscapeRoom("Escape from IT Academy II");

        escapeRoomController.showEscapeRoomInventory(true);
        escapeRoomController.showInventoryValue();

        escapeRoomController.addClue(clue1);
        escapeRoomController.addClue(clue2);

        escapeRoomController.addDecoration(deco1);
        escapeRoomController.addDecoration(deco2);

        escapeRoomController.addRoom(room1);
        escapeRoomController.addRoom(room2);

        escapeRoomController.showEscapeRoomInventory(true);
        escapeRoomController.showInventoryValue();

        roomController.addClueToRoom(room1, clue1);
        roomController.addDecorationToRoom(room1, deco2);
        roomController.addClueToRoom(room1, clue2);
        roomController.addDecorationToRoom(room1, deco1);

        roomController.addDecorationToRoom(room2, deco1);

        escapeRoomController.showEscapeRoomInventory(true);
        escapeRoomController.showInventoryValue();

        System.out.println(room1);
        System.out.println(room2);

    }
}