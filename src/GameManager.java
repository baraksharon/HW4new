import java.util.Iterator;
import java.lang.reflect.Method;


public class GameManager implements Cloneable  {
    private QuartlyLinkedList<Room> rooms;
    private Player player;

    /**
     * creating the rooms in an array
     */
    public GameManager() {
        this.rooms = new QuartlyLinkedList<Room>();
        this.player = null;
    }

    /**
     * Adds a player to the game.
     *
     * @param p The player to be added.
     */
    public void addPlayer(Player p) {
        if (this.player == null) {
            this.player = p;
            System.out.println(player.getName() + " was added to the game.");
        } else {
            System.out.println("Could not add " + p.getName() + " to the game.");
        }
    }

    /**
     * Adds a new room to the game and connects it to a target room via the specified direction.
     *
     * @param toInsert the room to be added
     * @param target the target room to connect to
     * @param direction the direction in which to connect the new room
     * @throws RoomDoesNotExistException if the target room does not exist in the game
     * @throws ExitIsOccupiedException if the exit in the specified direction from the target room is already occupied
     */
    public void addRoom(Room toInsert, Room target, Direction direction) {
        boolean roomExists = false;
        boolean exitOccupied = false;

        // Check if the target room exists
        Iterator<QuartNode<Room>> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            QuartNode<Room> quartNode = iterator.next();
            Room room = quartNode.getValue();
            if (room.equals(target)) {
                roomExists = true;
                if (quartNode.getDirection(direction) != null) {
                    exitOccupied = true;
                } else {
//                    QuartNode<Room> newNode = new QuartNode<>(toInsert, direction, quartNode);
//                    quartNode.setOppDirection(direction, newNode);
                    rooms.add(toInsert, target, direction);
                    System.out.println(toInsert.getRoomName() + " was added to the game and connected from " +
                            target.getRoomName() + " via the " + direction.name().toLowerCase() + " exit.");
                }
                break;
            }
        }
        // Handle cases where the target room doesn't exist or the exit is occupied
        if (!roomExists) {
            throw new RoomDoesNotExistException();
        } else if (exitOccupied) {
            throw new ExitIsOccupiedException();
        }
    }

    /**
     * Adds an item to a room in the game.
     *
     * @param r  The room to add the item to.
     * @param it The item to be added.
     */
    public void addItem(Room r, Item it) {
        boolean isThere = false;
        Iterator<QuartNode<Room>> iterator = rooms.iterator();
        while (iterator.hasNext()) {

            QuartNode<Room> quartNode = iterator.next();
            if(quartNode.getValue() != null){
                Room itRoom = quartNode.getValue();
                if(itRoom != null && itRoom.equals(r)){
                    r.addItemToRoom(it);
                    isThere = true;
                }
            }
        }
        if (!isThere) {
            System.out.println("Could not add " + it.getName() + " to the game.");
        }
    }

    /**
     * Removes a player from the game.
     *
     * @param p The player to be removed.
     */
    public void removePlayer(Player p) {
        if (this.player.equals(p)) {
            this.player.removePlayerItems();
            this.player.setCurrentRoom(null);
            this.player = null;
            System.out.println(p.getName() + " was removed from the game.");
        } else {
            System.out.println(p.getName() + " does not exist.");
        }
    }

    /**
     * Removes the specified room from the game.
     *
     * @param r the room to be removed
     */
    public void removeRoom(Room r) {
        try {
            rooms.remove(r); // Remove the specified room from the list of rooms in the game
        }
        catch (NoSuchElement e){
            throw new RoomDoesNotExistException();
        }
    }

    /**
     * Starts the player in a specified room.
     *
     * @param startRoom The room where the player starts.
     */
    public void startPlayer(Room startRoom) {
        if (this.player.getCurrentRoom() == null) {
            this.player.initializationCurrentRoom(startRoom);
            System.out.println(this.player.getName() + " starts in " + startRoom.getRoomName() + ".");
        } else {
            System.out.println(this.player.getName() + " has already started.");
        }

    }

    /**
     * Moves the player in the specified direction.
     *
     * @param dMove the direction in which the player should move
     */
    public void movePlayer(Direction dMove) {
        boolean isFound= false;
        Room current = this.player.getCurrentRoom();
        Iterator<QuartNode<Room>> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            QuartNode<Room> quartNode = iterator.next();
            Room room = quartNode.getValue();
            if (room != null && room.equals(current)) {
                isFound= true;
                if (!current.getPuzzleStatus() && quartNode.getDirection(dMove) != null) {
                    System.out.println(this.player.getName() + " moved from " + current.getRoomName() + " to " + quartNode.getDirection(dMove).getValue().getRoomName() + " via the " + dMove + " exit.");
                    this.player.setCurrentRoom(quartNode.getNeighbor(dMove).getValue());
                } else{
                    System.out.println(this.player.getName() + " could not move via the " + dMove + " exit.");
                }
            }
        }
        if (isFound){
            System.out.println(this.player.getName() + " could not move via the " + dMove + " exit.");
        }
    }

    /**
     * Picks up an item from the current room.
     *
     * @param it The item to pick up.
     */
    public void pickUpItem(Item it) {
        Room current = this.player.getCurrentRoom();
        boolean isInRoom =false;
        for (int j = 0; j < current.getListItems().length; j++) {
            if (current.getListItems()[j] != null && current.getListItems()[j].equals(it)) {
                isInRoom = true;
                boolean isTherePlace = false;
                int count = 0;
                while (!isTherePlace && count < this.player.getInventory().length) {
                    if (this.player.getInventory()[count] == null) {
                        isTherePlace = true;
                    } else {
                        count += 1;
                    }
                }
                if (isTherePlace) { // There is place to add item in the player's Bag
                    if ((this.player.getPlayerBag() instanceof Bag && (it instanceof LargeBag || it instanceof Bag)) || // checking if player's bag is instance of Bag and the item is instance of bag or large bag
                            (this.player.getPlayerBag() instanceof LargeBag && it instanceof LargeBag)) {// checking if player's bag is instance of LargeBag and if the item instance of large bag
                        System.out.println(it.getName() + " is not valid for sorting.");
                    } else {
                        this.player.addItemToBag(it, count);
                    }
                } else { // There is no place to add item in the player's Bag
                    System.out.println(this.player.getName() + "'s inventory is full.");
                }
            }
        }
        if (!isInRoom) {
            System.out.println(it.getName() + " is not in " + current.getRoomName() + ".");
        }
    }

    /**
     * Drops an item in the current room.
     *
     * @param it The item to drop.
     */
    public void dropItem(Item it) {
        boolean isInBag = false;
        int index = -1;
        for (int i = 0; i < this.player.getInventory().length; i++) {
            if (this.player.getInventory()[i] != null && this.player.getInventory()[i].equals(it)) {
                index = i;
                isInBag = true;
            }
        }
        if (isInBag) {
            Room current = this.player.getCurrentRoom();
            boolean isTherePlaceInRoom = false;
            int counter = 0;
            while (!isTherePlaceInRoom && counter < current.getListItems().length) {
                if (current.getListItems()[counter] == null) {
                    isTherePlaceInRoom = true;
                } else {
                    counter += 1;
                }
            }
            if (isTherePlaceInRoom && index != -1) {
                this.player.dropItem(this.player.getInventory()[index], counter);
            } else {
                System.out.println(current.getRoomName() + " is full.");
            }


        } else {
            System.out.println(it.getName() + " is not in " + player.getName() + "'s inventory.");
        }
    }

    /**
     * Disassembles an item.
     *
     * @param it The item to disassemble.
     */
    public void disassembleItem(Item it) {
        boolean isDestroyed = false;
        Item theEqual = null;
        for (int i = 0; i < this.player.getInventory().length; i++) {
            if (this.player.getInventory()[i] != null && this.player.getInventory()[i].equals(it)) {
                theEqual = this.player.getInventory()[i];
                this.player.destroyItemFromInventory(it, i);
                isDestroyed = true;
            }
        }
        for (int k = 0; k < this.player.getCurrentRoom().getListItems().length; k++) {
            if (this.player.getCurrentRoom().getListItems()[k] != null && this.player.getCurrentRoom().getListItems()[k].equals(it)) {
                theEqual = this.player.getCurrentRoom().getListItems()[k];
                this.player.destroyItemFromCurrentRoom(it, k);
                isDestroyed = true;
            }
        }
        if (isDestroyed) {
            System.out.println(this.player.getName() + " disassembled " + theEqual.getName() + ".");
        } else {
            System.out.println(this.player.getName() + " could not disassemble " + it.getName() + ".");
        }
    }

    /**
     * Solves the puzzle in the current room.
     */
    public void solvePuzzle() {
        Room current = this.player.getCurrentRoom();
        if (current.getPuzzleStatus()) { //if the puzzle exists
            System.out.println(this.player.getName() + " is solving the puzzle in " + current.getRoomName() + ".");
            current.setPuzzleStatus(false);
        } else {
            System.out.println("There is no active puzzle in " + current.getRoomName() + ".");
        }
    }


    /**
     * Activates a puzzle in a specified room.
     *
     * @param currentRoom The room where the puzzle is activated.
     */
    public void activatePuzzle(Room currentRoom) {
        if (!currentRoom.getKeyRoomStatus()) {
            currentRoom.setPuzzleStatus(true);
        } else {
            System.out.println(currentRoom.getRoomName() + " was unlocked with " + currentRoom.getRoomKey().getName() + ".");
        }
    }

    /**
     * Deactivates a puzzle in a specified room.
     *
     * @param currentRoom The room where the puzzle is deactivated.
     */
    public void deactivatePuzzle(Room currentRoom) {
        if (!currentRoom.getKeyRoomStatus()) {
            currentRoom.setPuzzleStatus(false);
        } else {
            System.out.println(currentRoom.getRoomName() + " was unlocked with " + currentRoom.getRoomKey().getName() + ".");
        }
    }

    /**
     * Uses the specified item, invoking its useItem method with the current player.
     *
     * @param it The item to use.
     */
    public void useItem(Item it) {
        it.useItem(this.player);
    }

//    /**
//     * Creates a deep copy of the GameManager object.
//     *
//     * @return A cloned GameManager object or null if cloning is not supported.
//     */
//    @Override
//    public GameManager clone() {
//        try {
//            GameManager copy = (GameManager) super.clone();
//            copy.rooms = this.rooms.clone(); // Deep copy of rooms
//            copy.player = this.player.clone(); // Deep copy of player
//            return copy;
//        } catch (CloneNotSupportedException e) {
//            return null;
//        }
//    }

    /**
     * Creates a deep copy of the GameManager object.
     *
     * @return A cloned GameManager object or null if cloning is not supported.
     */
    @Override
    public GameManager clone() {
        try {
            // Find the clone method of the GameManager class
            Method cloneMethod = GameManager.class.getMethod("clone");

            // Invoke the clone method on the current object
            GameManager copy = (GameManager) cloneMethod.invoke(this);

            // Perform deep copy for the fields that require it
            copy.rooms = this.rooms.clone();
            copy.player = this.player.clone();

            return copy;
        } catch (Exception e) {
            return null;
        }
    }
}






