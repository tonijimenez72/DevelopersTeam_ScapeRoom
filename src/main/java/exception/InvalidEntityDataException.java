package exception;

public class InvalidEntityDataException extends Exception{
    public InvalidEntityDataException(String message) {
        super(message);
    }
}