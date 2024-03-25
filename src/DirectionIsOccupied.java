/**
 * Exception indicating that a direction is occupied.
 */
public class DirectionIsOccupied extends RuntimeException {

    /**
     * Constructs a new DirectionIsOccupiedException with no detail message.
     */
    public DirectionIsOccupied() {
    }

    /**
     * Constructs a new DirectionIsOccupiedException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DirectionIsOccupied(String message) {
        super(message);
    }

    /**
     * Constructs a new DirectionIsOccupiedException with the specified detail message and cause.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public DirectionIsOccupied(String message, Throwable cause) {
        super(message, cause);
    }
}
