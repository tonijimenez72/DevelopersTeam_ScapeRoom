package service.impl;

import dao.Impl.DaoRoomImpl;
import dao.Impl.DaoRoomPlayerImpl;
import model.Player;
import model.Room;
import service.PlayerService;
import service.RoomPlayerService;
import service.RoomService;

import java.util.ArrayList;
import java.util.List;

public class RoomPlayerServiceImpl implements RoomPlayerService {

    private final PlayerService playerService;
    private final RoomService roomService;
    private final DaoRoomPlayerImpl daoRoomPlayer;

    public RoomPlayerServiceImpl(PlayerService playerService, RoomService roomService) {
        this.playerService = playerService;
        this.roomService = roomService;
        this.daoRoomPlayer= new DaoRoomPlayerImpl();
    }

    @Override
    public void addPlayerToRoom(int playerId, int roomId) {
        Player player = playerService.getPlayerById(playerId);
        Room room = roomService.getRoomById(roomId);

        if (player == null || room == null) {
            throw new IllegalArgumentException("Player or Room cannot be null.");
        }
        if (!room.isAvailable()) {
            throw new IllegalStateException("Room: " + room.getName() + " is not available.");
        }
        player.addPlayedRoom(room);
        room.addPlayerToRoom(player);
        room.setAvailable(false);
        daoRoomPlayer.addPlayerRoomRelation(playerId,roomId);
        System.out.printf("Room: %s | New player: %s%n", room.getName(), player.getName());
    }

    @Override
    public void endRoomSession(int playerId, int roomId, boolean isSolved) {
        Player player = playerService.getPlayerById(playerId);
        Room room = roomService.getRoomById(roomId);

        if (player == null || room == null) {
            throw new IllegalArgumentException("Player or Room cannot be null.");
        }
        if (!player.getPlayedRooms().contains(room)) {
            throw new IllegalStateException("Player has not played this room.");
        }
        if (isSolved) {
            player.addSolvedRoom(room);
            System.out.printf("Solved room:%n Name: %s | Player: %s%n", room.getName(), player.getName());

            printCertificate(playerId, roomId, isSolved);
        }
        room.setAvailable(true);
        System.out.printf("Ended session:%n Room: %s | Player: %s%n", room.getName(), player.getName());
    }

    @Override
    public List<Player> getPlayersByRoom(int roomId) {
        Room room = roomService.getRoomById(roomId);

        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        return new ArrayList<>(room.getPlayers());
    }

    @Override
    public List<Room> getRoomsPlayedByPlayer(int playerId) {
        Player player = playerService.getPlayerById(playerId);
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        return new ArrayList<>(player.getPlayedRooms());
    }

    @Override
    public List<Room> getRoomsSolvedByPlayer(int playerId) {
        Player player = playerService.getPlayerById(playerId);
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        return new ArrayList<>(player.getSolvedRooms());
    }

    @Override
    public void printTicket(int playerId, int roomId) {
        String ticket = """
                        Escape Room Ticket
                        
                        Player: %s
                        Room: %s
                        Price: %.2f
                       
                        WELCOME AND HAVE FUN!
                        """;

        Player player = playerService.getPlayerById(playerId);
        Room room = roomService.getRoomById(roomId);

        if (player == null || room == null) {
            throw new IllegalArgumentException("Player or Room not found.");
        }

        System.out.println(String.format(ticket,
                player.getName(),
                room.getName(),
                room.getTotalPrice()
        ));
    }

    @Override
    public void printCertificate(int playerId, int roomId, boolean isSolved) {
        String certificate = """
                       Escape Room Certificate
                       
                       Player: %s
                       Room: %s
                       Solved: %s
                       
                       CONGRATULATIONS, YOU SOLVED THE ROOM!
                       """;
        Player player = playerService.getPlayerById(playerId);
        Room room = roomService.getRoomById(roomId);

        if (player == null || room == null) {
            throw new IllegalArgumentException("Player or Room not found.");
        }

        System.out.println(String.format(certificate,
                player.getName(),
                room.getName(),
                isSolved ? "Yes" : "No"
        ));
    }
}
