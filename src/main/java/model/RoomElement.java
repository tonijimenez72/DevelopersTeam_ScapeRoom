package model;

public abstract class RoomElement {
    private String id;
    private String name;
    private double price;
    private boolean available;
    private Room room;

    public RoomElement() {
    }

    public RoomElement(String name, double price, Room room) {
        this.name = name;
        this.price = price;
        this.available = true;
        this.room = room;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public boolean isAvailable() {
        return available;
    }
    public Room getRoom() {
        return room;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "RoomElement{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", room=" + room +
                '}';
    }
}
