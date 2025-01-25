package service.impl;

import model.Clue;
import service.ClueService;

import java.util.ArrayList;
import java.util.List;

public class ClueServiceImpl implements ClueService {
    private final List<Clue> clues = new ArrayList<>();

    @Override
    public void addClue(Clue clue) {
        if (clue == null) {
            throw new IllegalArgumentException("Clue cannot be null.");
        }
        clues.add(clue);
        System.out.println("Clue added: " + clue.getName());
    }

    @Override
    public List<Clue> getAllClues() {
        return new ArrayList<>(clues);
    }

    @Override
    public void updateClueAvailability(Clue clue, boolean available) {
        clue.setAvailable(available);
        System.out.println("\nClue: " + clue.getName() + " [New status: " + (available ? "available]" : "not available]"));
    }

    @Override
    public void removeClue(Clue clue) {
        clues.remove(clue);
        System.out.println("Clue removed: " + clue.getName());
    }
}