package dao;

import model.Player;
import model.Room;

import java.util.List;

public interface DaoRoomPlayer  {


    void safe ( Player player, Room room);

    List<int[]> getAll();








    //void remove (Player player, Room room);
    /*void addPlayerRoomRelation(int idPlayer, int idRoom);

    List<int[]> getAllPlayerRoomRelations();

    void updatePlayerRoomRelation(int oldPlayerId, int oldRoomId, int newPlayerId, int newRoomId);

    void deletePlayerRoomRelation(int playerId, int roomId);*/
}
