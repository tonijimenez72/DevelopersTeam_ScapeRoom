package main;

import controller.*;
import model.Clue;
import model.Decoration;
import model.Player;
import model.Room;
import enums.*;
import service.*;
import service.impl.*;
import service.impl.RoomServiceImpl;

public class Main {
    public static void main(String[] args) {
        EscapeRoomService escapeRoomService = new EscapeRoomServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        ClueService clueService = new ClueServiceImpl();
        DecorationService decorationService = new DecorationServiceImpl();
        PlayerService playerService = new PlayerServiceImpl();
        RoomPlayerService roomPlayerService = new RoomPlayerServiceImpl();
        NotificationService notificationService = new NotificationServiceImpl();

        EscapeRoomController escapeRoomController = new EscapeRoomController(escapeRoomService);
        RoomController roomController = new RoomController(roomService);
        ClueController clueController = new ClueController(clueService);
        DecorationController decorationController = new DecorationController(decorationService);
        PlayerController playerController = new PlayerController(playerService);
        RoomPlayerController roomPlayerController = new RoomPlayerController(roomPlayerService);
        NotificationController notificationController = new NotificationController(notificationService);

        System.out.println("Manage Escape Room");
        escapeRoomController.createEscapeRoom("Escape from IT Academy");

        System.out.println("\nManage clues");
        Clue clue1 = new Clue("The last letter received", 20, Theme.MISTERY);
        Clue clue2 = new Clue("The portrait", 20, Theme.MISTERY);

        clueController.addClue(clue1);
        clueController.addClue(clue2);


        System.out.println("\nManage Decorations");
        Decoration deco1 = new Decoration("Red lamp", 10, "lamp");
        Decoration deco2 = new Decoration("Blue lamp", 10, "lamp");

        decorationController.addDecoration(deco1);
        decorationController.addDecoration(deco2);

        System.out.println("\nManage Rooms");
        Room room1 = new Room("The Old Library of Arkham", Theme.MISTERY, DifficultyLevel.MEDIUM, 50.0);
        Room room2 = new Room("The New Library of Arkham", Theme.MISTERY, DifficultyLevel.MEDIUM, 50.0);

        roomController.addRoom(room1);
        roomController.addRoom(room2);

        roomController.addClueToRoom(room1, clue1);
        roomController.addClueToRoom(room1, clue2);

        roomController.addDecorationToRoom(room1, deco1);
        roomController.addDecorationToRoom(room1, deco2);

        System.out.println("\nManage players");
        Player player1 = new Player("Player One", "readyplayer1@itacademy.cat");
        Player player2 = new Player("Player Two", "readyplayer2@itacademy.cat");

        playerController.addPlayer(player1);
        playerController.addPlayer(player2);

        System.out.println("\nManage notification subscriptions");

        playerController.subscribeToNotifications(player1);
        notificationService.addObserver(player1);

        playerController.subscribeToNotifications(player2);
        notificationService.addObserver(player2);

        playerController.showAllSubscribers();

        notificationService.notifyObservers("New Mistery Room: Escape from IT Academy!!!");

        System.out.println("\nManage Room session");
        roomPlayerController.addPlayerToRoom(player1, room1);
        roomController.updateRoomAvailability(room1,false);

        roomPlayerController.endRoomSession(player1, room1, true);
        roomController.updateRoomAvailability(room1,true);

        roomPlayerController.addPlayerToRoom(player2, room1);

        //player.addPlayedRoom(room);
        //room.addPlayerToRoom(player);
        //room.setAvailable(false);


        System.out.println("\nManage Room Info");
        roomController.showRoomTotalPrice(room1);

        roomController.showAllRooms();

        playerController.showAllPlayers();

        roomPlayerController.showRoomsSolvedByPlayer(player1);

        roomPlayerController.showPlayersByRoom(room1);






    }
}