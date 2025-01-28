package model;

public class Decoration extends RoomElement{
    private static int counter = 1;
    private int id;
    private String material;
    private int roomId;


    public Decoration() {}

    public Decoration(String name, double price, String material) {
        super(name, price);
        this.id = counter++;
        this.material = material;
    }

    public int getRoomId() {
        return roomId;
    }


    public int getId() {
        return id;
    }
    public String getMaterial() {
        return material;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }


    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return String.format(" ID: %s Name: %s | Price: %.2f | Theme: %s | Available: %s", id, super.getName(), super.getPrice(), material, super.isAvailable());
    }
}