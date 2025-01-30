package dao.Impl;

import dao.DaoDecoration;
import dao.DaoRoom;
import database.DatabaseConnection;
import enums.DifficultyLevel;
import enums.Theme;
import model.Decoration;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoDecorationImpl implements DaoDecoration {


    @Override
    public void safe(Decoration decoration) {

        String query = "INSERT INTO decoration (name, material, price) VALUES (?, ?, ?)";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, decoration.getName());
            statement.setString(2, decoration.getMaterial());
            statement.setDouble(3, decoration.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting the decoration in the db: "+e.getMessage());
        }

    }

    @Override
    public List<Decoration> getAll() {

        List<Decoration>decorations = new ArrayList<>();

        String query ="SELECT* From decoration WHERE is_deleted = false";

        try (Connection connection= DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet= preparedStatement.executeQuery()) {


            while (resultSet.next()) {
                Decoration decoration = new Decoration();

                decoration.setId(resultSet.getInt("id"));
                decoration.setName(resultSet.getString("name"));
                decoration.setMaterial(resultSet.getString("material"));
                decoration.setPrice(resultSet.getInt("price"));
                decoration.setAvailable(resultSet.getBoolean("available"));
                decoration.setRoomId(resultSet.getInt("room_id"));

                decorations.add(decoration);
            }


        } catch (SQLException e) {
            System.out.println("Error getting all the decorations from de db"+e.getMessage());        }


        return decorations;

    }

    @Override
    public Decoration getById(int id) {

        String query = "SELECT * FROM decoration WHERE id = ? ";
        Decoration decoration=null;

        try (   Connection connection= DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    decoration = new Decoration();

                    decoration.setId(resultSet.getInt("id"));
                    decoration.setName(resultSet.getString("name"));
                    decoration.setMaterial(resultSet.getString("material"));
                    decoration.setPrice(resultSet.getInt("price"));
                    decoration.setAvailable(resultSet.getBoolean("available"));
                    decoration.setRoomId(resultSet.getInt("room_id"));
                }
            }

        } catch (SQLException e) {

            System.out.println("Error getting the specific decoration by id: "+e.getMessage());        }

        return decoration;

    }

    @Override
    public void remove(Decoration decoration) {

        String query = "UPDATE decoration SET is_deleted = ? WHERE id = ?";

        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, true); // is_deleted = true
            statement.setInt(2, decoration.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error removing the specific decoration: "+e.getMessage());
        }

    }































    /*public void insertDecoration(Decoration decoration) {
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

    public void addDecoToRoom(int idDecoration, int idRoom) {
        String query = "UPDATE decorations SET roomId = ?, available = ? WHERE id = ? AND available = 1";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idRoom);
            preparedStatement.setBoolean(2,false);
            preparedStatement.setInt(2, idDecoration);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error assigning room to the decoration in the db: "+e.getMessage());
        }
    }

    public void removeDecorationFromRoom(int decorationId) {
        String query = "UPDATE decorations SET roomId = NULL, available = true WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, decorationId); // Establece el ID de la Decoration a actualizar
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error borrando la decoración de la room en la db"+e.getMessage());        }
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

    public void updateDecorationAvailability(int idDecoration, boolean newAvailability) {
        String query = "UPDATE decorations SET available = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, newAvailability); // Establece el nuevo valor de available
            preparedStatement.setInt(2, idDecoration); // Obtiene el ID de la Decoration
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating avialability of the chosen decoration in de db"+ e.getMessage());
        }
    }


    public void deleteDecoration(int idDecoration) {
        String query = "DELETE FROM decorations WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idDecoration);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error delating de select decoration object in the db: "+e.getMessage());
        }
    }*/



}
