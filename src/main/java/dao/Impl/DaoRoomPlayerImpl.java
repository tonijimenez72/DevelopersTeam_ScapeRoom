package dao.Impl;

import dao.DaoRoomPlayer;
import database.DatabaseConnection;
import model.Player;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRoomPlayerImpl implements DaoRoomPlayer {

    public void addPlayerRoomRelation(Player player, Room room) {
        String query = "INSERT INTO player_has_room (player_id, room_id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, player.getId());
            statement.setInt(2, room.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding the player to a room in the db: "+e.getMessage());
        }
    }

    public List<int[]> getAllPlayerRoomRelations() {
        List<int[]> relations = new ArrayList<>();
        String query = "SELECT player_id, room_id FROM player_has_room";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                int roomId = resultSet.getInt("room_id");
                relations.add(new int[]{playerId, roomId});
            }
        } catch (SQLException e) {
            System.out.println("Error getting all the registers of player_has_room from the db: "+e.getMessage());

        }

        return relations;
    }

    public void updatePlayerRoomRelation(int oldPlayerId, int oldRoomId, int newPlayerId, int newRoomId) {
        String query = "UPDATE player_has_room SET player_id = ?, room_id = ? WHERE player_id = ? AND room_id = ?";
        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, newPlayerId);
            statement.setInt(2, newRoomId);
            statement.setInt(3, oldPlayerId);
            statement.setInt(4, oldRoomId);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating the room id and player id chosen in the db: "+e.getMessage());
        }
    }

    public void deletePlayerRoomRelation(int playerId, int roomId) {
        String query = "DELETE FROM player_has_room WHERE player_id = ? AND room_id = ?";
        try (   Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, playerId);
            statement.setInt(2, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting the player id and player id chosen in the db "+e.getMessage());
        }
    }
}
