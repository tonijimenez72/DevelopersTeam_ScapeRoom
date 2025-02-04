package service.impl;

import dao.DaoEscapeRoom;
import dao.impl.DaoEscapeRoomImpl;
import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.EscapeRoom;
import service.EscapeRoomService;

import java.util.List;

public class EscapeRoomServiceImpl implements EscapeRoomService {

    private final DaoEscapeRoom daoEscapeRoom;

    public EscapeRoomServiceImpl() {
        this.daoEscapeRoom = new DaoEscapeRoomImpl();
    }

    @Override
    public void create(String name) throws InvalidEntityDataException {
        if (name == null || name.isEmpty()) {
            throw new InvalidEntityDataException("Invalid clue data: Name cannot be empty.");
        }
        if (daoEscapeRoom.count() > 0) {
            throw new InvalidEntityDataException("Only one Escape Room can be created.");
        }
        EscapeRoom escapeRoom = new EscapeRoom(name);
        daoEscapeRoom.save(escapeRoom);
    }

    @Override
    public List<EscapeRoom> getAll() {
        return daoEscapeRoom.getAll();
    }

    @Override
    public EscapeRoom getById(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Escape Room ID must be greater than 0.");
        }
        EscapeRoom escapeRoom = daoEscapeRoom.getById(id);
        if (escapeRoom == null) {
            throw new EntityNotFoundException("Escape Room not found for ID: " + id);
        }
        return escapeRoom;
    }
}