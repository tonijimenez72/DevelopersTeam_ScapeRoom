package main;

import controller.*;
import menu.MainMenu;
import service.*;
import service.impl.*;

public class Main {
    public static void main(String[] args) {

        ClueService clueService = new ClueServiceImpl();
        DecorationService decorationService = new DecorationServiceImpl();
        RoomService roomService = new RoomServiceImpl(clueService, decorationService);
        PlayerService playerService = new PlayerServiceImpl();
        RoomPlayerService roomPlayerService = new RoomPlayerServiceImpl(playerService, roomService);

        ClueController clueController = new ClueController(clueService);
        DecorationController decorationController = new DecorationController(decorationService);
        RoomController roomController = new RoomController(roomService);
        PlayerController playerController = new PlayerController(playerService);
        RoomPlayerController roomPlayerController = new RoomPlayerController(roomPlayerService);

        MainMenu mainMenu = new MainMenu(clueController, decorationController, roomController, playerController, roomPlayerController);

        mainMenu.run();
    }
}