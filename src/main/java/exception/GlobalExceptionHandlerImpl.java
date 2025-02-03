package exception;

public class GlobalExceptionHandlerImpl {

    public void handleException(Exception e) {
        if (e instanceof EntityNotFoundException) {
            System.err.println("Entity not found: " + e.getMessage());
        } else if (e instanceof InvalidEntityDataException) {
            System.err.println("Invalid entity data: " + e.getMessage());
        } else {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}