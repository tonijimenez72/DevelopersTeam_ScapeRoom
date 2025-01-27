package service.impl;

import model.Clue;
import model.Room;
import service.ClueService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClueServiceImpl implements ClueService {
    private final List<Clue> clues = new ArrayList<>();

    @Override
    public void createClue(Clue clue) {
        if (clue == null) {
            throw new IllegalArgumentException("Clue cannot be null.");
        }
        clues.add(clue);
    }

    @Override
    public List<Clue> getAllClues() {
        return new ArrayList<>(clues);
    }

    @Override
    public List<Clue> getAvailableClues() {
        return clues.stream()
                .filter(Clue::isAvailable)
                .collect(Collectors.toList());
    }


    @Override
    public Clue getClueById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Clue ID must be greater than 0.");
        }
        return clues.stream()
                .filter(clue -> clue.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Clue not found for ID: " + id)
                );
    }


    @Override
    public void updateClueStatus(int id, boolean available) {
        Clue clue = getClueById(id);
        clue.setAvailable(available);
        System.out.printf("Room status updated to: %s%n", available ? "available" : "not available");
    }

    @Override
    public void removeClue(int id) {
        Clue clue = getClueById(id);
        clues.remove(clue);
    }
}