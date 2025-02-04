package service.impl;

import dao.DaoEscapeRoom;
import dao.DaoRoom;
import dao.DaoClue;
import dao.DaoDecoration;
import dao.impl.DaoEscapeRoomImpl;
import dao.impl.DaoRoomImpl;
import dao.impl.DaoClueImpl;
import dao.impl.DaoDecorationImpl;
import enums.*;
import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.*;
import service.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private final DaoRoom daoRoom;
    private final DaoEscapeRoom daoEscapeRoom;
    private final DaoClue daoClue;
    private final DaoDecoration daoDecoration;

    public RoomServiceImpl() {
        this.daoRoom = new DaoRoomImpl();
        this.daoEscapeRoom = new DaoEscapeRoomImpl();
        this.daoClue = new DaoClueImpl();
        this.daoDecoration = new DaoDecorationImpl();
    }

    @Override
    public void create(String name, double price, Theme theme, DifficultyLevel difficultyLevel) throws InvalidEntityDataException {
        List<EscapeRoom> escapeRooms = daoEscapeRoom.getAll();
        int escapeRoomId = escapeRooms.get(0).getId();

        if (escapeRooms.isEmpty()) {
            throw new InvalidEntityDataException("Cannot create a room. Must create an escape room first.");
        }
        if (name == null || name.isEmpty() || price < 0) {
            throw new InvalidEntityDataException("Invalid room data: Name cannot be empty and price cannot be negative.");
        }

        Room room = new Room(name, price, theme, difficultyLevel, escapeRoomId);
        daoRoom.save(room);
    }

    @Override
    public List<Room> getAll() {
        return daoRoom.getAll();
    }

    @Override
    public Room getById(int id) throws EntityNotFoundException {
        if (id <= 0) throw new IllegalArgumentException("Room ID must be greater than 0.");
        Room room = daoRoom.getById(id);
        if (room == null) {
            throw new EntityNotFoundException("Clue not found for ID: " + id);
        }
        return room;
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        Room room = getById(id);
        daoRoom.remove(room);
    }

    @Override
    public double calculateTotalStockValue() {
        return getAll().stream()
                .mapToDouble(room ->
                        room.getPrice() +
                                room.getClues().stream().mapToDouble(Clue::getPrice).sum() +
                                room.getDecorations().stream().mapToDouble(Decoration::getPrice).sum()
                )
                .sum();
    }

    public void showStockInfo() {
        List<Room> rooms = daoRoom.getAll();
        List<Clue> clues = daoClue.getAll();
        List<Decoration> decorations = daoDecoration.getAll();

        int totalRooms = rooms.size();
        int totalClues = clues.size();
        int totalDecorations = decorations.size();

        System.out.printf("%nEscape Room Stock Info:%n Total Rooms: %s | Total Clues: %s | Total Decorations: %s | Total Value: %.2f%n", totalRooms, totalClues, totalDecorations, calculateTotalStockValue() );
    }


}