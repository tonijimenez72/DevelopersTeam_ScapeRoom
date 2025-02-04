package model;

public class Decoration extends RoomElement {
    private String material;

    public Decoration() {}

    public Decoration(String name, double price, String material, int roomId) {
        super(name, price, roomId);
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
        return String.format(" ID: %S | Name: %s | Price: %.2f | Material: %s", super.getId(), super.getName(), super.getPrice(), material);
    }
}