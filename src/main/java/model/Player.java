package model;

import utils.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class Player implements NotificationObserver {
    private int id;
    private String name;
    private String email;
    private String surname;
    private int phone;
    private boolean subscriber;
    private List<Room> playedRooms;
    private List<Room> solvedRooms;

    public Player() {
        this.playedRooms = new ArrayList<>();
        this.solvedRooms = new ArrayList<>();
    }

    public Player(int id,String name,String surname, String email, int phone) {

        this.id=id;
        this.name = name;
        this.email = email;
        this.surname=surname;
        this.phone=phone;
        this.subscriber = false;
        this.playedRooms = new ArrayList<>();
        this.solvedRooms = new ArrayList<>();
    }

    public String getSurname() {
        return surname;
    }

    public int getPhone() {
        return phone;
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

    public List<Room> getPlayedRooms() {
        return new ArrayList<>(playedRooms);
    }

    public List<Room> getSolvedRooms() {
        return new ArrayList<>(solvedRooms);
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void addPlayedRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        if (!playedRooms.contains(room)) {
            playedRooms.add(room);
        }
    }

    public void addSolvedRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        if (!solvedRooms.contains(room)) {
            solvedRooms.add(room);
        }
    }

    @Override
    public void update(String message) {
        if (subscriber) {
            System.out.println("Hi, " + name + ". " + message);
        }
    }

    @Override
    public String toString() {
        String playedRoomsNames = playedRooms.isEmpty() ? "None" : String.join(", ", playedRooms.stream().map(Room::getName).toList());
        String solvedRoomsNames = solvedRooms.isEmpty() ? "None" : String.join(", ", solvedRooms.stream().map(Room::getName).toList());

        return String.format("Player{name='%s', email='%s', subscriber=%s, playedRooms=[%s], solvedRooms=[%s]}",
                name, email, subscriber, playedRoomsNames, solvedRoomsNames);
    }
}