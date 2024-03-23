/**
 * Exception indicating that there is no such element.
 */
public class NoSuchElementException extends RuntimeException {

    /**
     * Constructs a new NoSuchElementException with no detail message.
     */
    public NoSuchElementException() {
    }

    /**
     * Constructs a new NoSuchElementException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public NoSuchElementException(String message) {
        super(message);
    }

    /**
     * Constructs a new NoSuchElementException with the specified detail message and cause.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public NoSuchElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
