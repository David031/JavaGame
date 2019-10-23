package app;

public class App {
    static String map1[][] = new String[15][15];
    static int playerLocationX = 1;
    static int playerLocationY = 1;
    static Enemy boss;

    public static void init() {

        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1.length; j++) {
                if (i == 0 || i == (map1.length - 1)) {
                    map1[i][j] = "# ";
                } else if (j == 0 || j == (map1.length - 1)) {
                    map1[i][j] = "# ";
                } else {
                    map1[i][j] = "  ";
                }
            }
        }
    }

    public static void playerMove(int x, int y) {
        int oldx = playerLocationX;
        int oldy = playerLocationY;
        playerLocationX = x;
        playerLocationY = y;
        map1[oldx][oldy] = "  ";
        map1[playerLocationX][playerLocationY] = "P ";
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void controlPanel() {

    }

    public static void print(String string) {
        System.out.println(string);
    }

    public static void printMap() {

        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1.length; j++) {
                System.out.print(map1[i][j]);
            }
            System.out.println();
        }
    }

    public static void cScreenDelay() {

        try {
            Thread.sleep(1000);
            clearScreen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        printMap();
        playerMove(2, 2);
        cScreenDelay();
        printMap();
        playerMove(8, 10);
        cScreenDelay();
        printMap();
    }
}