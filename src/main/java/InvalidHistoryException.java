public class InvalidHistoryException extends Exception {
    public InvalidHistoryException() {

    }

    public InvalidHistoryException(String message) {
        super(message);
    }
    public InvalidHistoryException (Throwable cause) {
        super (cause);
    }
    public InvalidHistoryException (String message, Throwable cause) {
        super (message, cause);
    }
}
