package service.impl;

import dao.DaoDecoration;
import dao.impl.DaoDecorationImpl;
import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.Decoration;
import service.DecorationService;

import java.util.List;

public class DecorationServiceImpl implements DecorationService {
    private final DaoDecoration daoDecoration;

    public DecorationServiceImpl() {
        this.daoDecoration = new DaoDecorationImpl();
    }

    @Override
    public void create(String name, double price, String material, int roomId) throws InvalidEntityDataException {
        if (name == null || name.isEmpty() || price < 0) {
            throw new InvalidEntityDataException("Invalid clue data: Name cannot be empty and price must be non-negative.");
        }
        Decoration decoration = new Decoration(name, price, material, roomId);
        daoDecoration.save(decoration);
    }

    @Override
    public List<Decoration> getAll() {
        return daoDecoration.getAll();
    }

    @Override
    public Decoration getById(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Decoration ID must be greater than 0.");
        }
        Decoration decoration = daoDecoration.getById(id);
        if (decoration == null) {
            throw new EntityNotFoundException("Clue not found for ID: " + id);
        }
        return decoration;
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        Decoration decoration = getById(id);
        daoDecoration.remove(decoration);
    }
}