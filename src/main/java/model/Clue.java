package model;

import enums.Theme;

public class Clue extends RoomElement {
    private static int counter = 1;
    private int id;
    private Theme theme;

    public Clue() {}

    public Clue(String name, double price, Theme theme) {
        super(name, price);
        this.id = counter++;
        this.theme = theme;
    }

    public int getId() {
        return id;
    }
    public Theme getTheme() {
        return theme;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return String.format(" ID: %S | Name: %s | Price: %.2f | Theme: %s | Available: %s", id, super.getName(), super.getPrice(), theme, super.isAvailable());
    }
}