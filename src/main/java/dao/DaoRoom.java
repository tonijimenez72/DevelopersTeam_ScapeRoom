package dao;

import model.Room;

import java.util.List;

public interface DaoRoom {

    void addRoom(Room room);
    List<Room> getAllRooms();
    void updateRoom(Room room);
    void deleteRoom(int id);
    void addRoomToEscapeRoom(Room room, int escapeRoomId);
    void updateRoomAvailability(int idRoom, boolean isAvailable);

}
