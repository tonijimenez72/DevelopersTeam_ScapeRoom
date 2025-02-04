package service;

import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.Decoration;

import java.util.List;

public interface DecorationService {
    void create(String name, double price, String material, int roomId) throws InvalidEntityDataException;
    List<Decoration> getAll();
    Decoration getById(int id) throws EntityNotFoundException;
    void delete(int id) throws EntityNotFoundException;
}