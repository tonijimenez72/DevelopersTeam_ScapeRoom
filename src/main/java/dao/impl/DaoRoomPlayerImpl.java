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

    @Override
    public void save(RoomPlayer roomPlayer) {
        String query = "INSERT INTO player_has_room (player_id, room_id, is_solved) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE is_solved = ?";
       /*String query;
        if (roomPlayer.getId() >0) {
            query = "UPDATE INTO player_has_room  SET player_id = ?, room_id = ?, is_solved  = ?) VALUES (?, ?, ?)";
        } else {
            query = "INSERT INTO player_has_room (player_id, room_id, is_solved) VALUES (?, ?, ?) ";
        }*/

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

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
        String query = "SELECT * FROM player_has_room WHERE id = ?";
        RoomPlayer roomPlayer = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

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
        String query = "SELECT * FROM player_has_room";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
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
        String query = "DELETE FROM player_has_room WHERE player_id = ? AND room_id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomPlayer.getPlayerId());
            statement.setInt(2, roomPlayer.getRoomId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error removing player-room relationship: " + e.getMessage());
        }
    }

    @Override
    public List<RoomPlayer> getRoomsByPlayerId(int playerId) {
        List<RoomPlayer> roomPlayers = new ArrayList<>();
        String query = "SELECT pr.id, pr.player_id, pr.room_id, pr.is_solved, " +
                "r.name AS room_name, r.price, r.theme, r.difficulty_level " +
                "FROM player_has_room pr " +
                "JOIN room r ON pr.room_id = r.id " +
                "WHERE pr.player_id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RoomPlayer roomPlayer = new RoomPlayer();
                roomPlayer.setId(resultSet.getInt("id"));
                roomPlayer.setPlayerId(resultSet.getInt("player_id"));
                roomPlayer.setRoomId(resultSet.getInt("room_id"));
                roomPlayer.setSolved(resultSet.getBoolean("is_solved"));

                Room room = new Room();
                room.setId(resultSet.getInt("room_id"));
                room.setName(resultSet.getString("room_name"));
                room.setPrice(resultSet.getDouble("price"));
                room.setTheme(Theme.valueOf(resultSet.getString("theme")));
                room.setDifficultyLevel(DifficultyLevel.valueOf(resultSet.getString("difficulty_level")));

                roomPlayer.setRoom(room);
                roomPlayers.add(roomPlayer);
            }

        } catch (SQLException e) {
            System.err.println("Error getting rooms for player ID " + playerId + ": " + e.getMessage());
        }

        return roomPlayers;
    }

    @Override
    public List<RoomPlayer> getPlayersByRoomId(int roomId) {
        List<RoomPlayer> roomPlayers = new ArrayList<>();
        String query = "SELECT pr.id, pr.player_id, pr.room_id, pr.is_solved, " +
                "p.name AS player_name, p.email, p.subscriber " +
                "FROM player_has_room pr " +
                "JOIN player p ON pr.player_id = p.id " +
                "WHERE pr.room_id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RoomPlayer roomPlayer = new RoomPlayer();
                roomPlayer.setId(resultSet.getInt("id"));
                roomPlayer.setPlayerId(resultSet.getInt("player_id"));
                roomPlayer.setRoomId(resultSet.getInt("room_id"));
                roomPlayer.setSolved(resultSet.getBoolean("is_solved"));

                Player player = new Player();
                player.setId(resultSet.getInt("player_id"));
                player.setName(resultSet.getString("player_name"));
                player.setEmail(resultSet.getString("email"));
                player.setSubscriber(resultSet.getBoolean("subscriber"));

                roomPlayer.setPlayer(player);
                roomPlayers.add(roomPlayer);
            }

        } catch (SQLException e) {
            System.err.println("Error getting players for room ID " + roomId + ": " + e.getMessage());
        }

        return roomPlayers;
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