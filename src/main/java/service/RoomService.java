package service;

import model.Clue;
import model.Decoration;
import model.Room;

public interface RoomService {
    void addClue(Room room, Clue clue);
    void addDecoration(Room room, Decoration decoration);
}