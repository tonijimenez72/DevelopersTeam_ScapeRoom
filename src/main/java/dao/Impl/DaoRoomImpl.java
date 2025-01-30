package dao.Impl;

import dao.DaoRoom;
import database.DatabaseConnection;
import enums.DifficultyLevel;
import enums.Theme;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRoomImpl implements DaoRoom {



    @Override
    public void safe(Room room)  {

        String query = "INSERT INTO room (name, theme, difficulty_level, price, available) VALUES (?, ?, ?, ?, ?)";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, room.getName());
            statement.setString(2, room.getTheme().name());
            statement.setString(3, room.getDifficultyLevel().name());
            statement.setDouble(4,room.getPrice());
            statement.setBoolean(5, room.isAvailable());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting the room in the db: "+e.getMessage());
        }
    }


    @Override
    public List<Room> getAll() {

        List<Room>rooms = new ArrayList<>();

        String query ="SELECT* From room WHERE is_deleted = false";

        try (Connection connection= DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet= preparedStatement.executeQuery()) {


            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setTheme(Theme.valueOf(resultSet.getString("theme")));
                room.setDifficultyLevel(DifficultyLevel.valueOf(resultSet.getString("difficulty_level")));
                room.setPrice(resultSet.getDouble("price"));
                room.setAvailable(resultSet.getBoolean("available"));

                rooms.add(room);
            }


        } catch (SQLException e) {
            System.out.println("Error getting all the rooms from de db"+e.getMessage());        }


        return rooms;
    }

    @Override
    public Room getById(int id) {

        String query = "SELECT * FROM room WHERE id = ? ";
        Room room=null;

        try (   Connection connection= DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    room = new Room();
                    room.setId(resultSet.getInt("id"));
                    room.setName(resultSet.getString("name"));
                    room.setTheme(Theme.valueOf(resultSet.getString("theme")));
                    room.setDifficultyLevel(DifficultyLevel.valueOf(resultSet.getString("difficulty_level")));
                    room.setPrice(resultSet.getDouble("price"));
                    room.setAvailable(resultSet.getBoolean("available"));
                }
            }

        } catch (SQLException e) {

            System.out.println("Error getting the specific room by id: "+e.getMessage());        }

        return room;

    }

    @Override
    public void remove(Room room) {

        String query = "UPDATE room SET is_deleted = ? WHERE id = ?";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, true); // is_deleted = true
            statement.setInt(2, room.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing the specific room: "+e.getMessage());
        }

    }





    /*public void addRoom(Room room) {
        String query = "INSERT INTO room (id, name, theme, difficulty_level, price, available) VALUES (?, ?, ?, ?, ?,?)";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, room.getId());
            statement.setString(2, room.getName()); // Assuming Difficulty is an Enum
            statement.setString(3, room.getTheme().name());
            statement.setString(4, room.getDifficultyLevel().name()); // Assuming Theme is an Enum
            statement.setDouble(5, room.getPrice());
            statement.setBoolean(6, room.isAvailable());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting de room in the data base: "+e.getMessage());

        }
    }

    public void addRoomToEscapeRoom(Room room, int escapeRoomId) {
        String query = "UPDATE rooms SET escape_room_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, escapeRoomId);
            statement.setInt(2, room.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding the the room to the chosen escape room in the db: "+e.getMessage());
        }
    }

    public List<Room> getAllRoomOfTheScapeRoom(int escapeRoomId) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE escape_room_id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, escapeRoomId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Room room = new Room();
                    room.setId(resultSet.getInt("id"));
                    room.setName(resultSet.getString("name"));
                    room.setDifficultyLevel(DifficultyLevel.valueOf(resultSet.getString("difficulty")));
                    room.setTheme(Theme.valueOf(resultSet.getString("theme")));
                    room.setPrice(resultSet.getDouble("price"));
                    room.setAvailable(resultSet.getBoolean("enabled"));
                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error bringing all the room of this sacape room from the db"+e.getMessage());        }

        return rooms;
    }




    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {  // Cambiamos Statement por PreparedStatement

            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setDifficultyLevel(DifficultyLevel.valueOf(resultSet.getString("difficulty")));
                room.setTheme(Theme.valueOf(resultSet.getString("theme")));
                room.setPrice(resultSet.getDouble("price"));
                room.setAvailable(resultSet.getBoolean("enabled"));

                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all the rooms from de db"+e.getMessage());
        }

        return rooms;
    }

    public void updateRoom(Room room) {
        String query = "UPDATE rooms SET name = ?, difficulty = ?, theme = ?, price = ?, available = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, room.getName());
            statement.setString(2, room.getDifficultyLevel().toString());
            statement.setString(3, room.getTheme().toString());
            statement.setDouble(4, room.getPrice());
            statement.setBoolean(5, room.isAvailable());
            statement.setInt(6, room.getId());

            statement.executeUpdate();
        } catch (SQLException e) {

            System.out.println("Error updating the chosen room in the db"+e.getMessage());
        }
    }

    public void updateRoomAvailability(int idRoom, boolean isAvailable) {
        String query = "UPDATE rooms SET available = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, isAvailable);
            statement.setInt(2, idRoom);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating the avialitibility of the chosen room in the db"+e.getMessage());
        }
    }

    public void deleteRoom(int roomId) {
        String query = "DELETE FROM rooms WHERE id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error trying to deleting the chosen room in the db: "+e.getMessage());
        }
    }*/





}
