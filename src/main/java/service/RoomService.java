package service;

import model.Room;

import java.util.List;

public interface RoomService {
    void createRoom(Room room);
    void addClueToRoom(int roomId, int clueId);
    void addDecorationToRoom(int roomId, int clueId);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    Room getRoomById(int id);
    void updateRoomStatus(int id, boolean available);
    void removeClueFromRoom(int roomId, int clueId);
    void removeDecorationFromRoom(int roomId, int clueId);
    void deleteRoom(int id);
    double calculateRoomTotalPrice(Room room);
    double calculateTotalSalesAmount();
}