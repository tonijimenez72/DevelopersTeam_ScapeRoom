package dao;

import model.Clue;
import model.Room;

import java.util.List;

public interface DaoClue {

    void addClue(Clue clue);

    List<Clue> getAllClues();

    void updateClue(Clue clue);

    void deleteClue(Clue clue);

    void addClueToRoom(Clue clue, Room room);
}
