package model;

import java.time.LocalDate;

public class Ticket {
    private int id;
    private Room room;
    private Player player;
    private double price;
    private LocalDate purchaseDate;

    public Ticket() {
    }

    public Ticket(Room room, Player player, double price, LocalDate purchaseDate) {
        this.room = room;
        this.player = player;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Player getPlayer() {
        return player;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", room=" + room.getName() +
                ", player=" + player.getName() +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
