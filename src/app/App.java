package app;

public class App {
  static String map1[][] = new String[15][15];
  static int  px;
  static int  py;
  static Enemy boss[] = new  Enemy[15];
  static Enemy minion[][] = new Enemy[15][5];

  static void init() {
     px = 1;
     py = 1;
     for (int i = 0; i < boss.length; i++) {
       boss[i] = new Enemy(EnemyType.Boss, i+1);
     }
     for (int i = 0; i < minion.length; i++) {
       for (int j = 0; j < minion[i].length; j++) {
         minion[i][j] = new Enemy(EnemyType.Minion, i+1);
       }
     }
    initMap1();
    printBanner();
  }

  static void initMap1() {
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

  static void initMap2() {
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

  static void playerMove(int x, int y) {
    int oldx =  px;
    int oldy =  py;
     px = x;
     py = y;
    map1[oldx][oldy] = "  ";
    map1[ px][ py] = "P ";
    cScreenDelay();
  }

  static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  static void controlPanel() {

  }

  static void printMap(String[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map.length; j++) {
        System.out.print(map[i][j]);
      }
      System.out.println();
    }
  }

  static void cScreenDelay() {

    try {
      Thread.sleep(1000);
      clearScreen();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static void printBanner() {
    System.out.println("    _/          _/  _/_/_/_/  _/        _/          _/_/_/    _/_/    _/      _/  _/_/_/_/");
    System.out.println("    _/          _/  _/        _/        _/        _/        _/    _/  _/_/  _/_/  _/      ");
    System.out.println("   _/    _/    _/  _/_/_/    _/        _/        _/        _/    _/  _/  _/  _/  _/_/_/   ");
    System.out.println("    _/  _/  _/    _/        _/        _/        _/        _/    _/  _/      _/  _/        ");
    System.out.println("     _/  _/      _/_/_/_/  _/_/_/_/  _/_/_/_/    _/_/_/    _/_/    _/      _/  _/_/_/_/   ");
    cScreenDelay();
    System.out.println("                                  _/_/_/_/_/    _/_/                                      ");
    System.out.println("                                     _/      _/    _/                                     ");
    System.out.println("                                    _/      _/    _/                                      ");
    System.out.println("                                   _/      _/    _/                                       ");
    System.out.println("                                  _/        _/_/                                          ");
    cScreenDelay();
    System.out.println("     _/_/_/    _/_/_/_/  _/_/_/_/  _/_/_/        _/_/_/      _/_/    _/_/_/    _/    _/   ");
    System.out.println("     _/    _/  _/        _/        _/    _/      _/    _/  _/    _/  _/    _/  _/  _/     ");
    System.out.println("    _/    _/  _/_/_/    _/_/_/    _/_/_/        _/    _/  _/_/_/_/  _/_/_/    _/_/        ");
    System.out.println("   _/    _/  _/        _/        _/            _/    _/  _/    _/  _/    _/  _/  _/       ");
    System.out.println(" _/_/_/    _/_/_/_/  _/_/_/_/  _/            _/_/_/    _/    _/  _/    _/  _/    _/       ");
    cScreenDelay();
  }

  static void printStageClear() {
    System.out.println("       _/_/_/  _/_/_/_/_/    _/_/      _/_/_/  _/_/_/_/        _/_/_/  _/        _/_/_/_/    _/_/    _/_/_/        _/  _/  ");
    System.out.println("    _/            _/      _/    _/  _/        _/            _/        _/        _/        _/    _/  _/    _/      _/  _/   ");
    System.out.println("     _/_/        _/      _/_/_/_/  _/  _/_/  _/_/_/        _/        _/        _/_/_/    _/_/_/_/  _/_/_/        _/  _/    ");
    System.out.println("        _/      _/      _/    _/  _/    _/  _/            _/        _/        _/        _/    _/  _/    _/                 ");
    System.out.println(" _/_/_/        _/      _/    _/    _/_/_/  _/_/_/_/        _/_/_/  _/_/_/_/  _/_/_/_/  _/    _/  _/    _/      _/  _/      ");
    cScreenDelay();
  }

  static void printStart(){
    System.out.println("    _/_/_/    _/_/_/_/    _/_/    _/_/_/    _/      _/       ");
    System.out.println("   _/    _/  _/        _/    _/  _/    _/    _/  _/          ");
    System.out.println("  _/_/_/    _/_/_/    _/_/_/_/  _/    _/      _/             ");
    System.out.println(" _/    _/  _/        _/    _/  _/    _/      _/              ");
    System.out.println("_/    _/  _/_/_/_/  _/    _/  _/_/_/        _/               ");
    cScreenDelay();
    System.out.println("       _/_/_/    _/_/        _/      _/       ");
    System.out.println("    _/        _/    _/      _/      _/        ");
    System.out.println("   _/  _/_/  _/    _/      _/      _/         ");
    System.out.println("  _/    _/  _/    _/                          ");
    System.out.println("   _/_/_/    _/_/        _/      _/           ");
  }
  public static void main(String[] args) throws Exception {
    init();
    // printStart();
    // printMap(map1);
    // playerMove(2, 2);
    // printMap(map1);
    // playerMove(8, 10);
    // printMap(map1);
  }
}