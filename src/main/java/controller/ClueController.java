package controller;

import model.Clue;
import model.Room;
import service.ClueService;

import java.util.List;

public class ClueController {
    private final ClueService clueService;

    public ClueController(ClueService clueService) {
        this.clueService = clueService;
    }

    public void addClue(Clue clue) {
        try {
            clueService.createClue(clue);
            System.out.println("New clue creaated: " + clue);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding clue: " + e.getMessage());
        }
    }

    public void showAllClues() {
        List<Clue> clues = clueService.getAllClues();
        if (clues.isEmpty()) {
            System.out.println("None.");
        } else {
            clues.forEach(System.out::println);
        }
    }

    public void showAllAvailableClues() {
        List<Clue> clues = clueService.getAvailableClues();
        int countAvailables = clues.size();
        if (clues.isEmpty()) {
            System.out.println("Available clues: None.");
        } else {
            System.out.printf("Available clues: %s%n", countAvailables);
            clues.forEach(System.out::println);
        }
    }

    public Clue getClueById(int id) {
        try {
            return clueService.getClueById(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void updateClueStatus(int id, boolean available) {
        try {
            clueService.updateClueStatus(id, available);
            System.out.println("Updated status for clue.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating clue status: " + e.getMessage());
        }
    }

    public void removeClue(int id) {
        try {
            clueService.removeClue(id);
            System.out.println("Clue removed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing clue: " + e.getMessage());
        }
    }
}