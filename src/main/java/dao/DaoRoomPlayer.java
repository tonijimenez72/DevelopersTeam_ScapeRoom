package dao;

import model.Player;
import model.Room;

import java.util.List;

public interface DaoRoomPlayer {

    void addPlayerRoomRelation(int idPlayer, int idRoom);

    List<int[]> getAllPlayerRoomRelations();

    void updatePlayerRoomRelation(int oldPlayerId, int oldRoomId, int newPlayerId, int newRoomId);

    void deletePlayerRoomRelation(int playerId, int roomId);
}
