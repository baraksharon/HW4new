/**
 * Exception indicating that a room does not exist.
 */
public class RoomDoesNotExistException extends RuntimeException {

    /**
     * Constructs a new RoomDoesNotExistException with no detail message.
     */
    public RoomDoesNotExistException() {
    }

    /**
     * Constructs a new RoomDoesNotExistException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public RoomDoesNotExistException(String message) {
        super(message);
    }

    /**
     * Constructs a new RoomDoesNotExistException with the specified detail message and cause.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public RoomDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
