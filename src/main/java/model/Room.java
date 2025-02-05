package model;

import enums.*;

import java.util.List;
import java.util.stream.Collectors;

public class Room {
    private int id;
    private String name;
    private double price;       // Costo material
    private double ticketPrice; // Precio del ticket basado en la dificultad
    private Theme theme;
    private DifficultyLevel difficultyLevel;
    private int escapeRoomId;
    List<Clue> clues;
    List<Decoration> decorations;

    public Room() {}

    public Room(String name, double price, Theme theme, DifficultyLevel difficultyLevel, int escapeRoomId) {
        this.name = name;
        this.price = price;
        this.theme = theme;
        this.difficultyLevel = difficultyLevel;
        this.escapeRoomId = escapeRoomId;
        this.ticketPrice = difficultyLevel.getAmount();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getTicketPrice() { return ticketPrice; }
    public Theme getTheme() { return theme; }
    public DifficultyLevel getDifficultyLevel() { return difficultyLevel; }
    public int getEscapeRoomId() { return escapeRoomId; }
    public List<Clue> getClues() { return clues; }
    public List<Decoration> getDecorations() { return decorations; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        this.ticketPrice = difficultyLevel.getAmount();
    }

    public void setTheme(Theme theme) { this.theme = theme; }
    public void setEscapeRoomId(int escapeRoomId) { this.escapeRoomId = escapeRoomId; }
    public void setClues(List<Clue> clues) { this.clues = clues; }
    public void setDecorations(List<Decoration> decorations) { this.decorations = decorations; }

    public double getTotalPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        String cluesList = (clues != null && !clues.isEmpty())
                ? clues.stream().map(Clue::toString).collect(Collectors.joining("\n"))
                : " None";

        String decorationsList = (decorations != null && !decorations.isEmpty())
                ? decorations.stream().map(Decoration::toString).collect(Collectors.joining("\n"))
                : " None";

        return String.format(
                "Room: ID: %s | Name: %s | Price: %.2f | Ticket Price: %.2f | Theme: %s | Difficulty: %s%n Clues:%n%s%n Decorations:%n%s%nTotal Price: %.2f%n",
                id, name, price, ticketPrice, theme, difficultyLevel, cluesList, decorationsList, getTotalPrice()
        );
    }
}
