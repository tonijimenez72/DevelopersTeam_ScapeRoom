package service;

import exception.DatabaseOperationException;
import model.Ticket;

public interface TicketService {
    void save(Ticket ticket) throws DatabaseOperationException;
    double getTotalIncome() throws DatabaseOperationException;
}