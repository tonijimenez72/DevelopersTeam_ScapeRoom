package controller;

import model.Ticket;
import service.TicketService;
import exception.GlobalExceptionHandler;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
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
