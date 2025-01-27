package model;

import enums.Theme;

public class Clue extends RoomElement {
    private static int counter = 1;
    private int id;
    private Theme theme;
    private int roomId;

    public Clue() {}

    public Clue(String name, double price, Theme theme) {
        super(name, price);
        this.id = counter++;
        this.theme = theme;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }
    public Theme getTheme() {
        return theme;
    }
    public int getRoomId() {
        return roomId;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTheme(Theme theme) {
        this.theme = theme;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return String.format(" ID: %S | Name: %s | Price: %.2f | Theme: %s | Available: %s", id, super.getName(), super.getPrice(), theme, super.isAvailable());
    }
}