class MyCloneable implements Cloneable {
    private int num;

    public MyCloneable(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "MyCloneable: " + num;
    }

    @Override
    public int hashCode() {
        return num;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyCloneable)){
            return false;
        }
        MyCloneable other = (MyCloneable) obj;
        return num == other.num;
    }

    @Override
    public MyCloneable clone() {
        try {
            return (MyCloneable) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}


public class Main {
    public static void main(String[] args){
        System.out.println("Test 1 starts");
        test1();
        System.out.println("Test 1 done");
        System.out.println("--------------------------------------------");
    }

    /**
     //     * Run first test of hw2
     //     */
    public static void test1(){
        MyCloneable element1 = new MyCloneable(1);
        MyCloneable element2 = new MyCloneable(2);
        MyCloneable element3 = new MyCloneable(3);
        MyCloneable element4 = new MyCloneable(4);
        MyCloneable element5 = new MyCloneable(5);
        MyCloneable element6 = new MyCloneable(6);
        MyCloneable element7 = new MyCloneable(7);
        MyCloneable elementTmp = new MyCloneable(2);
        QuartlyLinkedList<MyCloneable> lst = new QuartlyLinkedList<>();
        lst.add(element1, null, null);
        lst.add(element2, element1, Direction.NORTH);
        try{
            lst.add(element4, element3, Direction.NORTH);
        }
        catch (NoSuchElement e){
            System.out.println("Caught exception: " + e);
        }
        lst.add(element3, elementTmp, Direction.NORTH);
        lst.add(element4, element2, Direction.EAST);
        lst.add(element5, element4, Direction.NORTH);
        lst.add(element6, element1, Direction.EAST);
        lst.add(element7, element1, Direction.SOUTH);
        try{
            lst.add(new MyCloneable(8), element1, Direction.EAST);
        } catch (DirectionIsOccupied e){
            System.out.println("Caught exception: " + e);
        }

        for(QuartNode<MyCloneable> node: lst){
            System.out.println(node.getValue());
        }

        QuartlyLinkedList<MyCloneable> cloned = lst.clone();
        System.out.println("Printing cloned.");
        for(QuartNode<MyCloneable> node: cloned){
            System.out.println(node.getValue());
        }

        element6.setNum(13);
        element7.setNum(16);
        System.out.println("Printing original list.");
        for(QuartNode<MyCloneable> node: lst){
            System.out.println(node.getValue());
        }
        System.out.println("Printing cloned.");
        for(QuartNode<MyCloneable> node: cloned){
            System.out.println(node.getValue());
        }
    }
}
