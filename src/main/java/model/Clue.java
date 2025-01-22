package model;

import enums.Theme;

public class Clue extends RoomElement{
    private Theme theme;

    public Clue() {
    }

    public Clue(String name, double price, Room room, Theme theme) {
        super(name, price, room);
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
        return "Clue{" +
                "theme=" + theme +
                '}';
    }
}
