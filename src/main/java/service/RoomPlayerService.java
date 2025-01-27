package service;

import model.Player;
import model.Room;

import java.util.List;

public interface RoomPlayerService {
    void addPlayerToRoom(int playerId, int roomId);
    void endRoomSession(int playerId, int roomId, boolean isSolved);
    List<Player> getPlayersByRoom(int roomId);
    List<Room> getRoomsPlayedByPlayer(int playerId);
    List<Room> getRoomsSolvedByPlayer(int playerId);
    void printTicket(int playerId, int roomId);
    void printCertificate(int playerId, int roomId, boolean isSolved);
}