package dao.Impl;

import dao.DaoDecoration;
import database.DatabaseConnection;
import model.Decoration;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoDecorationImpl implements DaoDecoration {


    public void insertDecoration(Decoration decoration) {
        String query = "INSERT INTO decorations (id, name, material, price, available) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, decoration.getId());
            preparedStatement.setString(2, decoration.getName());
            preparedStatement.setString(3, decoration.getMaterial());
            preparedStatement.setDouble(4, decoration.getPrice());
            preparedStatement.setBoolean(5, true);
            preparedStatement.executeUpdate() ;
        } catch (SQLException e) {
            System.out.println("Error adding the decoration object in the db: "+e.getMessage());
        }
    }

    public void addDecoToRoom(Decoration decoration, Room room) {
        String query = "UPDATE decorations SET roomId = ?, available = ? WHERE id = ? AND available = 1";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, room.getId());
            preparedStatement.setBoolean(2,false);
            preparedStatement.setInt(2, decoration.getId());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error assigning room to the decoration in the db: "+e.getMessage());
        }
    }

    public List<Decoration> getAllDecorations() {
        List<Decoration> decorations = new ArrayList<>();
        String query = "SELECT * FROM decorations";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery(query)) {

            while (resultSet.next()) {
                Decoration decoration = new Decoration();
                decoration.setId(resultSet.getInt("id"));
                decoration.setName(resultSet.getString("name"));
                decoration.setMaterial(resultSet.getString("material"));
                decoration.setPrice(resultSet.getDouble("price"));
                decoration.setAvailable(resultSet.getBoolean("available"));
                decoration.setRoomId(resultSet.getInt("roomId"));
                decorations.add(decoration);
            }
        } catch (SQLException e) {
            System.out.println("Error listing all the decoration objects from the db: "+e.getMessage());
        }
        return decorations;
    }

    public void updateDecoration(Decoration decoration) {
        String query = "UPDATE decorations SET name = ?, material = ?, price = ?, available = ?, roomId = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, decoration.getName());
            preparedStatement.setString(2, decoration.getMaterial());
            preparedStatement.setDouble(3, decoration.getPrice());
            preparedStatement.setBoolean(4, decoration.isAvailable());
            preparedStatement.setInt(5, decoration.getRoomId());
            preparedStatement.setInt(6, decoration.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating the select decoration object in the db: "+e.getMessage());
        }
    }

    public void updateDecorationAvailability(Decoration decoration, boolean newAvailability) {
        String query = "UPDATE decorations SET available = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, newAvailability); // Establece el nuevo valor de available
            preparedStatement.setInt(2, decoration.getId()); // Obtiene el ID de la Decoration
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating avialability of the chosen decoration in de db"+ e.getMessage());
        }
    }


    public void deleteDecoration(Decoration decoration) {
        String query = "DELETE FROM decorations WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, decoration.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error delating de select decoration object in the db: "+e.getMessage());
        }
    }



}
