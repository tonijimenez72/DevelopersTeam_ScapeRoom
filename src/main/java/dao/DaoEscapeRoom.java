package dao;

import model.EscapeRoom;

import java.util.List;

public interface DaoEscapeRoom {
    void save(EscapeRoom escapeRoom);
    EscapeRoom getById(int id);
    List<EscapeRoom> getAll();
    int count();
}