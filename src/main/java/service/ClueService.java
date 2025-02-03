package service;

import enums.Theme;
import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.Clue;

import java.util.List;

public interface ClueService {
    void create(String name, double price, Theme theme, int roomId) throws InvalidEntityDataException;
    List<Clue> getAll();
    Clue getById(int id) throws EntityNotFoundException;
    void delete(int id) throws EntityNotFoundException;
}