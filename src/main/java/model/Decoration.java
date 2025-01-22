package model;

public class Decoration extends RoomElement{

    private String material;

    public Decoration() {
    }

    public Decoration(String name, double price, Room room, String material) {
        super(name, price, room);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Decoration{" +
                "material='" + material + '\'' +
                '}';
    }
}