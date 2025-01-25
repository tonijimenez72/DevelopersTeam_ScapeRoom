package service;

import model.Clue;
import model.Decoration;
import model.Room;

import java.util.List;

public interface RoomService {
    void addRoom(Room room);
    List<Room> getAllRooms();
    void addClueToRoom(Room room, Clue clue);
    void addDecorationToRoom(Room room, Decoration decoration);
    void updateRoomAvailability(Room room, boolean available);
    double calculateRoomTotalPrice(Room room);
}