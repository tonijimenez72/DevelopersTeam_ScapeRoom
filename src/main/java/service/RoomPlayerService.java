package service;

import exception.DatabaseOperationException;
import exception.EntityNotFoundException;
import model.Room;
import model.RoomPlayer;

import java.util.List;

public interface RoomPlayerService {
    void addPlayerToRoom(int playerId, int roomId, boolean isSolved) throws EntityNotFoundException, DatabaseOperationException;
    void removePlayerFromRoom(int playerId, int roomId);
    void playerSolvedRoom(int playerId, int roomId, boolean isSolved) throws EntityNotFoundException;
}