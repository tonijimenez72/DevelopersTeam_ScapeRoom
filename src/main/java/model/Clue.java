package model;

import enums.Theme;

public class Clue extends RoomElement{
    private Theme theme;

    public Clue() {
    }

    public Clue(String name, double price, Theme theme) {
        super(name, price);
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
        return String.format(" * %s [Price: %s] [Theme: %s]\n", super.getName(), super.getPrice(), theme);
    }
}