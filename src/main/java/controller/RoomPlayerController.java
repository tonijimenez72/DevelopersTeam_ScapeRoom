package controller;

import model.Player;
import model.Room;
import service.RoomPlayerService;

import java.util.List;

public class RoomPlayerController {

    private final RoomPlayerService roomPlayerService;

    public RoomPlayerController(RoomPlayerService roomPlayerService) {
        this.roomPlayerService = roomPlayerService;
    }

    public void addPlayerToRoom(Player player, Room room) {
        try {
            roomPlayerService.addPlayerToRoom(player, room);
            System.out.printf("Player '%s' added to room '%s'.%n", player.getName(), room.getName());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error adding player to room: " + e.getMessage());
        }
    }

    public void endRoomSession(Player player, Room room, boolean isSolved) {
        try {
            roomPlayerService.endRoomSession(player, room, isSolved);
            System.out.printf("Session ended: [Room %s] [Player: %s]%n", room.getName(), player.getName());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error ending session: " + e.getMessage());
        }
    }

    public void showPlayersByRoom(Room room) {
        try {
            List<Player> players = roomPlayerService.getPlayersByRoom(room);
            if (players.isEmpty()) {
                System.out.println("No players have played the room: " + room.getName());
            } else {
                System.out.printf("Room:%n * %s%nPlayed by:%n", room.getName());
                players.forEach(player -> System.out.println(" * " + player.getName()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error retrieving players for room: " + e.getMessage());
        }
    }

    public void showRoomsPlayedByPlayer(Player player) {
        try {
            List<Room> rooms = roomPlayerService.getRoomsPlayedByPlayer(player);
            if (rooms.isEmpty()) {
                System.out.println("Player " + player.getName() + " has not played any rooms.");
            } else {
                System.out.printf("Player:%n * %s%nRooms played:%n", player.getName());
                rooms.forEach(room -> System.out.println(" * " + room.getName()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error retrieving rooms for player: " + e.getMessage());
        }
    }

    public void showRoomsSolvedByPlayer(Player player) {
        try {
            List<Room> rooms = roomPlayerService.getRoomsSolvedByPlayer(player);
            if (rooms.isEmpty()) {
                System.out.println("Player " + player.getName() + " has not solved any rooms.");
            } else {
                System.out.printf("Player:%n * %s%nRooms solved:%n", player.getName());
                rooms.forEach(room -> System.out.println(" * " + room.getName()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error retrieving solved rooms for player: " + e.getMessage());
        }
    }
}