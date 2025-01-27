package service.impl;

import model.Clue;
import model.Decoration;
import model.Room;
import service.ClueService;
import service.DecorationService;
import service.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {

    private final List<Room> rooms = new ArrayList<>();
    private final ClueService clueService;
    private final DecorationService decorationService;

    public RoomServiceImpl(ClueService clueService, DecorationService decorationService) {
        this.clueService = clueService;
        this.decorationService = decorationService;
    }

    @Override
    public void createRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        rooms.add(room);
        System.out.println("Room added: " + room.getName());
    }

    @Override
    public void addClueToRoom(int roomId, int clueId) {
        Room room = getRoomById(roomId);
        Clue clue = clueService.getClueById(clueId);

        if (!clue.isAvailable()) {
            throw new IllegalStateException("Clue is not available: ID " + clueId);
        }

        room.addClueToRoom(clue);
        clue.setAvailable(false);

    }

    @Override
    public void addDecorationToRoom(int roomId, int decorationId) {
        Room room = getRoomById(roomId);
        Decoration decoration = decorationService.getDecorationById(decorationId);

        if (!decoration.isAvailable()) {
            throw new IllegalStateException("Decoration is not available: ID " + decorationId);
        }

        room.addDecorationToRoom(decoration);
        decoration.setAvailable(false);
    }

    @Override
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    @Override
    public List<Room> getAvailableRooms() {
        return rooms.stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public Room getRoomById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        return rooms.stream()
                .filter(clue -> clue.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Room not found."));
    }

    public void updateRoomStatus(int id, boolean available) {
        Room room = getRoomById(id);
        room.setAvailable(available);
    }

    @Override
    public void removeClueFromRoom(int roomId, int clueId) {
        Room room = getRoomById(roomId);
        Clue clue = room.getClues().stream()
                .filter(c -> c.getId() == clueId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Clue not found in room: ID " + clueId));

        room.getClues().remove(clue);
        clue.setAvailable(true);
    }

    @Override
    public void removeDecorationFromRoom(int roomId, int decorationId) {
        Room room = getRoomById(roomId);
        Decoration decoration = room.getDecorations().stream()
                .filter(d -> d.getId() == decorationId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Decoration not found in room: ID " + decorationId));

        room.getDecorations().remove(decoration);
        decoration.setAvailable(true);
    }

    @Override
    public void deleteRoom(int id) {
        Room room = getRoomById(id);
        rooms.remove(room);
        System.out.println("Room deleted.");
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

    @Override
    public double calculateTotalSalesAmount() {
        return getAllRooms().stream()
                .mapToDouble(room ->
                        room.getPrice() +
                                room.getClues().stream().mapToDouble(Clue::getPrice).sum() +
                                room.getDecorations().stream().mapToDouble(Decoration::getPrice).sum()
                )
                .sum();
    }
}