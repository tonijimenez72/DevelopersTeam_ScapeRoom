package dao.Impl;

import dao.DaoClue;
import database.DatabaseConnection;
import enums.DifficultyLevel;
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


    @Override
    public void safe(Clue clue) {

        String query = "INSERT INTO clue (name, theme, price) VALUES (?, ?, ?)";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, clue.getName());
            statement.setString(2, clue.getTheme().name());
            statement.setDouble(3, clue.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting the clue in the db: "+e.getMessage());
        }

    }

    @Override
    public List<Clue> getAll() {

        List<Clue>clues = new ArrayList<>();

        String query ="SELECT* From clue WHERE is_deleted = false";

        try (Connection connection= DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet= preparedStatement.executeQuery()) {


            while (resultSet.next()) {
                Clue clue = new Clue();
                clue.setId(resultSet.getInt("id"));
                clue.setName(resultSet.getString("name"));
                clue.setTheme(Theme.valueOf(resultSet.getString("theme")));
                clue.setPrice(resultSet.getDouble("price"));
                clue.setAvailable(resultSet.getBoolean("available"));
                clue.setRoomId(resultSet.getInt("room_id"));

                clues.add(clue);
            }


        } catch (SQLException e) {
            System.out.println("Error getting all the clues from de db"+e.getMessage());        }


        return clues;

    }

    @Override
    public Clue getById(int id) {

        String query = "SELECT * FROM clue WHERE id = ? ";
        Clue clue=null;

        try (   Connection connection= DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clue = new Clue();
                    clue.setId(resultSet.getInt("id"));
                    clue.setName(resultSet.getString("name"));
                    clue.setTheme(Theme.valueOf(resultSet.getString("theme")));
                    clue.setPrice(resultSet.getDouble("price"));
                    clue.setAvailable(resultSet.getBoolean("available"));
                    clue.setRoomId(resultSet.getInt("room_id"));


                }
            }

        } catch (SQLException e) {

            System.out.println("Error getting the specific clue by id: "+e.getMessage());        }

        return clue;

    }

    @Override
    public void remove(Clue clue) {

        String query = "UPDATE clue SET is_deleted = ? WHERE id = ?";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, true); // is_deleted = true
            statement.setInt(2, clue.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing the specific clue: "+e.getMessage());
        }

    }
















    /*public void addClue(Clue clue) {
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

    public void addClueToRoom(int idClue, int idRoom) {

        String sql = "UPDATE Clue SET roomId = ?, available = ? WHERE id = ? AND available = 1";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idRoom);
            statement.setBoolean(3,false);
            statement.setInt(2, idClue);


            statement.executeUpdate();
        } catch (SQLException e) {

            System.err.println("Error assigning Room to the Clue in the db: " + e.getMessage());
        }
    }

    public void removeClueFromRoom(int clueId) {
        String sql = "UPDATE Clue SET roomId = NULL, available = TRUE WHERE id = ?";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clueId); // ID del clue a actualizar
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La pista fue removida de la habitación y está disponible nuevamente.");
            } else {
                System.out.println("No se encontró la pista con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al remover la pista de la habitación en la db " + e.getMessage());
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

    public void updateClueAvailability(int idClue, boolean available) {
        String sql = "UPDATE Clue SET available = ? WHERE id = ?";
        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, available); // Nuevo valor para el campo 'available'
            statement.setInt(2, idClue); // ID del clue a actualizar
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating the available of the chosen clue in the db" + e.getMessage());        }
    }


    public void deleteClue(int idClue) {
        String sql = "DELETE FROM Clue WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idClue);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting the Clue in the db: " + e.getMessage());
        }
    }*/


}
