public class LargeBag extends Bag{
    public static final int defultCap= 5;

    /**
     * Constructs a new LargeBag object with the specified name, value, and capacity.
     *
     * @param name  The name of the bag.
     * @param value The value of the bag.
     * @param cap   The capacity of the bag's inventory.
     */
    public LargeBag(String name, int value, int cap) {
        super(name, value, cap);
        if (cap < defultCap) {
            this.inventory = new Item[defultCap];
        }
    }

    /**
     * Gets the inventory array of the bag.
     *
     * @return The array of items in the bag's inventory.
     */
    public Item[] getInventory() {
        return this.inventory;
    }

    /**
     * Gets the name of the bag.
     *
     * @return The name of the bag.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the bag.
     *
     * @return The value of the bag.
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Checks if the specified item is nearby the player.
     *
     * @param item   The item to check for proximity.
     * @param player The player to check proximity against.
     * @return True if the item is nearby the player, false otherwise.
     */
    @Override
    public boolean isNearBy(Item item, Player player) {
        return super.isNearBy(item, player);
    }


    /**
     * Counts the number of items in the bag's inventory array.
     *
     * @param items The array of items to count.
     * @return The number of items in the inventory.
     */
    @Override
    public int countItemsInBag(Item[] items) {
        return super.countItemsInBag(items);
    }

    /**
     * Counts the number of free places in the bag's inventory array.
     *
     * @param items The array of items to check for free places.
     * @return The number of free places in the inventory.
     */
    @Override
    public int countFreePlaces(Item[] items) {
        return super.countFreePlaces(items);
    }

    /**
     * Finds the index for placing the current bag inside another bag's inventory.
     *
     * @param it The array of items to search for an empty place.
     * @return The index of the first empty place in the inventory array, or -1 if no empty place is found.
     */
    public int getIndexForCurrentBag(Item[] it) {
        int index = 0;
        int place = -1;
        while (index < it.length && place == -1) {
            if (it[index] == null) {
                place = index;
            } else {
                index++;
            }
        }
        return place;
    }

    /**
     * Uses the large bag item by transferring items from the player's inventory to the large bag.
     * If the large bag is nearby and there is enough space in it, the items from the player's inventory are transferred.
     * If the large bag is not nearby or there is not enough space, appropriate messages are printed.
     *
     * @param player The player using the large bag item.
     */
    @Override
    public void useItem(Player player) {
        if (isNearBy(this, player)) { // The bag is nearby
            int spaceInNewFuturePlayerBag = this.countFreePlaces(this.getInventory());
            int itemsInPlayerBag = player.getPlayerBag().countItemsInBag(player.getInventory());
            boolean isTherePlace = (spaceInNewFuturePlayerBag >= itemsInPlayerBag);
            if (isTherePlace) {
                int i = 0;
                int j = 0;
                while (i < player.getInventory().length) {
                    if (player.getInventory()[i] != null && this.getInventory()[j] == null) {
                        this.getInventory()[j] = player.getInventory()[i];
                        player.getInventory()[i] = null;
                        i++;
                        j++;
                    } else {
                        if (player.getInventory()[i] == null) {
                            i++;
                        }
                        if (this.getInventory()[j] != null) {
                            j++;
                        }
                    }
                }
                if (player.getPlayerBag() instanceof Bag) { // Checks if the current is bag to determine if it can be put inside the new bag
                    int index = getIndexForCurrentBag(this.getInventory());
                    if (index != -1) {
                        this.getInventory()[index] = player.getPlayerBag();
                    } else {
                        player.destroyBag(player.getPlayerBag());
                    }
                } else {
                    player.destroyBag(player.getPlayerBag());
                }
                player.setPlayerBag(this);
                for (int u = 0; u < player.getCurrentRoom().getListItems().length; u++) { // Checking where the item is in the Room and remove it
                    if (player.getCurrentRoom().getListItems()[u] != null && player.getCurrentRoom().getListItems()[u].equals(this)) {
                        player.getCurrentRoom().getListItems()[u] = null;
                    }
                }
                System.out.println(player.getName() + " is now carrying " + this.getName() + ".");
            } else {
                System.out.println(this.getName() + " is too small.");
            }
        } else {
            System.out.println(this.getName() + " is not near " + player.getName() + ".");
        }
    }

    /**
     * Calculates the sum of values of items in the large bag.
     *
     * @return The sum of values of items in the large bag.
     */
    public int sumValuesOfLargeBagItems() {
        int sum = 0;
        for (int i = 0; i < this.inventory.length; i++) {
            if (inventory[i] != null) {
                sum += inventory[i].getValue();
            }
        }
        return sum;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two large bags are considered equal if they have the same value, the sum of values of their items is the same,
     * and they have the same inventory length.
     *
     * @param otherLargeBag1 The reference object with which to compare.
     * @return {@code true} if this object is equal to the other object; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object otherLargeBag1) {
        if (!(otherLargeBag1 instanceof LargeBag)) {
            return false;
        }
        LargeBag otherLargeBag2 = (LargeBag) otherLargeBag1;
        return (this.getValue() == otherLargeBag2.getValue() &&
                this.sumValuesOfLargeBagItems() == otherLargeBag2.sumValuesOfLargeBagItems() &&
                this.getInventory().length == otherLargeBag2.getInventory().length);
    }

    /**
     * Returns a hash code value for the large bag.
     *
     * @return A hash code value for the large bag.
     */
    @Override
    public int hashCode() {
        int result = 31; // Different initial prime number
        int multiplier = 53; // Different multiplier
        result = multiplier * result + value;
        result = multiplier * result + getInventory().length;
        result = multiplier * result + sumValuesOfLargeBagItems();
        return result;
    }

    /**
     * Overrides the clone method to create a deep copy of the LargeBag object.
     * This method clones the array of inventory and all the items within it.
     *
     * @return a cloned LargeBag object
     */
    @Override
    public LargeBag clone() {
        LargeBag clonedBag = (LargeBag) super.clone();
        // Clone the array of inventory
        clonedBag.inventory = inventory.clone();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                clonedBag.inventory[i] = inventory[i].clone();
            }
        }
        return clonedBag;
    }

}
