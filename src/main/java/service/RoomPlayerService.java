package service;

import model.Player;
import model.Room;

import java.util.List;

public interface RoomPlayerService {
    void addPlayerToRoom(Player player, Room room);
    void endRoomSession(Player player, Room room, boolean isSolved);
    List<Player> getPlayersByRoom(Room room);
    List<Room> getRoomsPlayedByPlayer(Player player);
    List<Room> getRoomsSolvedByPlayer(Player player);
}