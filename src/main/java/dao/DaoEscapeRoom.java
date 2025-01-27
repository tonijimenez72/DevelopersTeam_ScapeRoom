package dao;

import model.EscapeRoom;

import java.util.List;

public interface DaoEscapeRoom {

    void addEscapeRoom(EscapeRoom escapeRoom);
    List<EscapeRoom> getAllEscapeRooms();
    void updateEscapeRoom(EscapeRoom escapeRoom);
    void deleteEscapeRoom(int id);
}
