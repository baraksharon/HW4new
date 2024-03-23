import java.util.Iterator;

public class QuartlyLinkedList <T extends Cloneable> implements Iterable<QuartNode<T>>, Cloneable {

    private QuartNode<T> root;

    /**
     * Constructs an empty QuartlyLinkedList with no root node.
     */
    public QuartlyLinkedList() {
        this.root = null;
    }

    /**
     * Returns the root node of this QuartlyLinkedList.
     *
     * @return the root node of this QuartlyLinkedList
     */
    public QuartNode<T> getRoot() {
        return this.root;
    }

    /**
     * Adds the specified element to this QuartlyLinkedList adjacent to the target element in the specified direction.
     *
     * @param toInsert the element to be added
     * @param target the target element to which the new element will be adjacent
     * @param d the direction in which to insert the new element
     * @throws DirectionIsOccupiedException if the specified direction is already occupied by another element
     * @throws NoSuchElementException if the target element is not found in this QuartlyLinkedList
     */
    public void add(T toInsert,T target,Direction d){
        if(this==null){
            this.root=new QuartNode<>(toInsert);
        } else {
            Iterator<QuartNode<T>> iterator = iterator();
            while (iterator.hasNext()) {
                QuartNode<T> element = iterator.next();
                if(element.getValue().equals(target)){
                    if(element.oppDirection(d)== null){
                        QuartNode<T> newNode= new QuartNode<>(toInsert, d, element);
                    } else {
                        throw new DirectionIsOccupiedException();
                    }
                }
            }
            throw new NoSuchElementException();
        }
    }

    /**
     * Removes the specified element from this QuartlyLinkedList if it is present.
     * This method removes all references to the element in all directions.
     *
     * @param toRemove the element to be removed from this QuartlyLinkedList, if present
     * @throws NoSuchElementException if the element is not present in this QuartlyLinkedList
     */
    public void remove(T toRemove){
        Iterator<QuartNode<T>> iterator = iterator();
        while (iterator.hasNext()) {
            QuartNode<T> element = iterator.next();
            if(element.getValue().equals(toRemove)){
                if(element.getNeighbor(Direction.NORTH)!=null){
                    element.getNeighbor(Direction.NORTH).setSouth(null);
                    element.setNorth(null);
                }
                if(element.getNeighbor(Direction.SOUTH)!=null){
                    element.getNeighbor(Direction.SOUTH).setNorth(null);
                    element.setSouth(null);
                }
                if(element.getNeighbor(Direction.EAST)!=null){
                    element.getNeighbor(Direction.EAST).setWest(null);
                    element.setEast(null);
                }
                if(element.getNeighbor(Direction.WEST)!=null){
                    element.getNeighbor(Direction.WEST).setEast(null);
                    element.setWest(null);
                }
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Returns an iterator over the elements in this QuartlyLinkedList.
     *
     * @return an iterator over the elements in this QuartlyLinkedList
     */
    @Override
    public Iterator<QuartNode<T>> iterator () {
        return new QuartlyLinkedListIterator<>(this.getRoot());
    }

    /**
     * Overrides the clone method to create a deep copy of the QuartlyLinkedList object.
     *
     * @return A cloned QuartlyLinkedList object or null if cloning is not supported.
     */
    @Override
    public QuartlyLinkedList<T> clone() {
        try {
            QuartlyLinkedList<T> copy = (QuartlyLinkedList<T>) super.clone();
            copy.root = cloneNode(root); // Cloning the root node
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Recursively clones a QuartNode and its neighbors.
     *
     * @param node The node to clone.
     * @return The cloned QuartNode and its neighbors.
     */
    private QuartNode<T> cloneNode(QuartNode<T> node) {
        if (node == null) {
            return null;
        }
        QuartNode<T> clonedNode = new QuartNode<>(node.clone()); // Cloning the value of the node
        clonedNode.setNorth(cloneNode(node.getDirection(Direction.NORTH))); // Cloning north neighbor recursively
        clonedNode.setEast(cloneNode(node.getDirection(Direction.EAST)));   // Cloning east neighbor recursively
        clonedNode.setSouth(cloneNode(node.getDirection(Direction.SOUTH))); // Cloning south neighbor recursively
        clonedNode.setWest(cloneNode(node.getDirection(Direction.WEST)));   // Cloning west neighbor recursively
        return clonedNode;
    }

}
