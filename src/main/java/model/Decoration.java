package model;

public class Decoration extends RoomElement{

    private String material;

    public Decoration() {
    }

    public Decoration(String name, double price, String material) {
        super(name, price);
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
        return String.format(" * %s [Price: %s] [Material: %s]\n", super.getName(), super.getPrice(), material);
    }
}