package service.impl;

import dao.DaoTicket;
import dao.impl.DaoTicketImpl;
import exception.DatabaseOperationException;
import model.Ticket;
import service.TicketService;

public class TicketServiceImpl implements TicketService {
    private final DaoTicket daoTicket;

    public TicketServiceImpl() {
        this.daoTicket = new DaoTicketImpl();
    }

    @Override
    public void save(Ticket ticket) throws DatabaseOperationException {
        try {
            daoTicket.save(ticket);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error saving ticket");
        }
    }

    @Override
    public double getTotalIncome() throws DatabaseOperationException {
        try {
            return daoTicket.getTotalIncome();
        } catch (Exception e) {
            throw new DatabaseOperationException("Error retrieving total income");
        }
    }
}