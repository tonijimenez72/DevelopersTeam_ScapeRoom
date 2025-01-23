package exception.database;

public class DatabaseConnectionException extends Exception{
    public DatabaseConnectionException(String message) {
        super(message);
    }
}
