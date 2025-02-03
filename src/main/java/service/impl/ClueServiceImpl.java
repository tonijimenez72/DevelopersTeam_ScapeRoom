package service.impl;

import dao.DaoClue;
import dao.impl.DaoClueImpl;
import dao.DaoRoom;
import dao.impl.DaoRoomImpl;
import enums.Theme;
import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.Clue;
import service.ClueService;

import java.util.List;

public class ClueServiceImpl implements ClueService {
    private final DaoClue daoClue;
    private final DaoRoom daoRoom;

    public ClueServiceImpl() {
        this.daoClue = new DaoClueImpl();
        this.daoRoom = new DaoRoomImpl();
    }

    @Override
    public void create(String name, double price, Theme theme, int roomId) throws InvalidEntityDataException {
        if (name == null || name.isEmpty()) {
            throw new InvalidEntityDataException("Invalid clue data: Name cannot be empty.");
        }
        if (price < 0) {
            throw new InvalidEntityDataException("Invalid clue data: Price cannot be negative.");
        }
        if (roomId <= 0 || daoRoom.getById(roomId) == null) {
            throw new InvalidEntityDataException("Room not found for ID: " + roomId);
        }

        Clue clue = new Clue(name, price, theme, roomId);
        daoClue.save(clue);
    }

    @Override
    public List<Clue> getAll() {
        return daoClue.getAll();
    }

    @Override
    public Clue getById(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Clue ID must be greater than 0.");
        }
        Clue clue = daoClue.getById(id);
        if (clue == null) {
            throw new EntityNotFoundException("Clue not found for ID: " + id);
        }
        return clue;
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        Clue clue = getById(id);
        daoClue.remove(clue);
    }
}