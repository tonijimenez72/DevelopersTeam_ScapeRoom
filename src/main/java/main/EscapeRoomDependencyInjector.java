package main;

import menu.MainMenu;
import service.*;
import service.impl.*;
import controller.*;

public class EscapeRoomDependencyInjector implements DependencyInjector {
    public MainMenu initializeApplication() {
        EscapeRoomService escapeRoomService = new EscapeRoomServiceImpl();
        ClueService clueService = new ClueServiceImpl();
        DecorationService decorationService = new DecorationServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        PlayerService playerService = new PlayerServiceImpl();
        TicketService ticketService = new TicketServiceImpl();

        RoomPlayerService roomPlayerService = new RoomPlayerServiceImpl(roomService, playerService, ticketService);

        EscapeRoomController escapeRoomController = new EscapeRoomController(escapeRoomService);
        ClueController clueController = new ClueController(clueService);
        DecorationController decorationController = new DecorationController(decorationService);
        RoomController roomController = new RoomController(roomService);
        PlayerController playerController = new PlayerController();
        RoomPlayerController roomPlayerController = new RoomPlayerController(roomPlayerService);
        TicketController ticketController = new TicketController(ticketService);

        return new MainMenu(
                escapeRoomController,
                clueController,
                decorationController,
                roomController,
                playerController,
                roomPlayerController,
                ticketController
        );
    }
}
