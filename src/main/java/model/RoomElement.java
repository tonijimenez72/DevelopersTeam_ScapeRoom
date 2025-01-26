package model;

public abstract class RoomElement {
    private int id;
    private String name;
    private double price;
    private boolean available;

    public RoomElement() {
    }

    public RoomElement(int id,String name, double price) {
        this.name = name;
        this.price = price;
        this.available = true;
        this.id=id;
    }

    public int getId() {
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

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "RoomElement{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
