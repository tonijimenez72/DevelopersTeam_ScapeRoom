package dao;

import model.Room;

import java.util.List;

public interface DaoRoom extends GenericDao<Room>{
    void save(Room room);
    List<Room> getAll();
    Room getById(int id);
    void remove(Room room);
}