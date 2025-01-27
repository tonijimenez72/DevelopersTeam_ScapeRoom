package service;

import model.Clue;

import java.util.List;

public interface ClueService {
    void createClue(Clue clue);
    List<Clue> getAllClues();
    Clue getClueById(int id);
    void updateClueStatus(int id, boolean available);
    void removeClue(int id);
}