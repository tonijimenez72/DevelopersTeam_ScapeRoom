package dao.impl;

import dao.DaoRoomPlayer;
import database.DatabaseConnection;
import model.Room;
import model.Player;
import model.RoomPlayer;
import enums.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoRoomPlayerImpl implements DaoRoomPlayer {
    public static final String SAVE_QUERY = "INSERT INTO player_has_room (player_id, room_id, is_solved) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE is_solved = ?";
    public static final String GET_BY_ID_QUERY = "SELECT * FROM player_has_room WHERE id = ?";
    public static final String GET_ALL_QUERY = "SELECT * FROM player_has_room";
    public static final String DELETE_QUERY = "DELETE FROM player_has_room WHERE player_id = ? AND room_id = ?";
    public static final String  GET_ROOMS_BY_PLAYER_ID= "SELECT pr.id, pr.player_id, pr.room_id, pr.is_solved, " +
            "r.name AS room_name, r.price, r.theme, r.difficulty_level " +
            "FROM player_has_room pr " +
            "JOIN room r ON pr.room_id = r.id " +
            "WHERE pr.player_id = ?";

    public static final String GET_PLAYER_BY_ROOM_ID = "SELECT pr.id, pr.player_id, pr.room_id, pr.is_solved, " +
            "p.name AS player_name, p.email, p.subscriber " +
            "FROM player_has_room pr " +
            "JOIN player p ON pr.player_id = p.id " +
            "WHERE pr.room_id = ?";

    @Override
    public void save(RoomPlayer roomPlayer) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {

            statement.setInt(1, roomPlayer.getPlayerId());
            statement.setInt(2, roomPlayer.getRoomId());
            statement.setBoolean(3, roomPlayer.isSolved());
            statement.setBoolean(4, roomPlayer.isSolved());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error inserting/updating player-room relationship in database: " + e.getMessage());
        }
    }

    @Override
    public RoomPlayer getById(int id) {
        RoomPlayer roomPlayer = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    roomPlayer = mapResultSetToRoomPlayer(resultSet);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error getting player-room relationship by id: " + e.getMessage());
        }

        return roomPlayer;
    }

    @Override
    public List<RoomPlayer> getAll() {
        List<RoomPlayer> roomPlayers = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                roomPlayers.add(mapResultSetToRoomPlayer(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error getting all player-room relationships: " + e.getMessage());
        }

        return roomPlayers;
    }

    @Override
    public void remove(RoomPlayer roomPlayer) {

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, roomPlayer.getPlayerId());
            statement.setInt(2, roomPlayer.getRoomId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error removing player-room relationship: " + e.getMessage());
        }
    }

    private RoomPlayer mapResultSetToRoomPlayer(ResultSet resultSet) throws SQLException {
        RoomPlayer roomPlayer = new RoomPlayer();
        roomPlayer.setId(resultSet.getInt("id"));
        roomPlayer.setPlayerId(resultSet.getInt("player_id"));
        roomPlayer.setRoomId(resultSet.getInt("room_id"));
        roomPlayer.setSolved(resultSet.getBoolean("is_solved"));
        return roomPlayer;
    }
}