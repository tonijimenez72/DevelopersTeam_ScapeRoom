package dao.impl;

import dao.DaoClue;
import database.DatabaseConnection;
import enums.Theme;
import model.Clue;
import model.Decoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoClueImpl implements DaoClue {
    private static final String INSERT_QUERY = "INSERT INTO clue (name, theme, price, room_id) VALUES (?, ?, ?, ?)";
    public static final String GET_ALL_QUERY = "SELECT * From clue";
    public static final String GET_BY_ID_QUERY = "SELECT * FROM clue WHERE id = ? ";
    public static final String DELETE_QUERY = "DELETE FROM clue WHERE id = ?";

    @Override
    public void save(Clue clue) {

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, clue.getName());
            statement.setString(2, clue.getTheme().name());
            statement.setDouble(3, clue.getPrice());
            statement.setInt(4, clue.getRoomId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting the clue in the db: " + e.getMessage());
        }

    }

    @Override
    public List<Clue> getAll() {

        List<Clue> clues = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {


            while (resultSet.next()) {
                Clue clue = new Clue();

                clue.setId(resultSet.getInt("id"));
                clue.setName(resultSet.getString("name"));
                clue.setTheme(Theme.valueOf(resultSet.getString("theme")));
                clue.setPrice(resultSet.getDouble("price"));
                clue.setRoomId(resultSet.getInt("room_id"));

                clues.add(clue);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all the clues from de db" + e.getMessage());
        }

        return clues;
    }

    @Override
    public Clue getById(int id) {
        Clue clue = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clue = new Clue();

                    clue.setId(resultSet.getInt("id"));
                    clue.setName(resultSet.getString("name"));
                    clue.setTheme(Theme.valueOf(resultSet.getString("theme")));
                    clue.setPrice(resultSet.getDouble("price"));
                    clue.setRoomId(resultSet.getInt("room_id"));
                }
            }

        } catch (SQLException e) {

            System.out.println("Error getting the specific clue by id: " + e.getMessage());
        }

        return clue;
    }

    @Override
    public void remove(Clue clue) {

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, clue.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing the specific clue: " + e.getMessage());
        }
    }
}