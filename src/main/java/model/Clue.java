package model;

import enums.Theme;

public class Clue extends RoomElement {

    private Theme theme;

    public Clue() {}

    public Clue(String name, double price, Theme theme, int roomId) {
        super(name, price, roomId);
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return String.format(" ID: %S | Name: %s | Price: %.2f | Theme: %s | Room ID: %s", super.getId(), super.getName(), super.getPrice(), theme, super.getRoomId());
    }
}