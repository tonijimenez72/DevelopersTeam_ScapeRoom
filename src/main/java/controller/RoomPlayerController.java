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

    public void addPlayerToRoom(int playerId, int roomId) {
        try {
            roomPlayerService.addPlayerToRoom(playerId, roomId);
        } catch (Exception e) {
            System.out.println("Error adding player to room: " + e.getMessage());
        }
    }

    public void endRoomSession(int playerId, int roomId, boolean isSolved) {
        try {
            roomPlayerService.endRoomSession(playerId, roomId, isSolved);
        } catch (Exception e) {
            System.out.println("Error ending room session: " + e.getMessage());
        }
    }

    public void showPlayersByRoom(int roomId) {
        try {
            List<Player> players = roomPlayerService.getPlayersByRoom(roomId);
            if (players.isEmpty()) {
                System.out.println("Room available.");
            } else {
                System.out.printf("Session in progress:%n Room ID: %s%n Players: ", roomId);
                players.forEach(player -> System.out.printf(" Player ID: %s | Name: %s", player.getId(), player.getName()));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving players by room: " + e.getMessage());
        }
    }

    public void showRoomsPlayedByPlayer(int playerId) {
        try {
            List<Room> rooms = roomPlayerService.getRoomsPlayedByPlayer(playerId);
            if (rooms.isEmpty()) {
                System.out.printf("Player with ID '%d' has not played any rooms.%n", playerId);
            } else {
                System.out.printf("Player ID:%s:%n", playerId);
                rooms.forEach(room -> System.out.printf("Rooms played:%n ID: %d | Name: %s%n", room.getId(), room.getName()));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving rooms played by player: " + e.getMessage());
        }
    }

    public void showRoomsSolvedByPlayer(int playerId) {
        try {
            List<Room> rooms = roomPlayerService.getRoomsSolvedByPlayer(playerId);
            if (rooms.isEmpty()) {
                System.out.println("Player has not solved any room.");
            } else {
                System.out.printf("Player ID: %s:", playerId);
                rooms.forEach(room -> System.out.printf("%nRooms solved:%n ID: %d | Name: %s%n", room.getId(), room.getName()));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving rooms solved by player: " + e.getMessage());
        }
    }
}
