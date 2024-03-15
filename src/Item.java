abstract class Item {
    protected String name;
    protected int value;

    /**
     * Constructs a new Item object with the specified name and value.
     *
     * @param name  The name of the item.
     * @param value The value of the item.
     */
    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the value of the item.
     *
     * @return The value of the item.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Checks if this item is nearby to the specified player.
     *
     * @param item   The item to check for proximity.
     * @param player The player to check proximity against.
     * @return True if the item is nearby to the player, otherwise false.
     */
    public boolean isNearBy(Item item, Player player) {
        boolean isNearby = false;
        Room currentRoom = player.getCurrentRoom();
        for (int i = 0; i < currentRoom.getListItems().length; i++) { // Checking if the item is in the Room
            if (currentRoom.getListItems()[i] != null && currentRoom.getListItems()[i].equals(this)) {
                isNearby = true;
            }
        }
        for (int j = 0; j < player.getInventory().length; j++) { // Checking if the item is in the player's bag
            if (player.getInventory()[j] != null && player.getInventory()[j].equals(this)) {
                isNearby = true;
            }
        }
        return isNearby;
    }

    /**
     * Finds and returns an equal item present in the player's inventory or the current room.
     *
     * @param item   The item to find an equal item for.
     * @param player The player whose inventory and current room to search in.
     * @return An equal item found in the player's inventory or the current room, or null if not found.
     */
    public Item findEqual(Item item, Player player) {
        Room currentRoom = player.getCurrentRoom();
        for (int i = 0; i < currentRoom.getListItems().length; i++) { // Checking if the item is in the Room
            if (currentRoom.getListItems()[i] != null && currentRoom.getListItems()[i].equals(this)) {
                return currentRoom.getListItems()[i];
            }
        }
        for (int j = 0; j < player.getInventory().length; j++) { // Checking if the item is in the player's bag
            if (player.getInventory()[j] != null && player.getInventory()[j].equals(this)) {
                return player.getInventory()[j];
            }
        }
        return null;
    }

    /**
     * Abstract method to be implemented by subclasses to define the behavior of using the item.
     *
     * @param player The player who is using the item.
     */
    public abstract void useItem(Player player);
}
