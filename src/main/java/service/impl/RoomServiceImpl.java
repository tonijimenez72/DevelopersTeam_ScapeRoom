package service.impl;

import dao.Impl.DaoClueImpl;
import dao.Impl.DaoDecorationImpl;
import dao.Impl.DaoRoomImpl;
import model.Clue;
import model.Decoration;
import model.Room;
import service.RoomService;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    private final List<Room> rooms = new ArrayList<>();

    DaoRoomImpl daoRoom = new DaoRoomImpl();
    DaoClueImpl daoClue= new DaoClueImpl();
    DaoDecorationImpl daoDecoration= new DaoDecorationImpl();

    @Override
    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        rooms.add(room);
        daoRoom.addRoom(room);
        System.out.println("Room added: " + room.getName());


    }

    @Override
    public List<Room> getAllRooms() {

        List<Room> rooms1 = daoRoom.getAllRooms();

        return rooms1;

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
        daoClue.addClueToRoom(clue,room);

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
        daoDecoration.addDecoToRoom(decoration,room);


        System.out.println("Decoration \"" + decoration.getName() + "\" added to room \"" + room.getName() + "\".");
    }

    @Override
    public void updateRoomAvailability(Room room, boolean available) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        room.setAvailable(available);
        daoRoom.updateRoomAvailability(room,available);

        System.out.println("Room \"" + room.getName() + "\" availability updated to: " + available);
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