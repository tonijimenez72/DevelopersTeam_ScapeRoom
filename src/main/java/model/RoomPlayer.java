package model;

public class RoomPlayer {
    private int id;
    private int roomId;
    private int playerId;
    private boolean isSolved;
    private Room room;
    private Player player;

    public RoomPlayer() {
    }

    public RoomPlayer(int playerId, int roomId, boolean isSolved) {
        this.playerId = playerId;
        this.roomId = roomId;
        this.isSolved = isSolved;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public Room getRoom() {
        return room;
    }

    public Player getPlayer() {
        return player;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setSolved(boolean solved) {
        this.isSolved = solved;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return String.format(
                "RoomPlayer: ID: %d | Room: %s | Player: %s | Solved: %b",
                id,
                (room != null) ? room.getName() : "N/A",
                (player != null) ? player.getName() : "N/A",
                isSolved
        );
    }
}