/**
 * Exception indicating that an exit is occupied.
 */
public class ExitIsOccupiedException extends RuntimeException {

    /**
     * Constructs a new ExitIsOccupiedException with no detail message.
     */
    public ExitIsOccupiedException() {
    }

    /**
     * Constructs a new ExitIsOccupiedException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ExitIsOccupiedException(String message) {
        super(message);
    }

    /**
     * Constructs a new ExitIsOccupiedException with the specified detail message and cause.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public ExitIsOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
