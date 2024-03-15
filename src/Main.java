public class Main {
    public static void main(String[] args) {
        System.out.println("Test 1 starts");
        test1();
        System.out.println("Test 1 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 2 starts");
        test2();
        System.out.println("Test 2 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 3 starts");
        test3();
        System.out.println("Test 3 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 4 starts");
        test4();
        System.out.println("Test 4 done");
        System.out.println("--------------------------------------------");



    }

    /**
     * Run first test of hw2
     */
    public static void test1(){
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 1");

        System.out.println("Are room1 and room2 equal: " + room1.equals(room2));
        System.out.println("Are room2 and room1 equal: " + room2.equals(room1));

        System.out.println("Are room1 and room3 equal: " + room1.equals(room3));

        Item item1 = new Bag("Bag 1", 5, 5);
        Item item2 = new Bag("Bag 2", 5, 5);
        Item item3 = new LargeBag("Bag 1", 5, 5);

        System.out.println("Are item1 and item2 equal: " + item1.equals(item2));
        System.out.println("Are item2 and item1 equal: " + item2.equals(item1));

        System.out.println("Are item1 and item3 equal: " + item1.equals(item3));
        System.out.println("Are item3 and item1 equal: " + item3.equals(item1));
    }
    /**
     * Run second test of hw2
     */
    public static void test2(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 1");

        Item item1 = new Relic("Item 1", 9);
        Item item2 = new Key("Item 2", 4);
        Item item3 = new Relic("Item 3", 5);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);
        gameManager.addItem(room1, item1);
        gameManager.addRoom(room3);


        System.out.println("Are room1 and room3 equal: " + room1.equals(room3));

        gameManager.addItem(room3, item3);
        System.out.println("Are room1 and room3 equal: " + room1.equals(room3));
        gameManager.addItem(room3, item2);
        System.out.println("Are room1 and room3 equal: " + room1.equals(room3));


    }
    /**
     * Run third test of hw2
     */
    public static void test3(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 2");


        Item item1 = new Relic("Item 1", 2);
        Item item2 = new Key("Key 2", 6);
        Item item3 = new Relic("Item 3", 7);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);

        gameManager.removeRoom(room3);
        gameManager.addRoom(room2);

        gameManager.addItem(room2, item1);
        gameManager.addItem(room2, item2);
        gameManager.addItem(room1, item3);

        Player player = new Player("Player 1", 2);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.connectRooms(room1, room2, Direction.NORTH);

        gameManager.pickUpItem(item3);
        gameManager.activatePuzzle(room1);

        gameManager.movePlayer(Direction.NORTH);
        gameManager.solvePuzzle();
        gameManager.movePlayer(Direction.NORTH);

        gameManager.dropItem(item3);
        gameManager.solvePuzzle();
        gameManager.pickUpItem(item1);
        gameManager.activatePuzzle(room2);

        gameManager.movePlayer(Direction.SOUTH);
        gameManager.dropItem(item3);
        gameManager.pickUpItem(item3);
        gameManager.useItem(item2);
        gameManager.movePlayer(Direction.SOUTH);

        Item item4 = new Relic("Item 4", 7);
        gameManager.disassembleItem(item1);
        gameManager.useItem(item4);
        gameManager.disassembleItem(item4);
        gameManager.pickUpItem(item4);
    }
    /**
     * Run fourth test of hw2
     */
    public static void test4(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");


        Item item1 = new Bag("Bag 1", 5, 3);
        Item item2 = new Key("Key 2", 6);
        Item item3 = new Relic("Item 3", 7);
        Item item4 = new Relic("Relic 4", 2);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);

        gameManager.connectRooms(room1, room2, Direction.NORTH);

        gameManager.addItem(room2, item1);
        gameManager.addItem(room2, item2);
        gameManager.addItem(room1, item3);
        gameManager.addItem(room1, item4);

        Player player = new Player("Player 1", 2);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.pickUpItem(item3);
        gameManager.pickUpItem(item4);

        gameManager.movePlayer(Direction.NORTH);
        gameManager.solvePuzzle();

        gameManager.useItem(item4);
        gameManager.useItem(item3);

        gameManager.useItem(item1);
        gameManager.useItem(item2);
        gameManager.pickUpItem(item2);
        gameManager.pickUpItem(item1);

        gameManager.activatePuzzle(room2);
        gameManager.movePlayer(Direction.SOUTH);

        gameManager.useItem(item4);
        gameManager.useItem(item3);

        gameManager.dropItem(item3);
    }
}
