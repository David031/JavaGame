package app;

/**
 * InnerEnemy
 */
public class Map {

    String map1[][] = new String[15][15];

    Map() {
        this.map1 = setMap1();
    }

    String[][] getMap(int mapLevel) {
        String map[][] = new String[15][15];
        switch (mapLevel) {
        case 1:
            map = map1;
            break;

        default:
            break;
        }
        return map;
    }

    String[][] setMap1() {
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
        temp[1][0] = "⇀ ";
        temp[3][0] = "⇁ ";
        return temp;
    }
}