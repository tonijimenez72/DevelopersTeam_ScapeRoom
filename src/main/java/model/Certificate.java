package model;

import java.time.LocalDate;

public class Certificate {
    private int id;
    private Player player;
    private Room room;
    private LocalDate completionDate;
    private String achievement;

    public Certificate() {
    }

    public Certificate(Player player, Room room, LocalDate completionDate, String achievement) {
        this.player = player;
        this.room = room;
        this.completionDate = completionDate;
        this.achievement = achievement;
    }

    public int getId() {
        return id;
    }
    public Player getPlayer() {
        return player;
    }
    public Room getRoom() {
        return room;
    }
    public LocalDate getCompletionDate() {
        return completionDate;
    }
    public String getAchievement() {
        return achievement;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", player=" + player +
                ", room=" + room +
                ", completionDate=" + completionDate +
                ", achievement='" + achievement + '\'' +
                '}';
    }
}