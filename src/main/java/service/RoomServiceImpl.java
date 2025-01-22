package service;

import model.Clue;
import model.Decoration;
import model.Room;

public class RoomServiceImpl implements RoomService{
    @Override
    public void addClue(Room room, Clue clue) {
        if (!clue.isAvailable()) {
            System.out.println("Clue is unavailable.");
            return;
        }
        room.getClues().add(clue);
        System.out.println("Clue added to room.");
    }

    @Override
    public void addDecoration(Room room, Decoration decoration) {
        if (!decoration.isAvailable()) {
            System.out.println("Decoration is unavailable.");
            return;
        }
        room.getDecorations().add(decoration);
        System.out.println("Decoration added to room.");
    }
}