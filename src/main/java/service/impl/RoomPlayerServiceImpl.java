package service.impl;

import dao.DaoRoomPlayer;
import dao.impl.DaoRoomPlayerImpl;
import exception.DatabaseOperationException;
import exception.EntityNotFoundException;
import model.Player;
import model.Room;
import model.RoomPlayer;
import model.Ticket;
import service.RoomPlayerService;
import service.RoomService;
import service.PlayerService;
import service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

public class RoomPlayerServiceImpl implements RoomPlayerService {
    private final DaoRoomPlayer daoRoomPlayer;
    private final RoomService roomService;
    private final PlayerService playerService;
    private final TicketService ticketService; // ✅ Agregado para guardar tickets

    public RoomPlayerServiceImpl(RoomService roomService, PlayerService playerService, TicketService ticketService) {
        this.daoRoomPlayer = new DaoRoomPlayerImpl();
        this.roomService = roomService;
        this.playerService = playerService;
        this.ticketService = ticketService; // ✅ Inicializado
    }

    @Override
    public void addPlayerToRoom(int playerId, int roomId, boolean isSolved) throws EntityNotFoundException, DatabaseOperationException {
        Player player = playerService.getById(playerId);
        Room room = roomService.getById(roomId);

        if (player == null) {
            throw new EntityNotFoundException("Player not found for ID: " + playerId);
        }
        if (room == null) {
            throw new EntityNotFoundException("Room not found for ID: " + roomId);
        }

        RoomPlayer roomPlayer = new RoomPlayer(playerId, roomId, isSolved);
        daoRoomPlayer.save(roomPlayer);

        printTicket(player, room);

         Ticket ticket = new Ticket(roomId, playerId, room.getTotalPrice());
        ticketService.save(ticket);

        String notificationMessage = String.format("Player: %s has reserved the room: %s", player.getName(), room.getName());
        playerService.sendNotification(notificationMessage);
    }

    @Override
    public void removePlayerFromRoom(int playerId, int roomId) {
        RoomPlayer roomPlayer = new RoomPlayer(playerId, roomId, false);
        daoRoomPlayer.remove(roomPlayer);
    }

    @Override
    public List<RoomPlayer> playerPlayedRooms(int playerId) throws EntityNotFoundException {
        if (playerId <= 0) {
            throw new IllegalArgumentException("Player ID must be greater than zero.");
        }

        List<RoomPlayer> rooms = daoRoomPlayer.getRoomsByPlayerId(playerId);
        if (rooms.isEmpty()) {
            throw new EntityNotFoundException("No rooms found for Player ID: " + playerId);
        }

        return rooms;
    }

    @Override
    public List<RoomPlayer> roomPlayedByPlayers(int roomId) throws EntityNotFoundException {
        if (roomId <= 0) {
            throw new IllegalArgumentException("Room ID must be greater than zero.");
        }

        List<RoomPlayer> players = daoRoomPlayer.getPlayersByRoomId(roomId);
        if (players.isEmpty()) {
            throw new EntityNotFoundException("No players found for Room ID: " + roomId);
        }

        return players;
    }

    @Override
    public void playerSolvedRoom(int playerId, int roomId, boolean isSolved) throws EntityNotFoundException {
        Player player = playerService.getById(playerId);
        Room room = roomService.getById(roomId);

        if (player == null) {
            throw new EntityNotFoundException("Player not found for ID: " + playerId);
        }
        if (room == null) {
            throw new EntityNotFoundException("Room not found for ID: " + roomId);
        }

        RoomPlayer roomPlayer = new RoomPlayer(playerId, roomId, isSolved);
        daoRoomPlayer.save(roomPlayer);

        if (isSolved) {
            printCertificate(player, room);
        }
    }

    @Override
    public List<Room> roomsSolvedByPlayer(int playerId) throws EntityNotFoundException {
        if (playerId <= 0) {
            throw new IllegalArgumentException("Player ID must be greater than zero.");
        }

        List<Room> solvedRooms = daoRoomPlayer.getRoomsByPlayerId(playerId).stream()
                .filter(RoomPlayer::isSolved)
                .map(RoomPlayer::getRoom)
                .collect(Collectors.toList());

        if (solvedRooms.isEmpty()) {
            throw new EntityNotFoundException("No solved rooms found for Player ID: " + playerId);
        }

        return solvedRooms;
    }


    private void printTicket(Player player, Room room) throws EntityNotFoundException {
        if (player == null || room == null) {
            throw new EntityNotFoundException("Player or Room not found.");
        }

        System.out.printf(
                "ESCAPE ROOM TICKET%nPlayer: %s%nRoom: %s%nPrice: €%.2f%nWELCOME AND HAVE FUN!%n",
                player.getName(), room.getName(), room.getPrice()
        );
    }

    private void printCertificate(Player player, Room room) throws EntityNotFoundException {
        if (player == null || room == null) {
            throw new EntityNotFoundException("Player or Room not found.");
        }

        System.out.printf(
                "ESCAPE ROOM CERTIFICATE%nPlayer: %s%nRoom: %s%nCONGRATULATIONS, YOU SOLVED THE ROOM!%n",
                player.getName(), room.getName()
        );
    }
}