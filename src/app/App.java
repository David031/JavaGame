package app;

import java.util.Scanner;

public class App {
  static Map gameMap = new Map();  
  static int px;
  static int py;
  static Enemy boss[] = new Enemy[15];
  static Enemy minion[][] = new Enemy[15][5];
  static Player player = new Player();
  static int mapLevel;
  static Scanner scanner = new Scanner(System.in);
  static String currentMap[][];
  
  static void init() {
    px = 2;
    py = 0;
    mapLevel = 1;
    for (int i = 0; i < boss.length; i++) {
      boss[i] = new Enemy(EnemyType.Boss, i + 1);
    }
    for (int i = 0; i < minion.length; i++) {
      for (int j = 0; j < 5; j++) {
        minion[i][j] = new Enemy(EnemyType.Minion, i + 1);
      }
    }
    currentMap = gameMap.map1;
    initMap(currentMap);
    // printBanner();
  }

  static void initMap(String map [][]) {
    for (int i = 0; i < 5; i++) {
      map[minion[0][i].x][minion[0][i].y] = "E ";
    }
    map[px][py] = "P ";
  }

  static void gameControPanel() {
    cScreenDelay();
    System.out.println("Game Control Panel");
    printMap(currentMap);
  }

  static void vsControlPanel() {
    cScreenDelay();
    System.out.println("Frighting Control Panel");
  }

  static void inventoryControlPanel() {
    cScreenDelay();
    System.out.println("Inventory Control Panel");
  }

  static void vsPart(Enemy enemy) {

  }

  static void mainControlPanel() {
    cScreenDelay();
    System.out.println("Wellcome to DeepDark ");
    System.out.println("1. Start");
    System.out.println("2. Inventory");
    int option = 0;
    System.out.println(minion);
    while (option != 1 && option != 2) {
      option = userOption();
    }

    if (option == 1) {
      gameControPanel();
    } else {
      inventoryControlPanel();
    }
  }

  static void playerMove(int x, int y, String map[][]) {
    int oldx = px;
    int oldy = py;
    px = x;
    py = y;
    map[oldx][oldy] = "  ";
    map[px][py] = "P ";
    cScreenDelay();
    printMap(map);
  }

  static void cScreenDelay() {
    try {
      Thread.sleep(1000);
      clearScreen();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
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

  }

  static void printStageClear() {
    cScreenDelay();
    System.out.println(
        "       _/_/_/  _/_/_/_/_/    _/_/      _/_/_/  _/_/_/_/        _/_/_/  _/        _/_/_/_/    _/_/    _/_/_/        _/  _/  ");
    System.out.println(
        "    _/            _/      _/    _/  _/        _/            _/        _/        _/        _/    _/  _/    _/      _/  _/   ");
    System.out.println(
        "     _/_/        _/      _/_/_/_/  _/  _/_/  _/_/_/        _/        _/        _/_/_/    _/_/_/_/  _/_/_/        _/  _/    ");
    System.out.println(
        "        _/      _/      _/    _/  _/    _/  _/            _/        _/        _/        _/    _/  _/    _/                 ");
    System.out.println(
        " _/_/_/        _/      _/    _/    _/_/_/  _/_/_/_/        _/_/_/  _/_/_/_/  _/_/_/_/  _/    _/  _/    _/      _/  _/      ");

  }

  static void printStart() {
    cScreenDelay();
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

  static int userOption() {
    System.out.print("Please input your option : ");
    return scanner.nextInt();
  }

  static void printMap(String[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map.length; j++) {
        System.out.print(map[i][j]);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) throws Exception {
    init();
    printMap(currentMap);
  }
}