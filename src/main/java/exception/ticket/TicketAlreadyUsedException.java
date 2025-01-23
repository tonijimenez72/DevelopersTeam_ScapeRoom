package exception.ticket;

public class TicketAlreadyUsedException extends Exception{
    public TicketAlreadyUsedException(String message) {
        super(message);
    }
}
