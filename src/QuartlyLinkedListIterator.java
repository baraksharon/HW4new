import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;


public class QuartlyLinkedListIterator<E extends Cloneable> implements Iterator<QuartNode<E>> {
    private Set<QuartNode<E>> visited;
    private Stack<QuartNode<E>> stack;
    private QuartNode<E> nextNode;

    public QuartlyLinkedListIterator(QuartNode<E> nextNode){
        this.nextNode = nextNode;
        visited = new HashSet<>();
        stack = new Stack<>();
    }

    @Override
    public boolean hasNext(){
        return nextNode != null;
    }


    @Override
    public QuartNode<E> next(){
        QuartNode<E> prev = nextNode;

        // Push nextNode to stack and add it to visited list
        stack.push(prev);
        visited.add(prev);

        // Define the order of checking neighbors
        Direction[] directions = Direction.values();

        while (!stack.isEmpty()) {
            QuartNode<E> current = stack.peek();
            for (Direction direction : directions) {
                QuartNode<E> neighbor = current.getNeighbor(direction);
                if (neighbor != null && !visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    nextNode = neighbor;
                    return prev;
                }
            }
            stack.pop();
        }

        // No more nodes left to traverse
        nextNode = null;
        return prev;
    }
}
