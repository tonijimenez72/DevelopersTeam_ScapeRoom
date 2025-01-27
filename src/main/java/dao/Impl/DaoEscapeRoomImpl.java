package dao.Impl;

import dao.DaoEscapeRoom;
import database.DatabaseConnection;
import model.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEscapeRoomImpl implements DaoEscapeRoom {

    public void addEscapeRoom(EscapeRoom escapeRoom) {
        String query = "INSERT INTO escape_room (id, name) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, escapeRoom.getId());
            statement.setString(2, escapeRoom.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding the scape room in the db: " + e.getMessage());
        }
    }



    public List<EscapeRoom> getAllEscapeRooms() {
        List<EscapeRoom> escapeRooms = new ArrayList<>();
        String query = "SELECT * FROM escape_rooms";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                EscapeRoom escapeRoom = new EscapeRoom();
                escapeRoom.setId(resultSet.getInt("id"));
                escapeRoom.setName(resultSet.getString("name"));
                escapeRooms.add(escapeRoom);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all the escape rooms from the db:" + e.getMessage());
        }
        return escapeRooms;
    }


    public void  updateEscapeRoom(EscapeRoom escapeRoom) {
        String query = "UPDATE escape_rooms SET name = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, escapeRoom.getName());
            statement.setInt(2, escapeRoom.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating the chosen escape room in the db:" + e.getMessage());

        }
    }

    public void deleteEscapeRoom(int id) {
        String query = "DELETE FROM escape_rooms WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error delating the scape room in the db: " + e.getMessage());
        }
    }



}
