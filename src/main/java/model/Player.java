package model;

import utils.NotificationObserver;

public class Player implements NotificationObserver {
    private int id;
    private String name;
    private String email;
    private boolean subscriber;
    private int escapeRoomId;

    public Player(){
    }

    public Player(String name, String email, int escapeRoomId) {
        this.name = name;
        this.email = email;
        this.subscriber = false;
        this.escapeRoomId = escapeRoomId;
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
    public boolean isSubscriber() {
        return subscriber;
    }
    public int getEscapeRoomId() {
        return escapeRoomId;
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
    public void setEscapeRoomId(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }

    @Override
    public void update(String message) {
        if (subscriber) {
            System.out.println("Hi, " + name + ". " + message);
        }
    }

    @Override
    public String toString() {
        return String.format("Playe ID: %s | Name: %s | Email: %s | Subscriber: %s", id, name, email, subscriber);
    }
}
