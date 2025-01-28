package model;

import enums.DifficultyLevel;
import enums.Theme;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Room {
    private static int counter = 1;
    private int id;
    private String name;
    private Theme theme;
    private DifficultyLevel difficultyLevel;
    private double price;
    private boolean available;
    private List<Clue> clues;
    private List<Decoration> decorations;
    private List<Player> players;


    public Room() {}

    public Room(String name, Theme theme, DifficultyLevel difficultyLevel, double price) {
        this.id = counter++;
        this.name = name;
        this.theme = theme;
        this.difficultyLevel = difficultyLevel;
        this.price = price;
        this.available = true;
        this.clues = new ArrayList<>();
        this.decorations = new ArrayList<>();
        this.players = new ArrayList<>();
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
    public List<Player> getPlayers() {
        return players;
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
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addClueToRoom(Clue clue) {
        if (clue == null) {
            throw new IllegalArgumentException("Clue cannot be null.");
        }
        if (!clues.contains(clue)) {
            clues.add(clue);
        }
    }
    public void addDecorationToRoom(Decoration decoration) {
        if (decoration == null) {
            throw new IllegalArgumentException("Decoration cannot be null.");
        }
        if (!decorations.contains(decoration)) {
            decorations.add(decoration);
        }
    }

    public void addPlayerToRoom(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public double getTotalPrice() {
        return price
                + clues.stream().mapToDouble(Clue::getPrice).sum()
                + decorations.stream().mapToDouble(Decoration::getPrice).sum();
    }

    @Override
    public String toString() {
        return String.format(
                "Room:\n ID: %s | Name: %s | Price: %.2f | Theme: %s | Difficulty: %s | Available: %s", id, name, price, theme, difficultyLevel, available)
                + "\nClues:\n" + (clues.isEmpty() ? "None" : clues.stream().map(Clue::toString).collect(Collectors.joining("\n")))
                + "\nDecorations:\n" + (decorations.isEmpty() ? "None" : decorations.stream().map(Decoration::toString).collect(Collectors.joining("\n")))
                + String.format("\nTotal Price: %.2f", getTotalPrice());
    }
}