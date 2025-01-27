package model;

public abstract class RoomElement {
    private String name;
    private double price;
    private boolean available;

    public RoomElement() {}

    public RoomElement(String name, double price) {
        this.name = name;
        this.price = price;
        this.available = true;
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

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
