public class QuartNode<T extends Cloneable> implements Cloneable {
    private T value;
    private QuartNode<T> north;
    private QuartNode<T> south;
    private QuartNode<T> east;
    private QuartNode<T> west;

    /**
     * Constructs a new QuartNode with the specified value and initializes the directional pointers to null.
     *
     * @param value the value of the node
     */
    public QuartNode(T value) {
        this.value = value;
        this.north = null;
        this.east = null;
        this.south = null;
        this.west = null;
    }

    /**
     * Constructs a new QuartNode with the specified value and connects it to another QuartNode in the specified direction.
     *
     * @param value the value of the node
     * @param d the direction in which to connect the new node
     * @param other the QuartNode to connect to
     */
    public QuartNode(T value, Direction d, QuartNode<T> other){
        QuartNode<T> newNode=new QuartNode<T>(value);
        if(d==Direction.NORTH){
            newNode.north=other;
            other.south=newNode;
        } else if(d==Direction.SOUTH){
            newNode.south=other;
            other.north=newNode;
        } else if(d==Direction.EAST){
            newNode.east=other;
            other.west=newNode;
        } else if(d==Direction.WEST){
            newNode.west=other;
            other.east=newNode;
        }
    }

    /**
     * Gets the value stored in this QuartNode.
     *
     * @return the value stored in this QuartNode
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value stored in this QuartNode.
     *
     * @param value the new value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Gets the QuartNode in the specified direction.
     *
     * @param d the direction to retrieve the QuartNode from
     * @return the QuartNode in the specified direction, or null if the direction is invalid
     */
    public QuartNode<T> getDirection(Direction d){
        if(d==Direction.NORTH){
            return this.north;
        } else if(d==Direction.SOUTH){
            return this.south;
        } else if(d==Direction.EAST){
            return this.east;
        } else if(d==Direction.WEST){
            return this.west;
        }
        return null;
    }

    /**
     * Gets the QuartNode in the opposite direction of the specified direction.
     *
     * @param d the direction to find the opposite direction for
     * @return the QuartNode in the opposite direction of the specified direction, or null if the direction is invalid
     */
    public QuartNode<T> oppDirection(Direction d){
        if(d==Direction.NORTH){
            return this.south;
        } else if(d==Direction.SOUTH){
            return this.north;
        } else if(d==Direction.EAST){
            return this.west;
        } else if(d==Direction.WEST){
            return this.east;
        }
        return null;
    }

    /**
     * Sets the QuartNode in the opposite direction of the specified direction.
     *
     * @param d the direction to set the opposite direction for
     * @param node the QuartNode to set in the opposite direction
     */
    public void setOppDirection(Direction d, QuartNode<T> node) {
        if (d == Direction.NORTH) {
            this.south = node;
        } else if (d == Direction.SOUTH) {
            this.north = node;
        } else if (d == Direction.EAST) {
            this.west = node;
        } else if (d == Direction.WEST) {
            this.east = node;
        }
    }

    /**
     * Sets the QuartNode to the north of this node.
     *
     * @param newNode the QuartNode to set to the north
     */
    public void setNorth(QuartNode<T> newNode) {
        this.north = newNode;
    }

    /**
     * Sets the QuartNode to the south of this node.
     *
     * @param newNode the QuartNode to set to the south
     */
    public void setSouth(QuartNode<T> newNode) {
        this.south = newNode;
    }

    /**
     * Sets the QuartNode to the east of this node.
     *
     * @param newNode the QuartNode to set to the east
     */
    public void setEast(QuartNode<T> newNode) {
        this.east = newNode;
    }

    /**
     * Sets the QuartNode to the west of this node.
     *
     * @param newNode the QuartNode to set to the west
     */
    public void setWest(QuartNode<T> newNode) {
        this.west = newNode;
    }

    /**
     * Gets the neighbor QuartNode in the specified direction.
     *
     * @param d the direction to retrieve the neighbor QuartNode from
     * @return the neighbor QuartNode in the specified direction, or null if the direction is invalid
     */
    public QuartNode<T> getNeighbor(Direction d){
        if (d== Direction.NORTH){
            return this.north;
        } else if (d==Direction.SOUTH){
            return this.south;
        } else if (d==Direction.EAST){
            return this.east;
        } else if (d==Direction.WEST){
            return this.west;
        }
        return null;
    }

    /**
     * Creates and returns a shallow copy of this object.
     *
     * @return a clone of this instance
     * @throws CloneNotSupportedException if the object's class does not support the Cloneable interface
     */
    @Override
    public T clone(){
        try{
            return (T) super.clone();

        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}
