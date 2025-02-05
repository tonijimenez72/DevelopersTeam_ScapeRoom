package dao;

import model.RoomPlayer;

import java.util.List;

public interface DaoRoomPlayer extends GenericDao<RoomPlayer> {
    void save(RoomPlayer roomPlayer);
    RoomPlayer getById(int id);
    List<RoomPlayer> getAll();
    void remove(RoomPlayer roomPlayer);
}
