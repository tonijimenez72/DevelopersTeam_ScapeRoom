package model;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private Integer roomId;  // Debe ser Integer en lugar de int
    private Integer playerId; // Debe ser Integer en lugar de int
    private double totalPrice;
    private LocalDateTime createdAt;

    public Ticket(Integer roomId, Integer playerId, double totalPrice) {
        this.roomId = roomId;
        this.playerId = playerId;
        this.totalPrice = totalPrice;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Ticket ID: %d | Room ID: %s | Player ID: %s | Total Price: %.2f | Created At: %s",
                id, (roomId != null ? roomId : "Deleted"),
                (playerId != null ? playerId : "Deleted"),
                totalPrice, createdAt
        );
    }
}
