package dao.impl;

import dao.DaoEscapeRoom;
import database.DatabaseConnection;
import model.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEscapeRoomImpl implements DaoEscapeRoom {
    public void save(EscapeRoom escapeRoom) {

        String query = "INSERT INTO escape_room  (name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting the escape room  in the db: " + e.getMessage());
        }

    }

    @Override
    public EscapeRoom getById(int id) {
        String query = "SELECT * FROM escape_room WHERE id = ? ";
        EscapeRoom escapeRoom = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

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
    public List<EscapeRoom> getAll() {
        List<EscapeRoom> escapeRooms = new ArrayList<>();
        String query = "SELECT * FROM escape_room";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
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
    public int count() {
        String query = "SELECT COUNT(*) FROM escape_room";
        int count = 0;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
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