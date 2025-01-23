package model;

import enums.DifficultyLevel;
import enums.Theme;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Room {
    private int id;
    private String name;
    private Theme theme;
    private DifficultyLevel difficultyLevel;
    private String description;
    private double price;
    private boolean available;
    private EscapeRoom escapeRoom;
    protected List<Clue> clues;
    protected List<Decoration> decorations;

    public Room() {
    }

    public Room(String name, Theme theme, DifficultyLevel difficultyLevel, String description, double price, List<Clue> clues, List<Decoration> decorations) {
        this.name = name;
        this.theme = theme;
        this.difficultyLevel = difficultyLevel;
        this.description = description;
        this.price = price;
        this.available = true;
        this.clues = clues;
        this.decorations = decorations;
    }

    public Room(String name, Theme theme, DifficultyLevel difficultyLevel, double price) {
        this.name = name;
        this.theme = theme;
        this.difficultyLevel = difficultyLevel;
        this.price = price;
        this.description = "";
        this.available = true;
        this.clues = new ArrayList<>();
        this.decorations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Theme getTheme() {
        return theme;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public List<Clue> getClues() {
        return clues;
    }

    public List<Decoration> getDecorations() {
        return decorations;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    public void setDecorations(List<Decoration> decorations) {
        this.decorations = decorations;
    }

    @Override
    public String toString() {
        String formattedClues = clues.stream()
                .map(clue -> String.format(" * %s [Price: %.2f] [Theme: %s]", clue.getName(), clue.getPrice(), clue.getTheme()))
                .collect(Collectors.joining("\n"));

        String formattedDecorations = decorations.stream()
                .map(decoration -> String.format(" * %s [Price: %.2f] [Material: %s]", decoration.getName(), decoration.getPrice(), decoration.getMaterial()))
                .collect(Collectors.joining("\n"));

        return String.format("Room:\n * %s [Price: %.2f] [Theme: %s] [Difficulty: %s]\n", name, price, theme, difficultyLevel) +
                "Clues:\n" + (formattedClues.isEmpty() ? "None" : formattedClues) + "\n" +
                "Decorations:\n" + (formattedDecorations.isEmpty() ? "None" : formattedDecorations);
    }

}