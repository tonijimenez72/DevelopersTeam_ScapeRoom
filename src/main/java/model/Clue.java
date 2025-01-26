package model;

import enums.Theme;

public class Clue extends RoomElement{
    private Theme theme;
    private boolean available;
    private int room_id;

    public Clue() {
    }

    public Clue(int id,String name, double price, Theme theme) {

        super(id,name, price);
        this.theme = theme;
        this.available=true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public int getRoomId() {
        return room_id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setRoomId(int room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return String.format(" * %s [Price: %s] [Theme: %s]\n", super.getName(), super.getPrice(), theme);
    }
}