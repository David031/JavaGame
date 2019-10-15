package app;

public class App {
    static int map1[][] = new int[15][15];
    static int playerLocationX;
    static int playerLocationY;

    static void init() {
        playerLocationX = 10;
        playerLocationY = 10;

        for (int i = 0; i < map1.length; i++) {

        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void controlPanel() {

    }

    static void printMap() {
        String temp = "";
        for (int i = 0; i < 10; i++) {
            clearScreen();
            temp = temp + "*";
            System.out.println(temp);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        init();
        printMap();

    }
}