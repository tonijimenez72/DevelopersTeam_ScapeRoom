package service;

import enums.DifficultyLevel;
import enums.Theme;
import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.Room;

import java.util.List;

public interface RoomService {
    void create(String name, double price, Theme theme, DifficultyLevel difficultyLevel) throws InvalidEntityDataException;
    List<Room> getAll();
    Room getById(int id) throws EntityNotFoundException;
    void delete(int id) throws EntityNotFoundException;
    double calculateTotalStockValue();
    void showStockInfo();
}