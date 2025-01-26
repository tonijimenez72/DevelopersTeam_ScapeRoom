package service;

import model.Clue;
import model.Decoration;
import model.Room;

import java.util.List;

public interface EscapeRoomService {
    void createEscapeRoom(String name,int id);
    String displayEscapeRoomInventory(boolean onlyAvailable);
    double calculateInventoryValue();
    List<Room> getRooms(int id_scape_room);
}
