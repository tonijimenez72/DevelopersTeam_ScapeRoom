package controller;

import service.RoomPlayerService;

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

    public void playerSolvedRoom(int playerId, int roomId, boolean isSolved) {
        try {
            roomPlayerService.playerSolvedRoom(playerId, roomId, isSolved);
            System.out.println("Room completion status updated successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}