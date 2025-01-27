package dao;

import model.Decoration;
import model.Room;

import java.util.List;

public interface DaoDecoration {

    void insertDecoration(Decoration decoration);
    List<Decoration> getAllDecorations();
    void updateDecoration(Decoration decoration);
    void deleteDecoration(Decoration decoration);
    void addDecoToRoom(Decoration decoration, Room room);
}
