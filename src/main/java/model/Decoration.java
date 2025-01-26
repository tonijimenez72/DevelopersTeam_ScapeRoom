package model;

public class Decoration extends RoomElement{


    private String material;
    private  boolean available;
    private  int roomId;

    public Decoration() {
    }

    public Decoration(int id, String name, double price, String material) {
        super(id,name, price);
        this.material = material;
        this.available=true;

    }



    @Override
    public boolean isAvailable() {
        return available;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getMaterial() {
        return material;
    }


    public void setMaterial(String material) {
        this.material = material;
    }


    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }



    @Override
    public String toString() {
        return String.format(" * %s [Price: %s] [Material: %s]\n", super.getName(), super.getPrice(), material);
    }
}