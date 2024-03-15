import java.util.Iterator;

public class QuartlyLinkedList <T extends Cloneable> implements Iterable<QuartNode<T>>, Cloneable {

    private QuartNode<T> root;



    public QuartlyLinkedList(){
        this.root=null;
    }

    public QuartNode getRoot(){
        return this.root;
    }

//    public void add(QuartNode<T> toInsert, QuartNode<T> target, Direction d) {
//        if (this == null) {
//            this.root = new QuartNode<>(toInsert.getValue());
//        } else {
//            Iterator<T> iterator = iterator();
//            while (iterator.hasNext()) {
//                T element = iterator.next();
//                if(element.equals(target.getValue())){
//                    if(target.getNeighbor(d)==null){
//                        QuartNode<T> newNode= new QuartNode<>(toInsert.getValue(), d, target);
//                    } else {
//                        throw new DirectionIsOccupiedException();
//                    }
//                }
//            }
//            throw new NoSuchElementException();
//        }
//    }

    public void add(T toInsert,T target,Direction d){

        if(this==null){
            this.root=new QuartNode<>(toInsert);
        }
        else {
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

        @Override
    public Iterator<QuartNode<T>> iterator () {
        return new QuartlyLinkedListIterator<>(this.getRoot());
    }
}