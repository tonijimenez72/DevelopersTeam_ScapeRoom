package model;

public abstract class RoomElement {
    private int id;
    private String name;
    private double price;
    private int roomId;

    public RoomElement() {}

    public RoomElement(String name, double price, int roomId) {

        this.name = name;
        this.price = price;
        this.roomId = roomId;
    }

    public int  getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getRoomId() {
        return roomId;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setRoomId(int roomId) {this.roomId = roomId;}
}