package dao.impl;

import dao.DaoPlayer;
import database.DatabaseConnection;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPlayerImpl implements DaoPlayer {

    @Override
    public void save(Player player) {
        String query;

        if (player.getId() > 0) {
            query = "UPDATE player SET name = ?, email = ?, subscriber = ?, escape_room_id = ? WHERE id = ?";
        } else {
            query = "INSERT INTO player (name, email, subscriber, escape_room_id) VALUES (?, ?, ?, ?)";
        }

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, player.getName());
            statement.setString(2, player.getEmail());
            statement.setBoolean(3, player.isSubscriber());
            statement.setInt(4, player.getEscapeRoomId());

            if (player.getId() > 0) {
                statement.setInt(5, player.getId());
            }

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Saving player failed, no rows affected.");
            }

            if (player.getId() == 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        player.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating player failed, no ID obtained.");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error saving the player into the database: " + e.getMessage());
        }
    }

    @Override
    public List<Player> getAll() {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getInt("id"));
                player.setName(resultSet.getString("name"));
                player.setEmail(resultSet.getString("email"));
                player.setSubscriber(resultSet.getBoolean("subscriber"));
                player.setEscapeRoomId(resultSet.getInt("escape_room_id"));

                players.add(player);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving players from the database: " + e.getMessage());
        }

        return players;
    }

    @Override
    public Player getById(int id) {
        String query = "SELECT * FROM player WHERE id = ?";
        Player player = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    player = new Player();
                    player.setId(resultSet.getInt("id"));
                    player.setName(resultSet.getString("name"));
                    player.setEmail(resultSet.getString("email"));
                    player.setSubscriber(resultSet.getBoolean("subscriber"));
                    player.setEscapeRoomId(resultSet.getInt("escape_room_id"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving player by ID: " + e.getMessage());
        }

        return player;
    }

    @Override
    public void remove(Player player) {
        String query = "DELETE FROM player WHERE id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, player.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error removing player: " + e.getMessage());
        }
    }
}