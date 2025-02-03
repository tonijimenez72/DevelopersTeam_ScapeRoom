package controller;

import exception.EntityNotFoundException;
import service.RoomPlayerService;

import java.util.List;

public class RoomPlayerController {
    private final RoomPlayerService roomPlayerService;

    public RoomPlayerController(RoomPlayerService roomPlayerService) {
        this.roomPlayerService = roomPlayerService;
    }

    public void addPlayerToRoom(int playerId, int roomId, boolean isSolved) {
        try {
            roomPlayerService.addPlayerToRoom(playerId, roomId, isSolved);
            System.out.println("Player successfully added to the room.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removePlayerFromRoom(int playerId, int roomId) {
        roomPlayerService.removePlayerFromRoom(playerId, roomId);
        System.out.println("Player successfully removed from the room.");
    }

    public void playerPlayedRooms(int playerId) {
        try {
            List<String> rooms = roomPlayerService.playerPlayedRooms(playerId).stream()
                    .map(Object::toString)
                    .toList();

            if (rooms.isEmpty()) {
                System.out.println("No rooms found for this player.");
            } else {
                System.out.println("Rooms played by the player:");
                rooms.forEach(System.out::println);
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void roomPlayedByPlayers(int roomId) {
        try {
            List<String> players = roomPlayerService.roomPlayedByPlayers(roomId).stream()
                    .map(Object::toString)
                    .toList();

            if (players.isEmpty()) {
                System.out.println("No players found for this room.");
            } else {
                System.out.println("Players who played in this room:");
                players.forEach(System.out::println);
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void playerSolvedRoom(int playerId, int roomId, boolean isSolved) {
        try {
            roomPlayerService.playerSolvedRoom(playerId, roomId, isSolved);
            System.out.println("Room completion status updated successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}