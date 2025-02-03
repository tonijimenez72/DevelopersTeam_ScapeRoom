package service;

import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.EscapeRoom;

import java.util.List;

public interface EscapeRoomService {
    void create(String name) throws InvalidEntityDataException;
    List<EscapeRoom> getAll();
    EscapeRoom getById(int id) throws EntityNotFoundException;
}