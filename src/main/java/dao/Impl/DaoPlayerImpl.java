package dao.Impl;

import dao.DaoPlayer;
import database.DatabaseConnection;
import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPlayerImpl implements DaoPlayer {

    public void addPlayer(Player player) {
        String query = "INSERT INTO player (id, name, surname, email, phone, subscriber) VALUES (?, ?, ?, ?, ?,?)";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, player.getId());
            stmt.setString(2, player.getName());
            stmt.setString(3, player.getSurname());
            stmt.setString(4, player.getEmail());
            stmt.setInt(5, player.getPhone());
            stmt.setBoolean(6,player.isSubscriber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding the player in the db: " + e.getMessage());
        }
    }

    public List<Player> getAllPlayers()  {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("name"));
                player.setSurname(rs.getString("surname"));
                player.setEmail(rs.getString("email"));
                player.setPhone(rs.getInt("phone"));
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error showing all the players from the db: " + e.getMessage());
        }
        return players;
    }


    public void updatePlayer(Player player) {
        String query = "UPDATE player SET name = ?, surname = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getSurname());
            stmt.setString(3, player.getEmail());
            stmt.setInt(4, player.getPhone());
            stmt.setInt(5, player.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating the player in the db: " + e.getMessage());
        }
    }

    public void addSubscriber(Player player) {
        String query = "UPDATE player SET subscriber = ? WHERE id = ?";
        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setBoolean(1, true); // Cambiamos el campo subscriber a true
            stmt.setInt(2, player.getId()); // Usamos el ID del player para identificar el registro
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado a true  de suscripción del jugador en la db: " + e.getMessage());
        }
    }

    public void deleteSubscriber(Player player) {
        String query = "UPDATE player SET subscriber = ? WHERE id = ?";
        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setBoolean(1, false); // Cambiamos el campo subscriber a true
            stmt.setInt(2, player.getId()); // Usamos el ID del player para identificar el registro
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado a false de la suscripción del jugador en la db: " + e.getMessage());
        }
    }

    public void deletePlayer(Player player)  {
        String query = "DELETE FROM player WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, player.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting the chosen player in the db" + e.getMessage());
        }
    }
}
