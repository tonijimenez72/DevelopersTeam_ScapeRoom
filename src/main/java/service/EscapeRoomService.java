package service;

import model.EscapeRoom;
import java.util.List;

public interface EscapeRoomService {
    void addEscapeRoom(EscapeRoom escapeRoom) throws IllegalStateException;
    EscapeRoom getEscapeRoomById(int id);
    List<EscapeRoom> getAllEscapeRooms();
    void updateEscapeRoom(EscapeRoom escapeRoom);
    void deleteEscapeRoom(int id);
    boolean escapeRoomExists();
}