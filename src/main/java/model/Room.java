package model;

import enums.DifficultyLevel;
import enums.Theme;

import java.util.List;

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

    public Room(String name, Theme theme, DifficultyLevel difficultyLevel, String description, double price, List<Clue> clues, List<Decoration> decorations, EscapeRoom escapeRoom) {
        this.name = name;
        this.theme = theme;
        this.difficultyLevel = difficultyLevel;
        this.description = description;
        this.price = price;
        this.available = true;
        this.clues = clues;
        this.decorations = decorations;
        this.escapeRoom = escapeRoom;
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

    public EscapeRoom getEscapeRoom() {
        return escapeRoom;
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

    public void setEscapeRoom(EscapeRoom escapeRoom) {
        this.escapeRoom = escapeRoom;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    public void setDecorations(List<Decoration> decorations) {
        this.decorations = decorations;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme=" + theme +
                ", difficultyLevel=" + difficultyLevel +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", escapeRoom=" + (escapeRoom != null ? escapeRoom.getName() : "None") +
                ", clues=" + clues +
                ", decorations=" + decorations +
                '}';
    }
}