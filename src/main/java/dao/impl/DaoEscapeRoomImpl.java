package dao.impl;

import dao.DaoEscapeRoom;
import database.DatabaseConnection;
import model.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEscapeRoomImpl implements DaoEscapeRoom {
    private static final String INSERT_QUERY = "INSERT INTO escape_room  (name) VALUES (?)";
    public static final String GET_ALL_QUERY = "SELECT * From escape_room";
    public static final String GET_BY_ID_QUERY = "SELECT * FROM escape_room WHERE id = ? ";
    public static final String COUNT_QUERY = "SELECT COUNT(*) FROM escape_room";
    public static final String DELETE_QUERY = "DELETE FROM decoration WHERE id = ?";

    public void save(EscapeRoom escapeRoom) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, escapeRoom.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting the escape room  in the db: " + e.getMessage());
        }
    }

    @Override
    public List<EscapeRoom> getAll() {
        List<EscapeRoom> escapeRooms = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                EscapeRoom escapeRoom = new EscapeRoom();
                escapeRoom.setId(resultSet.getInt("id"));
                escapeRoom.setName(resultSet.getString("name"));
                escapeRooms.add(escapeRoom);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Escape Rooms from database: " + e.getMessage());
        }

        return escapeRooms;
    }

    @Override
    public EscapeRoom getById(int id) {
        EscapeRoom escapeRoom = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    escapeRoom = new EscapeRoom();
                    escapeRoom.setId(resultSet.getInt("id"));
                    escapeRoom.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting escape room by id: " + e.getMessage());
        }

        return escapeRoom;
    }

    @Override
    public int count() {
        int count = 0;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting escape rooms: " + e.getMessage());
        }

        return count;
    }
}