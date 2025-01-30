package dao.Impl;

import dao.DaoEscapeRoom;
import database.DatabaseConnection;
import enums.DifficultyLevel;
import enums.Theme;
import model.EscapeRoom;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEscapeRoomImpl implements DaoEscapeRoom {
    @Override
    public void safe(EscapeRoom escapeRoom) {

        String query = "INSERT INTO escape_room  (name) VALUES (?)";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, escapeRoom.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting the escape room  in the db: "+e.getMessage());
        }

    }

    @Override
    public List<EscapeRoom> getAll() {

        List<EscapeRoom> escapeRooms = new ArrayList<>();

        String query ="SELECT* From escape_room WHERE is_deleted = false ";

        try (Connection connection= DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet= preparedStatement.executeQuery()) {


            while (resultSet.next()) {
                EscapeRoom escapeRoom = new EscapeRoom();

                escapeRoom.setId(resultSet.getInt("id"));
                escapeRoom.setName(resultSet.getString("name"));


                escapeRooms.add(escapeRoom);
            }


        } catch (SQLException e) {
            System.out.println("Error getting all the escape rooms from de db"+e.getMessage());        }


        return escapeRooms;


    }

    @Override
    public EscapeRoom getById(int id) {


        String query = "SELECT * FROM escape_room WHERE id = ? ";
        EscapeRoom escapeRoom=null;

        try (   Connection connection= DatabaseConnection.getInstance().getConnection();
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

            System.out.println("Error getting the specific escape room by id: "+e.getMessage());        }

        return escapeRoom;


    }

    @Override
    public void remove(EscapeRoom escapeRoom) {


        String query = "UPDATE escape_room SET is_deleted = ? WHERE id = ?";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, true); // is_deleted = true
            statement.setInt(2, escapeRoom.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing the specific escape room: "+e.getMessage());
        }

    }














    /*public void addEscapeRoom(EscapeRoom escapeRoom) {
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
    }*/



}
