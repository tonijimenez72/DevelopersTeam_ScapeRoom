package dao.impl;

import dao.DaoRoom;
import database.DatabaseConnection;
import enums.*;
import model.Clue;
import model.Decoration;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRoomImpl implements DaoRoom {

    @Override
    public void save(Room room) {
        String query = "INSERT INTO room (name, price, theme, difficulty_level, escape_room_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, room.getName());
            statement.setDouble(2, room.getPrice());
            statement.setString(3, room.getTheme().name());
            statement.setString(4, room.getDifficultyLevel().name());
            statement.setInt(5, room.getEscapeRoomId());  // 🔹 Ahora guardamos el escapeRoomId

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting the room in the db: " + e.getMessage());
        }
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setTheme(Theme.valueOf(resultSet.getString("theme")));
                room.setDifficultyLevel(DifficultyLevel.valueOf(resultSet.getString("difficulty_level")));
                room.setPrice(resultSet.getDouble("price"));

                rooms.add(room);
            }

        } catch (SQLException e) {
            System.out.println("Error getting all the rooms from the db: " + e.getMessage());
        }

        for (Room room : rooms) {
            room.setClues(getCluesInTheRoom(room.getId()));
            room.setDecorations(getDecorationsInTheRoom(room.getId()));
        }

        return rooms;
    }


    @Override
    public Room getById(int id) {
        String query = "SELECT * FROM room WHERE id = ?";
        Room room = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
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
                    room.setEscapeRoomId(resultSet.getInt("escape_room_id"));

                    room.setClues(getCluesInTheRoom(id));
                    room.setDecorations(getDecorationsInTheRoom(id));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error getting the specific room by id: " + e.getMessage());
        }

        return room;
    }

    @Override
    public void remove(Room room) {
        String query = "DELETE FROM room WHERE id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, room.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing the specific room: " + e.getMessage());
        }
    }

    private List<Clue> getCluesInTheRoom(int roomId) {
        List<Clue> clues = new ArrayList<>();
        String query = "SELECT id, name, theme, price FROM clue WHERE room_id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Clue clue = new Clue(
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        Theme.valueOf(resultSet.getString("theme")),
                        roomId
                );
                clue.setId(resultSet.getInt("id"));
                clues.add(clue);
            }

        } catch (SQLException e) {
            System.err.println("Error getting clues for room: " + e.getMessage());
        }

        return clues;
    }

    private List<Decoration> getDecorationsInTheRoom(int roomId) {
        List<Decoration> decorations = new ArrayList<>();
        String query = "SELECT id, name, material, price FROM decoration WHERE room_id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Decoration decoration = new Decoration(
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("material"),  // 🔹 CORREGIDO: Usar "material" en vez de "name"
                        roomId
                );
                decoration.setId(resultSet.getInt("id"));
                decorations.add(decoration);
            }

        } catch (SQLException e) {
            System.err.println("Error getting decorations for room: " + e.getMessage());
        }

        return decorations;
    }
}
