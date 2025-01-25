package service.impl;

import model.Clue;
import model.Decoration;
import model.Room;
import service.RoomService;

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
        return new ArrayList<>(rooms);
    }

    @Override
    public void addClueToRoom(Room room, Clue clue) {
        if (room == null || clue == null) {
            throw new IllegalArgumentException("Room or Clue cannot be null.");
        }
        if (!clue.isAvailable()) {
            throw new IllegalStateException("[Clue: " + clue.getName() + "] [Status: not available]");
        }
        room.addClueToRoom(clue);
        System.out.printf("Room %s [New clue added: %s]", room.getName(), clue.getName());
    }

    @Override
    public void addDecorationToRoom(Room room, Decoration decoration) {
        if (room == null || decoration == null) {
            throw new IllegalArgumentException("Room or Decoration cannot be null.");
        }
        if (!decoration.isAvailable()) {
            throw new IllegalStateException("[Decoration: " + decoration.getName() + "] [Status: not available]");
        }
        room.addDecorationToRoom(decoration);
        System.out.printf("Room: %s [New decoration added: %s]", room.getName(), decoration.getName());
    }

    @Override
    public void updateRoomAvailability(Room room, boolean available) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        room.setAvailable(available);
        System.out.println("Room: " + room.getName() + "[status: " + (available ? "available" : "not available") + "]");

    }

    @Override
    public double calculateRoomTotalPrice(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }

        double totalCluePrice = room.getClues().stream()
                .mapToDouble(Clue::getPrice)
                .sum();

        double totalDecorationPrice = room.getDecorations().stream()
                .mapToDouble(Decoration::getPrice)
                .sum();

        return room.getPrice() + totalCluePrice + totalDecorationPrice;
    }

}