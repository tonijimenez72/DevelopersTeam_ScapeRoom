package dao;

import exception.DatabaseOperationException;
import model.Ticket;
import java.util.List;

public interface DaoTicket {
    void save(Ticket ticket) throws DatabaseOperationException;
    double getTotalIncome() throws DatabaseOperationException;
}
