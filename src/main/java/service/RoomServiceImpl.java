package service;

import model.Clue;
import model.Decoration;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    private final List<Room> rooms = new ArrayList<>();

    @Override
    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        rooms.add(room);
        System.out.println("Room added: " + room.getName());
    }

    @Override
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms); // Return a copy to avoid external modification
    }

    @Override
    public void addClueToRoom(Room room, Clue clue) {
        if (room == null || clue == null) {
            throw new IllegalArgumentException("Room or Clue cannot be null.");
        }
        if (!clue.isAvailable()) {
            throw new IllegalStateException("Clue is not available.");
        }
        room.getClues().add(clue);
        clue.setAvailable(false);
        System.out.println("Clue \"" + clue.getName() + "\" added to room \"" + room.getName() + "\".");
    }

    @Override
    public void addDecorationToRoom(Room room, Decoration decoration) {
        if (room == null || decoration == null) {
            throw new IllegalArgumentException("Room or Decoration cannot be null.");
        }
        if (!decoration.isAvailable()) {
            throw new IllegalStateException("Decoration is not available.");
        }
        room.getDecorations().add(decoration);
        decoration.setAvailable(false);
        System.out.println("Decoration \"" + decoration.getName() + "\" added to room \"" + room.getName() + "\".");
    }

    @Override
    public void updateRoomAvailability(Room room, boolean available) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        room.setAvailable(available);
        System.out.println("Room \"" + room.getName() + "\" availability updated to: " + available);
    }
}