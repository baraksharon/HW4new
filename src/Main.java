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
        try {
            System.out.println("Test 1 starts");
            test1();
            System.out.println("Test 1 done");
            System.out.println("--------------------------------------------");
        }catch (Exception e){
            System.out.println("exception " + e);
        }

        try {
            System.out.println("Test 2 starts");
            test2();
            System.out.println("Test 2 done");
            System.out.println("--------------------------------------------");
        }catch (Exception e){
            System.out.println("exception " + e);
        }

        try {
            System.out.println("Test 3 starts");
            test3();
            System.out.println("Test 3 done");
            System.out.println("--------------------------------------------");
        }catch (Exception e){
            System.out.println("exception " + e);
        }
    }

    /**
     //     * Run first test of hw4
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

    /**
     //     * Run second test of hw4
     //     */
    public static void test2() {
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        Room room4 = new Room("Room 4");
        Room room5 = new Room("Room 5");
        Room room6 = new Room("Room 6");

        gameManager.addRoom(room1, null, null);
        gameManager.addRoom(room2, room1, Direction.NORTH);
        gameManager.addRoom(room3, room2, Direction.WEST);
        gameManager.addRoom(room4, room2, Direction.EAST);
        gameManager.addRoom(room5, room1, Direction.SOUTH);
        gameManager.addRoom(room6, room2, Direction.NORTH);

        Player player = new Player("Player 1", 5);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room6);

        gameManager.movePlayer(Direction.NORTH);
        gameManager.movePlayer(Direction.SOUTH);

        gameManager.movePlayer(Direction.WEST);
        gameManager.movePlayer(Direction.SOUTH);
        gameManager.movePlayer(Direction.EAST);

        gameManager.movePlayer(Direction.SOUTH);
        gameManager.movePlayer(Direction.SOUTH);
        gameManager.movePlayer(Direction.NORTH);

    }
    /**
     //     * Run second test of hw3
     //     */
    public static void test3() {
        Room room1 = new Room("Room 1");

        Room room2 = room1.clone();

        boolean areSame = room1 == room2;
        System.out.println("Are room1 and room2 the same: " + areSame);

        Relic item1 = new Relic("Item 1", 6);

        Relic item2 = item1.clone();

        areSame = item1 == item2;
        System.out.println("Are item1 and item2 the same: " + areSame);
    }
}
