package service;

import model.Clue;

import java.util.List;

public interface ClueService {
    void addClue(Clue clue);
    List<Clue> getAllClues();
    void updateClueAvailability(Clue clue, boolean available);
    void removeClue(Clue clue);
}