package dao.impl;

import dao.DaoTicket;
import database.DatabaseConnection;
import exception.DatabaseOperationException;
import model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoTicketImpl implements DaoTicket {
    private static final String INSERT_QUERY = "INSERT INTO tickets (room_id, player_id, total_price) VALUES (?, ?, ?)";
    private static final String TOTAL_REVENUE_QUERY = "SELECT SUM(total_price) FROM tickets";

    //INSERT INTO tickets (room_id, player_id, total_price) VALUES (1, 1, 20.5)

    @Override
    public void save(Ticket ticket) throws DatabaseOperationException {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            if (ticket.getRoomId() != null) {
                stmt.setInt(1, ticket.getRoomId());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            if (ticket.getPlayerId() != null) {
                stmt.setInt(2, ticket.getPlayerId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            stmt.setDouble(3, ticket.getTotalPrice());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error saving ticket to database");
        }
    }

    @Override
    public double getTotalIncome() throws DatabaseOperationException {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(TOTAL_REVENUE_QUERY);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error retrieving total revenue from tickets");
        }
        return 0.0;
    }

}