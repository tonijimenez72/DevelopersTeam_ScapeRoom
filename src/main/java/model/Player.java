package model;

import utils.NotificationObserver;

public class Player implements NotificationObserver {
    private int id;
    private String name;
    private String email;
    private boolean subscriber;
    private Room room;

    public Player() {
    }

    public Player(Room room, String name, String email) {
        this.room = room;
        this.name = name;
        this.email = email;
        this.subscriber = false;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public Room getRoom() {
        return room;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isSubscriber() {
        return subscriber;
    }

    public void update(String message) {
        if (subscriber) {
            System.out.println("Hi, " + name + ". " + message);
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", subscriber=" + subscriber +
                ", room=" + room +
                '}';
    }
}
