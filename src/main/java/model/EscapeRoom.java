package model;

public class EscapeRoom {
    private int id;
    private String name;

    public EscapeRoom() {
    }

    public EscapeRoom( int id, String name) {
        this.name = name;
        this.id= id;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EscapeRoom{" +
                ", name='" + name + '\'' +
                '}';
    }
}
