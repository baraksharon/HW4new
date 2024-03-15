public class Relic extends Item {

    /**
     * Constructs a new Relic object with the specified name and value.
     *
     * @param name  The name of the relic.
     * @param value The value of the relic.
     */
    public Relic(String name, int value) {
        super(name, value);
    }

    /**
     * Gets the name of the relic.
     *
     * @return The name of the relic.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the relic.
     *
     * @return The value of the relic.
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Checks if the relic is nearby to the player.
     *
     * @param item   The relic item to check.
     * @param player The player to check for proximity.
     * @return True if the relic is nearby the player, false otherwise.
     */
    @Override
    public boolean isNearBy(Item item, Player player) {
        return super.isNearBy(item, player);
    }

    /**
     * Uses the relic item.
     *
     * @param player The player using the relic.
     */
    @Override
    public void useItem(Player player) {
        boolean isRelicNearby = isNearBy(this, player);
        if (isRelicNearby) {
            Relic equalRelic = (Relic) findEqual(this, player);
            System.out.println(player.getName() + " is inspecting " + equalRelic.getName() + ".");
        } else {
            System.out.println(this.getName() + " is not near " + player.getName() + ".");
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param otherRelic1 The reference object with which to compare.
     * @return True if this object is the same as the other argument; false otherwise.
     */
    @Override
    public boolean equals(Object otherRelic1) {
        if (!(otherRelic1 instanceof Relic)) {
            return false;
        }
        Relic otherRelic2 = (Relic) otherRelic1;
        return (this.getValue() == otherRelic2.getValue());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = 19; // Different initial prime number
        int multiplier = 43; // Different multiplier
        result = multiplier * result + value;
        return result;
    }
}
