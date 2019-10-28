package app;

/**
 * InnerEnemy
 */
public class Map {

    String map1[][] = new String[15][15];

    Map() {
        this.map1 = map1();
    }

    String[][] map1() {
        String temp[][] = new String[15][15];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (i == 0 || i == (temp.length - 1)) {
                    temp[i][j] = "# ";
                } else if (j == 0 || j == (temp.length - 1)) {
                    temp[i][j] = "# ";
                } else {
                    temp[i][j] = "  ";
                }
            }
        }
        return temp;
    }
}