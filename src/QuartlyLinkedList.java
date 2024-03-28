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
     * @throws DirectionIsOccupied if the specified direction is already occupied by another element
     * @throws NoSuchElement if the target element is not found in this QuartlyLinkedList
     */
    public void add(T toInsert, T target, Direction d){
        if(this.getRoot() == null){
            this.root= new QuartNode<>(toInsert);
        } else {
            Iterator<QuartNode<T>> iterator = iterator();
            boolean isFound= false;
            while (iterator.hasNext()) {
                QuartNode<T> element = iterator.next();
                if(element.getValue().equals(target)){
                    if(element.oppDirection(d)== null){
                        isFound= true;
                        QuartNode<T> newNode= new QuartNode<>(toInsert, d, element);
                    } else {
                        throw new DirectionIsOccupied();
                    }
                }
            }
            if( ! isFound){
                throw new NoSuchElement();
            }
        }
    }

    /**
     * Removes the specified element from this QuartlyLinkedList if it is present.
     * This method removes all references to the element in all directions.
     *
     * @param toRemove the element to be removed from this QuartlyLinkedList, if present
     * @throws NoSuchElement if the element is not present in this QuartlyLinkedList
     */
    public void remove(T toRemove){
        Iterator<QuartNode<T>> iterator = iterator();
        Boolean isFound= false;
        while (iterator.hasNext()) {
            QuartNode<T> element = iterator.next();
            if(element.getValue().equals(toRemove)){
                isFound= true;
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
        if(! isFound){
            throw new NoSuchElement();
        }
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
            copy.root = cloneNodesUsingIterator(); // Cloning the root node
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Clones all nodes in the linked list and their neighbors using an iterator.
     *
     * @return The cloned root node of the linked list.
     */
    public QuartNode<T> cloneNodesUsingIterator() {
        if (root == null) {
            return null;
        }

        // Create a new root node with the cloned value
        QuartNode<T> clonedRoot = new QuartNode<>(root.clone());

        // Initialize iterator for the original linked list
        Iterator<QuartNode<T>> iterator = iterator();

        // Initialize variables to keep track of current nodes
        QuartNode<T> currentOriginalNode = root;
        QuartNode<T> currentClonedNode = clonedRoot;

        if(iterator.hasNext()) {
            QuartNode<T> originalNextNode = iterator.next();
            // Iterate through the linked list using the iterator
            while (iterator.hasNext()) {
                // Move to the next node in the original linked list
                originalNextNode = iterator.next();

                // Clone the value of the next node and create a new QuartNode
                QuartNode<T> clonedNextNode = new QuartNode<>(originalNextNode.clone());

                // Connect the cloned nodes in the appropriate directions
                if (currentOriginalNode.getDirection(Direction.NORTH) != null) {
                    currentClonedNode.setNorth(clonedNextNode);
                }
                if (currentOriginalNode.getDirection(Direction.EAST) != null) {
                    currentClonedNode.setEast(clonedNextNode);
                }
                if (currentOriginalNode.getDirection(Direction.SOUTH) != null) {
                    currentClonedNode.setSouth(clonedNextNode);
                }
                if (currentOriginalNode.getDirection(Direction.WEST) != null) {
                    currentClonedNode.setWest(clonedNextNode);
                }

                // Update the current nodes for the next iteration
                currentOriginalNode = originalNextNode;
                currentClonedNode = clonedNextNode;
            }

        }
        return clonedRoot;
    }

}
