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
    private static final String INSERT_QUERY = "INSERT INTO decoration (name, material, price, room_id) VALUES (?, ?, ?, ?)";
    public static final String GET_ALL_QUERY = "SELECT * From decoration";
    public static final String GET_BY_ID_QUERY = "SELECT * FROM decoration WHERE id = ? ";
    public static final String DELETE_QUERY = "DELETE FROM decoration WHERE id = ?";

    @Override
    public void save(Decoration decoration) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
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

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
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
        Decoration decoration = null;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
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
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, decoration.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing decoration form : " + e.getMessage());
        }

    }
}
