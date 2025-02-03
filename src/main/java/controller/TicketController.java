package controller;

import exception.DatabaseOperationException;
import model.Ticket;
import service.TicketService;
import exception.GlobalExceptionHandler;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void saveTicket(Ticket ticket) {
        try {
            ticketService.save(ticket);
            System.out.println("Ticket saved successfully.");
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void showTotalIncome() {
        try {
            double totalIncome = ticketService.getTotalIncome();
            System.out.printf("Escape room total income: %.2f%n", totalIncome);
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }
}
