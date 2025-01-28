package dao;

import model.Decoration;
import model.Room;

import java.util.List;

public interface DaoDecoration {

    void insertDecoration(Decoration decoration);
    List<Decoration> getAllDecorations();
    void updateDecoration(Decoration decoration);
    void deleteDecoration(int idDecoration);
    void addDecoToRoom(int idDecoration, int idRoom);
}
