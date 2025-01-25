package service.impl;

import model.Player;
import model.Room;
import service.RoomPlayerService;

import java.util.ArrayList;
import java.util.List;

public class RoomPlayerServiceImpl implements RoomPlayerService {

    @Override
    public void addPlayerToRoom(Player player, Room room) {
        if (player == null || room == null) {
            throw new IllegalArgumentException("Player or Room cannot be null.");
        }
        if (!room.isAvailable()) {
            throw new IllegalStateException("Room: " + room.getName() + " [Status: not available]");
        }
        player.addPlayedRoom(room);
        room.addPlayerToRoom(player);
        room.setAvailable(false);
        System.out.printf("Room: %s [New player: %s]", room.getName(), player.getName());
    }

    @Override
    public void endRoomSession(Player player, Room room, boolean isSolved) {
        if (player == null || room == null) {
            throw new IllegalArgumentException("Player or Room cannot be null.");
        }
        if (!player.getPlayedRooms().contains(room)) {
            throw new IllegalStateException("Player '" + player.getName() + "' has not played this room.");
        }
        if (isSolved) {
            player.addSolvedRoom(room);
            System.out.printf("Solved room: %s [Player: %s]%n", room.getName(), player.getName());
        }
        room.setAvailable(true);
        System.out.printf("Ended session: [Room: %s] [Player %s]%n", room.getName(), player.getName());
    }

    @Override
    public List<Player> getPlayersByRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        return new ArrayList<>(room.getPlayers());
    }

    @Override
    public List<Room> getRoomsPlayedByPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        return new ArrayList<>(player.getPlayedRooms());
    }

    @Override
    public List<Room> getRoomsSolvedByPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        return new ArrayList<>(player.getSolvedRooms());
    }
}