import java.sql.SQLOutput;

public class Bag extends Item {
    protected int cap;
    protected Item[] inventory;

    /**
     * Constructs a new Bag object with the specified name, value, and capacity.
     *
     * @param name  The name of the bag.
     * @param value The value of the bag.
     * @param cap   The capacity of the bag's inventory.
     */
    public Bag(String name, int value, int cap) {
        super(name, value);
        this.inventory = new Item[cap];
    }

    /**
     * Retrieves the inventory of the bag.
     *
     * @return The array of items representing the bag's inventory.
     */
    public Item[] getInventory() {
        return this.inventory;
    }

    /**
     * Retrieves the name of the bag.
     *
     * @return The name of the bag.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the value of the bag.
     *
     * @return The value of the bag.
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Checks if the specified item is nearby to the player.
     *
     * @param item   The item to check for proximity.
     * @param player The player to check proximity against.
     * @return True if the item is nearby to the player, otherwise false.
     */
    @Override
    public boolean isNearBy(Item item, Player player) {
        return super.isNearBy(item, player);
    }


    /**
     * Counts the number of items present in the bag.
     *
     * @param items The array of items representing the bag's inventory.
     * @return The count of items in the bag.
     */
    public int countItemsInBag(Item[] items) {
        int counter = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                counter += 1;
            }
        }
        return counter;
    }

    /**
     * Counts the number of free places available in the bag's inventory.
     *
     * @param items The array of items representing the bag's inventory.
     * @return The count of free places in the bag's inventory.
     */
    public int countFreePlaces(Item[] items) {
        return (items.length - countItemsInBag(items));
    }

    /**
     * Counts the number of bags present in the inventory.
     *
     * @param inventory The array of items representing the inventory.
     * @return The count of bags present in the inventory.
     */
    public int countingBags(Item[] inventory) {
        int sum = 0;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i] instanceof Bag) {
                sum += 1;
            }
        }
        return sum;
    }

    /**
     * Checks if the specified bag is already present inside the inventory.
     *
     * @param inventory The array of items representing the inventory.
     * @param newbag    The bag to be checked if it's present.
     * @return True if the bag is already present in the inventory, otherwise false.
     */
    public boolean isNewBagInside(Item[] inventory, Bag newbag) {
        boolean isThere = false;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].equals(newbag)) {
                isThere = true;
            }
        }
        return isThere;
    }

    /**
     * Uses the LargeBag item by transferring items from the player's current bag to this bag if it's nearby.
     * If the bag is already inside the player's inventory, it moves items from the current bag to this bag,
     * ensuring the new bag is not smaller than the current one. If successful, the current bag is destroyed,
     * and the player's bag is set to this bag.
     *
     * @param player The player using the item.
     */
    @Override
    public void useItem(Player player) {
        if (isNearBy(this, player)) { //The bag is nearby
            int spaceInNewFuturePlayerBag = this.countFreePlaces(this.getInventory());
            int itemsInPlayerBag = player.getPlayerBag().countItemsInBag(player.getInventory());
            int sumOfBags = player.getPlayerBag().countingBags(player.getInventory());
            boolean isBagInside = isNewBagInside(player.getInventory(), this);
            if (isBagInside) { //checking if the new bag is inside the current bag
                boolean isTherePlace1 = (spaceInNewFuturePlayerBag >= itemsInPlayerBag - 1); // -1 because we need to move the items in the current bag without the (new)bag
                if (sumOfBags != 1 || !isTherePlace1) {//if in the current bag there are more bags than the new one-the bag is too small
                    System.out.println(this.getName() + " is too small.");
                } else {
                    int index = 0;
                    int k = 0;
                    while (index < player.getInventory().length) {
                        if (player.getInventory()[index] != null && !player.getInventory()[index].equals(this) && this.getInventory()[k] == null) {
                            this.getInventory()[k] = player.getInventory()[index];
                            player.getInventory()[index] = null;
                            index++;
                            k++;
                        } else {
                            if (player.getInventory()[index] == null || player.getInventory()[index].equals(this)) {
                                index++;
                            }
                            if (this.getInventory()[k] != null) {
                                k++;
                            }
                        }
                    }
                    player.destroyBag(player.getPlayerBag());
                    player.setPlayerBag(this);
                    System.out.println(player.getName() + " is now carrying " + this.getName() + ".");
                }

            } else {
                boolean isTherePlace2 = (spaceInNewFuturePlayerBag >= itemsInPlayerBag && sumOfBags == 0);
                if (isTherePlace2) {
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
                    player.destroyBag(player.getPlayerBag());
                    player.setPlayerBag(this);
                    for (int u = 0; u < player.getCurrentRoom().getListItems().length; u++) { // Checking where the item is in the Room and remove him
                        if (player.getCurrentRoom().getListItems()[u] != null && player.getCurrentRoom().getListItems()[u].equals(this)) {
                            player.getCurrentRoom().getListItems()[u] = null;
                        }
                    }
                    System.out.println(player.getName() + " is now carrying " + this.getName() + ".");
                } else {
                    System.out.println(this.getName() + " is too small.");
                }
            }
        } else {
            System.out.println(this.getName() + " is not near " + player.getName() + ".");
        }
    }

    /**
     * Calculates the sum of values of all items in the bag.
     *
     * @return The sum of values of all items in the bag.
     */
    public int sumValuesOfBagItems() {
        int sum = 0;
        for (int i = 0; i < this.inventory.length; i++) {
            if (inventory[i] != null) {
                sum += inventory[i].getValue();
            }
        }
        return sum;
    }


    /**
     * Indicates whether some other object is "equal to" this one. Equality is determined
     * based on the value, sum of item values, and the length of the inventory array.
     *
     * @param otherBag1 The object to compare for equality.
     * @return True if the specified object is equal to this one, false otherwise.
     */
    @Override
    public boolean equals(Object otherBag1) {
        if ((!(otherBag1 instanceof Bag)) || (otherBag1 instanceof LargeBag)) {
            return false;
        }
        Bag otherBag2 = (Bag) otherBag1;
        return (this.getValue() == otherBag2.getValue() &&
                this.sumValuesOfBagItems() == otherBag2.sumValuesOfBagItems() &&
                this.getInventory().length == otherBag2.getInventory().length);
    }

    /**
     * Returns a hash code value for the LargeBag object. The hash code is based on the
     * value, sum of item values, and the length of the inventory array.
     *
     * @return A hash code value for this LargeBag object.
     */
    @Override
    public int hashCode() {
        int result = 29; // Different initial prime number
        int multiplier = 47; // Different multiplier
        result = multiplier * result + value;
        result = multiplier * result + getInventory().length;
        result = multiplier * result + sumValuesOfBagItems();
        return result;
    }

}
