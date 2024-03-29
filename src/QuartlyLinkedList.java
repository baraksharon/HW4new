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
     * Removes the specified element from the linked list.
     * If the element to be removed is the root node, all connections to it will be removed.
     * If the element is not the root, only its connections to neighboring nodes will be removed.
     *
     * @param toRemove the element to be removed
     * @throws NoSuchElement if the specified element does not exist in the linked list
     */
    public void remove(T toRemove) {
        if (toRemove.equals(this.root.getValue())) {
            removeRoot();
        } else {
            removeNonRoot(toRemove);
        }
    }

    /**
     * Removes the root node from the linked list.
     * All connections to the root node's neighboring nodes will be removed.
     */
    private void removeRoot() {
        if (this.root == null) {
            return;
        }
        removeNeighborConnections(this.root);  // Remove connections from neighbors
        this.root = null;
    }

    /**
     * Removes a non-root node from the linked list.
     * Only the connections to neighboring nodes of the specified node will be removed.
     *
     * @param toRemove the element to be removed
     * @throws NoSuchElement if the specified element does not exist in the linked list
     */
    private void removeNonRoot(T toRemove) {
        Iterator<QuartNode<T>> iterator = iterator();
        boolean isFound = false;
        while (iterator.hasNext()) {
            QuartNode<T> element = iterator.next();
            if (element.getValue().equals(toRemove)) {
                isFound = true;
                removeNeighborConnections(element); // Remove connections from neighbors
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElement();
        }
    }

    /**
     * Removes the connections to neighboring nodes of the specified node.
     *
     * @param node the node whose connections are to be removed
     */
    private void removeNeighborConnections(QuartNode<T> node) {
        if (node == null) {
            return;
        }
        // Remove connections to the node from its neighbors:
        QuartNode<T> north = node.getNeighbor(Direction.NORTH);
        QuartNode<T> south = node.getNeighbor(Direction.SOUTH);
        QuartNode<T> east = node.getNeighbor(Direction.EAST);
        QuartNode<T> west = node.getNeighbor(Direction.WEST);

        if (north != null) {
            north.setSouth(null);
            node.setNorth(null);
        }
        if (south != null) {
            south.setNorth(null);
            node.setSouth(null);
        }
        if (east != null) {
            east.setWest(null);
            node.setEast(null);
        }
        if (west != null) {
            west.setEast(null);
            node.setWest(null);
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
        QuartNode<T> clonedRoot = new QuartNode<>(root.clone()); // Create a new root node with the cloned value
        Iterator<QuartNode<T>> iterator = iterator();// Initialize iterator for the original linked list
        QuartNode<T> currentOriginalNode = root;// Initialize variables to keep track of current nodes
        QuartNode<T> currentClonedNode = clonedRoot;
        if(iterator.hasNext()) {
            QuartNode<T> originalNextNode = iterator.next();
            while (iterator.hasNext()) { // Iterate through the linked list using the iterator
                originalNextNode = iterator.next();// Move to the next node in the original linked list
                QuartNode<T> clonedNextNode = new QuartNode<>(originalNextNode.clone());// Clone the value of the next node and create a new QuartNode
                // Connect the cloned nodes in the appropriate directions:
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
                currentOriginalNode = originalNextNode;// Update the current nodes for the next iteration
                currentClonedNode = clonedNextNode;
            }
        }
        return clonedRoot;
    }

}
