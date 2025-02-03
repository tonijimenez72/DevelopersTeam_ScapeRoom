package service;

import exception.DatabaseOperationException;
import exception.EntityNotFoundException;
import model.Room;
import model.RoomPlayer;

import java.util.List;

public interface RoomPlayerService {
    void addPlayerToRoom(int playerId, int roomId, boolean isSolved) throws EntityNotFoundException, DatabaseOperationException;
    void removePlayerFromRoom(int playerId, int roomId);
    List<RoomPlayer> playerPlayedRooms(int playerId)  throws EntityNotFoundException;
    List<RoomPlayer> roomPlayedByPlayers(int roomId) throws EntityNotFoundException;
    void playerSolvedRoom(int playerId, int roomId, boolean isSolved) throws EntityNotFoundException;
    List<Room> roomsSolvedByPlayer(int playerId) throws EntityNotFoundException;
}