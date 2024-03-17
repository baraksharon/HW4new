public class Player {
    private  String name;
    private Bag playerBag;
    private Room currentRoom;
    public final String defultString= "Starting bag";
    public final int defultValue= 1;

    /**
     * Constructs a new Player object with the specified name and bag capacity.
     *
     * @param name    The name of the player.
     * @param bagCap  The capacity of the player's inventory.
     */
    public  Player(String name, int bagCap){
        this.name= name;
        this.playerBag = new Bag(defultString, defultValue, bagCap);
        this.currentRoom= null;
    }


    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the inventory of the player.
     *
     * @return The inventory of the player.
     */
    public Item[] getInventory() {
        return  this.playerBag.getInventory();
    }

    /**
     * Gets the current room of the player.
     *
     * @return The current room of the player.
     */
    public Room getCurrentRoom(){return this.currentRoom;}

    /**
     * Sets the current room of the player.
     *
     * @param newCurrentRoom The new current room of the player.
     */
    public void setCurrentRoom(Room newCurrentRoom){this.currentRoom=newCurrentRoom;}

    /**
     * Initializes the current room of the player.
     *
     * @param newCurrentRoom The new current room of the player.
     */
    public void initializationCurrentRoom(Room newCurrentRoom){
        this.currentRoom= newCurrentRoom;
    }

    /**
     * Removes all items from the player's inventory.
     */
    public void removePlayerItems(){
        for (int i = 0; i< playerBag.getInventory().length; i++){
            this. playerBag.getInventory()[i]= null;
        }
    }

    /**
     * Destroys an item from the player's inventory.
     *
     * @param it    The item to destroy.
     * @param index The index of the item in the inventory.
     */
    public void destroyItemFromInventory(Item it, int index) {
        playerBag.getInventory()[index] = null;
    }

    /**
     * Destroys an item from the current room.
     *
     * @param it    The item to destroy.
     * @param index The index of the item in the current room's item list.
     */
    public void destroyItemFromCurrentRoom(Item it, int index) {
        this.currentRoom.getListItems()[index]= null;
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param newItem The item to add to the inventory.
     * @param place   The index where the item should be placed in the inventory.
     */
    public void addItemToBag(Item newItem, int place){
        for(int i=0; i<this.getCurrentRoom().getListItems().length; i++){
            if(this.getCurrentRoom().getListItems()[i] != null && this.getCurrentRoom().getListItems()[i].equals(newItem)){
                this.getCurrentRoom().getListItems()[i]= null;
            }
        }
        this.playerBag.getInventory()[place]=newItem;
        System.out.println(this.name + " picked up " + newItem.getName() + " from " + currentRoom.getRoomName() + ".");
    }

    /**
     * drops an item from the player's inventory into the current room.
     *
     * @param itemToDrop The item to drop.
     * @param place      The index of the item in the inventory.
     */
    public void dropItem(Item itemToDrop, int place){
        for(int i = 0; i< this.playerBag.getInventory().length; i++){
            if(this.playerBag.getInventory()[i] != null && this.playerBag.getInventory()[i].equals(itemToDrop)){
                this.playerBag.getInventory()[i]= null;
            }
        }
        this.currentRoom.getListItems()[place]= itemToDrop;
        System.out.println(this.name + " dropped " + itemToDrop.getName() + " in " + currentRoom.getRoomName() + ".");
    }

    /**
     * Calculates the sum of values of items in the player's bag.
     *
     * @return The sum of values of items in the player's bag.
     */
    public int sumValuesOfplayerBag() {
        int sum = 0;
        for (int i = 0; i < this.playerBag.getInventory().length; i++) {
            if (this.playerBag.getInventory()[i] != null) {
                sum += this.playerBag.getInventory()[i].getValue();
            }
        }
        return sum;
    }

    /**
     * Gets the player's bag.
     *
     * @return The player's bag.
     */
    public Bag getPlayerBag() {
        return playerBag;
    }

    /**
     * Sets the player's bag to a new bag.
     *
     * @param newBag The new bag to set for the player.
     */
    public void setPlayerBag(Bag newBag) {
        this.playerBag = newBag;
    }

    /**
     * Destroys the specified bag.
     *
     * @param bag The bag to destroy.
     */
    public void destroyBag(Bag bag) {
        System.out.println(this.getName() + " disassembled " + bag.getName() + ".");
        this.setPlayerBag(null);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param otherPlayer1 The reference object with which to compare.
     * @return True if this object is the same as the other argument; false otherwise.
     */
    @Override
    public boolean equals(Object otherPlayer1) {
        if (!(otherPlayer1 instanceof Player)) {
            return false;
        }
        Player otherPlayer2 = (Player) otherPlayer1;
        return (this.sumValuesOfplayerBag() == otherPlayer2.sumValuesOfplayerBag());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = 23; // Different initial prime number
        int multiplier = 37; // Different multiplier
        result = multiplier * result + sumValuesOfplayerBag();
        return result;
    }

    @Override
    public Player clone() {
        try {
            // Shallow clone for player
            Player clonedPlayer = (Player) super.clone();
            // Deep clone for the bag
            clonedPlayer.playerBag = this.playerBag.clone();
            // If the current room is not null, clone it as well
            if (this.currentRoom != null) {
                clonedPlayer.currentRoom = this.currentRoom.clone();
            }
            return clonedPlayer;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}