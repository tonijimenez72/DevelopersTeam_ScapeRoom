package dao.impl;

import dao.DaoDecoration;
import database.DatabaseConnection;
import model.Decoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoDecorationImpl implements DaoDecoration {

    @Override
    public void save(Decoration decoration) {
        String query = "INSERT INTO decoration (name, material, price, room_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, decoration.getName());
            statement.setString(2, decoration.getMaterial());
            statement.setDouble(3, decoration.getPrice());
            statement.setInt(4, decoration.getRoomId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting the decoration in database: " + e.getMessage());
        }
    }

    @Override
    public List<Decoration> getAll() {
        List<Decoration> decorations = new ArrayList<>();
        String query = "SELECT * FROM decoration";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Decoration decoration = new Decoration();
                decoration.setId(resultSet.getInt("id"));
                decoration.setName(resultSet.getString("name"));
                decoration.setMaterial(resultSet.getString("material"));
                decoration.setPrice(resultSet.getDouble("price"));
                decoration.setRoomId(resultSet.getInt("room_id"));
                decorations.add(decoration);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all the decorations from database: " + e.getMessage());
        }
        return decorations;
    }

    @Override
    public Decoration getById(int id) {
        String query = "SELECT * FROM decoration WHERE id = ?";
        Decoration decoration = null;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    decoration = new Decoration();
                    decoration.setId(resultSet.getInt("id"));
                    decoration.setName(resultSet.getString("name"));
                    decoration.setMaterial(resultSet.getString("material"));
                    decoration.setPrice(resultSet.getDouble("price"));
                    decoration.setRoomId(resultSet.getInt("room_id"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting decoration by id: " + e.getMessage());
        }
        return decoration;
    }

    @Override
    public void remove(Decoration decoration) {

        String query = "UPDATE decoration SET is_deleted = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, true);
            statement.setInt(2, decoration.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing decoration form : " + e.getMessage());
        }

    }
}
