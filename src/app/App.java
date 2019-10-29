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

  static void initMap(String map[][]) {
    for (int i = 0; i < 5; i++) {
      map[minion[0][i].x][minion[0][i].y] = "E ";
    }
    map[px][py] = "P ";
  }

  static void gameControPanel() {

    int option = 0;
    while (option != 1 && option != 2 && option != 3 && option != 4 && option != -1) {
      while (option != -1) {
        cScreenDelay();
        System.out.println("Game Control Panel");
        System.out.println("--Game Map--");
        printMap(currentMap);
        System.out.println();
        System.out.println("Current Map Level " + mapLevel);
        System.out.println("Player Status : ");
        System.out.println(" HP " + player.hp);
        System.out.println(" MP " + player.mp);
        System.out.println();
        System.out.println("Control Option : ");
        System.out.println("1. Move Up");
        System.out.println("2. Move Down");
        System.out.println("3. Move Left");
        System.out.println("4. Move Right");
        System.out.println("5. Inventory");
        System.out.println("-1. Exit Game");
        option = userOption();

        if (option == 1) {
          playerMove(px - 1, py, currentMap);
        } else if (option == 2) {
          playerMove(px + 1, py, currentMap);
        } else if (option == 3) {
          playerMove(px, py - 1, currentMap);
        } else if (option == 4) {
          playerMove(px, py + 1, currentMap);
        } else if (option == 5) {
          inventoryControlPanel();
          //TODO:Inventory
        }
      }

    }
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
    int option = 0;
    while (option != 1 && option != 2) {
      cScreenDelay();
      System.out.println("Wellcome to DeepDark ");
      System.out.println("Control Option : ");
      System.out.println("1. Start");
      System.out.println("2. Exit");
      option = userOption();
    }
    if (option == 1) {
      gameControPanel();
    }
  }

  static void playerMove(int x, int y, String map[][]) {
    int oldx = px;
    int oldy = py;
    if (x >= 0 && y >= 0) {
      if (map[x][y] == "  ") {
        px = x;
        py = y;
        map[oldx][oldy] = "  ";
        map[px][py] = "P ";
      } else if (map[x][y] == "E ") {
        System.out.println(" You see Enemy !!");
        //TODO: Fighting
      } else if (map[x][y] == "B ") {
        System.out.println(" You see Boss !!");
         //TODO: Fighting
      } else {
        System.out.println("Don't hit the wall !!");
      }
    } else {
      System.out.println("Don't hit the wall !!");
    }
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
    mainControlPanel();
  }
}