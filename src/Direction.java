/**
 * Represents cardinal directions: NORTH, SOUTH, EAST, and WEST.
 */
public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    @Override
    public String toString() {
        // Returns the lowercase representation of the enum constant
        return this.name().toLowerCase();
    }

}
