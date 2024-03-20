public class QuartNode<T extends Cloneable> implements Cloneable {
    private T value;
    private QuartNode<T> north;
    private QuartNode<T> south;
    private QuartNode<T> east;
    private QuartNode<T> west;

    public QuartNode(T value) {
        this.value = value;
        this.north = null;
        this.east = null;
        this.south = null;
        this.west = null;
    }

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

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


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

    public void setNorth(QuartNode<T> newNode){
        this.north= newNode;
    }
    public void setSouth(QuartNode<T> newNode){ this.south= newNode;}
    public void setEast(QuartNode<T> newNode){
        this.east= newNode;
    }
    public void setWest(QuartNode<T> newNode){
        this.west= newNode;
    }



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

    @Override
    public T clone(){
        try{
            return (T) super.clone();

        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}
