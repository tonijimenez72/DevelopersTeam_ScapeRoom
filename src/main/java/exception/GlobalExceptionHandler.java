package exception;

public interface GlobalExceptionHandler {
    static void handleException(Exception e) {
        System.err.println("ERROR: " + e.getMessage());
    }
}