package exception.ticket;

public class InvalidTicketDataException extends Exception{
    public InvalidTicketDataException(String message) {
        super(message);
    }
}
