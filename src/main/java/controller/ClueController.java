package controller;

import model.Clue;
import service.ClueService;

import java.util.List;

public class ClueController {
    private final ClueService clueService;

    public ClueController(ClueService clueService) {
        this.clueService = clueService;
    }

    public void addClue(Clue clue) {
        try {
            clueService.addClue(clue);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding clue: " + e.getMessage());
        }
    }

    public void showAllClues() {
        List<Clue> clues = clueService.getAllClues();
        if (clues.isEmpty()) {
            System.out.println("No clues available.");
        } else {
            clues.forEach(System.out::println);
        }
    }

    public void updateClueAvailability(Clue clue, boolean available) {
        try {
            clueService.updateClueAvailability(clue, available);
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating clue availability: " + e.getMessage());
        }
    }

    public void removeClue(Clue clue) {
        try {
            clueService.removeClue(clue);
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing clue: " + e.getMessage());
        }
    }
}