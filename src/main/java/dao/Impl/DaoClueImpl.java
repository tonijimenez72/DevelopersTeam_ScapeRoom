package dao.Impl;

import dao.DaoClue;
import database.DatabaseConnection;
import enums.Theme;
import model.Clue;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoClueImpl implements DaoClue {

    public void addClue(Clue clue) {
        String query = "INSERT INTO Clue (id, name, theme, price, available) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, clue.getId());
            statement.setString(2, clue.getName());
            statement.setString(3, clue.getTheme().name());
            statement.setDouble(4, clue.getPrice());
            statement.setBoolean(5, clue.isAvailable());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding Clue to the db: " + e.getMessage());

        }
    }

    public void addClueToRoom(Clue clue, Room room) {

        String sql = "UPDATE Clue SET roomId = ?, available = ? WHERE id = ? AND available = 1";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, room.getId());
            statement.setBoolean(3,false);
            statement.setInt(2, clue.getId());


            statement.executeUpdate();
        } catch (SQLException e) {

            System.err.println("Error assigning Room to the Clue in the db: " + e.getMessage());
        }
    }


    public List<Clue> getAllClues() {
        List<Clue> clues = new ArrayList<>();
        String sql = "SELECT * FROM Clue";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Clue clue = new Clue();

                clue.setId(resultSet.getInt("id"));
                clue.setName(resultSet.getString("name"));
                clue.setTheme(Theme.valueOf(resultSet.getString("theme")));
                clue.setPrice(resultSet.getDouble("price"));
                clue.setAvailable(resultSet.getBoolean("available"));
                clue.setRoomId(resultSet.getInt("roomId"));
                clues.add(clue);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all Clues from the db: " + e.getMessage());
        }
        return clues;
    }


    public void  updateClue(Clue clue) {
        String sql = "UPDATE Clue SET name = ?, theme = ?, price = ?, available = ?, roomId = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, clue.getName());
            statement.setString(2, clue.getTheme().name());
            statement.setDouble(3, clue.getPrice());
            statement.setBoolean(4, clue.isAvailable());
            statement.setInt(5, clue.getRoomId());
            statement.setInt(6, clue.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating Clue in the db: " + e.getMessage());
        }
    }

    public void updateClueAvailability(Clue clue, boolean available) {
        String sql = "UPDATE Clue SET available = ? WHERE id = ?";
        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, available); // Nuevo valor para el campo 'available'
            statement.setInt(2, clue.getId()); // ID del clue a actualizar
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating the available of the chosen clue in the db" + e.getMessage());        }
    }


    public void deleteClue(Clue clue) {
        String sql = "DELETE FROM Clue WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clue.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting the Clue in the db: " + e.getMessage());
        }
    }


}
