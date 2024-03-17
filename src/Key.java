public class Key extends Item{
    protected boolean keyStatus;

    /**
     * Constructs a Key object with a name and value.
     *
     * @param name  the name of the key
     * @param value the value of the key
     */
    public Key(String name, int value) {
        super(name, value);
        keyStatus = false;
    }

    /**
     * Returns the name of the key.
     *
     * @return the name of the key
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the value of the key.
     *
     * @return the value of the key
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Checks if the key is near a specific item and player.
     *
     * @param item   the item to check proximity to
     * @param player the player to check proximity to
     * @return true if the key is near the item and player, false otherwise
     */
    @Override
    public boolean isNearBy(Item item, Player player) {
        return super.isNearBy(item, player);
    }

    /**
     * Returns the status of the key (true if used, false otherwise).
     *
     * @return the status of the key
     */
    public Boolean getKeyStatus() {
        return this.keyStatus;
    }

    /**
     * Uses the key, unlocking a room if the key is near the player.
     *
     * @param player the player using the key
     */
    @Override
    public void useItem(Player player) {
        Room currentRoom = player.getCurrentRoom();
        if (currentRoom.getRoomKey() != null) {
            System.out.println(player.getCurrentRoom().getRoomName() + " was already unlocked.");
        } else {
            boolean isKeyNearby = isNearBy(this, player);
            if (isKeyNearby) {
                for (int i = 0; i < currentRoom.getListItems().length; i++) { // Checking if the item is in the Room
                    if (currentRoom.getListItems()[i] != null && currentRoom.getListItems()[i].equals(this)) {
                        player.getCurrentRoom().setRoomKey(this);
                        player.getCurrentRoom().setKeyRoomStatus(true);
                        currentRoom.getListItems()[i] = null;
                    }
                }
                for (int j = 0; j < player.getInventory().length; j++) {  // Checking if the item is in the player's bag
                    if (player.getInventory()[j] != null && player.getInventory()[j].equals(this)) {
                        player.getCurrentRoom().setRoomKey(this);
                        player.getCurrentRoom().setKeyRoomStatus(true);
                        player.getInventory()[j] = null;
                    }
                }
                System.out.println(player.getName() + " used " + this.name + " in " + player.getCurrentRoom().getRoomName() + ".");
                if (player.getCurrentRoom().getPuzzleStatus()) {
                    player.getCurrentRoom().setPuzzleStatus(false);
                }
            } else {
                System.out.println(this.name + " is not near " + player.getName() + ".");
            }
        }
    }

    /**
     * Checks if this key is equal to another key based on their values.
     *
     * @param otherKey1 the other key to compare
     * @return true if the keys have the same value, false otherwise
     */
    @Override
    public boolean equals(Object otherKey1) {
        if (!(otherKey1 instanceof Key)) {
            return false;
        }
        Key otherKey2 = (Key) otherKey1;
        return (this.getValue() == otherKey2.getValue());
    }

    /**
     * Generates a hash code for the key based on its value.
     *
     * @return the hash code of the key
     */
    @Override
    public int hashCode() {
        int result = 41;
        result = 31 * result + value;
        return result;
    }

    @Override
    public Key clone() {
        Key clonedKey = (Key) super.clone();
        // Clone the keyStatus field
        clonedKey.keyStatus = this.keyStatus;
        return clonedKey;
    }

}