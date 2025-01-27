package service.impl;

import model.EscapeRoom;
import service.EscapeRoomService;

import java.util.ArrayList;
import java.util.List;

public class EscapeRoomServiceImpl implements EscapeRoomService {
    private final List<EscapeRoom> escapeRooms = new ArrayList<>();

    @Override
    public void addEscapeRoom(EscapeRoom escapeRoom) throws IllegalStateException {
        if (escapeRoomExists()) {
            throw new IllegalStateException("An Escape Room already exists. Only one is allowed.");
        }
        escapeRooms.add(escapeRoom);
        System.out.println("Escape Room added successfully!");
    }

    @Override
    public EscapeRoom getEscapeRoomById(int id) {
        return escapeRooms.stream()
                .filter(escapeRoom -> escapeRoom.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<EscapeRoom> getAllEscapeRooms() {
        return new ArrayList<>(escapeRooms);
    }

    @Override
    public void updateEscapeRoom(EscapeRoom escapeRoom) {
        for (int i = 0; i < escapeRooms.size(); i++) {
            if (escapeRooms.get(i).getId() == escapeRoom.getId()) {
                escapeRooms.set(i, escapeRoom);
                System.out.println("Escape Room updated successfully!");
                return;
            }
        }
        System.out.println("Escape Room not found. Update failed.");
    }

    @Override
    public void deleteEscapeRoom(int id) {
        escapeRooms.removeIf(escapeRoom -> escapeRoom.getId() == id);
        System.out.println("Escape Room deleted successfully!");
    }

    @Override
    public boolean escapeRoomExists() {
        return !escapeRooms.isEmpty();
    }
}
