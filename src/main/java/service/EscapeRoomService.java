package service;

import model.Clue;
import model.Decoration;
import model.Room;

import java.util.List;

public interface EscapeRoomService {
    void createEscapeRoom(String name);
    String displayEscapeRoomInventory(boolean onlyAvailable);
    double calculateInventoryValue();
    void addRoom(Room room);
    void addClue(Clue clue);
    void addDecoration(Decoration decoration);
    List<Room> getRooms();
}
