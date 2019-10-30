package app;

import java.util.Scanner;

public class App {
  static Map gameMap = new Map();
  static Enemy boss[] = new Enemy[15];
  static Enemy minion[][] = new Enemy[15][5];
  static Player player = new Player();
  static int mapLevel;
  static Scanner scanner = new Scanner(System.in);
  static String currentMap[][];

  static void init() {
    mapLevel = 1;
    for (int i = 0; i < boss.length; i++) {
      boss[i] = new Enemy(EnemyType.Boss, i + 1);
    }
    for (int i = 0; i < minion.length; i++) {
      for (int j = 0; j < 5; j++) {
        minion[i][j] = new Enemy(EnemyType.Minion, i + 1);
      }
    }
    currentMap = gameMap.getMap(mapLevel);
    initMap(currentMap);
    // printBanner();
  }

  static void initMap(String map[][]) {
    for (int i = 0; i < 5; i++) {
      map[minion[0][i].x][minion[0][i].y] = "E ";
    }
    map[player.x][player.y] = "P ";
  }

  static void gameControPanel() {

    int option = 0;
    while (option != 1 && option != 2 && option != 3 && option != 4 && option != -1) {
      while (option != -1 && player.hp > 0) {
        // cScreenDelay(100);
        System.out.println("Game Control Panel");
        System.out.println("--Game Map--");
        printMap(currentMap);
        System.out.println("Current Map Level " + mapLevel);
        System.out.println();
        System.out.println("Player Status : ");
        System.out.println(" HP : " + player.hp);
        System.out.println(" MP : " + player.mp);
        System.out.println(" Level : " + player.level);
        System.out.println(" XP : " + player.xp);
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
          playerMove(player.x - 1, player.y, currentMap);
        } else if (option == 2) {
          playerMove(player.x + 1, player.y, currentMap);
        } else if (option == 3) {
          playerMove(player.x, player.y - 1, currentMap);
        } else if (option == 4) {
          playerMove(player.x, player.y + 1, currentMap);
        } else if (option == 5) {
          inventoryControlPanel();

        }
      }

    }
  }

  static void inventoryControlPanel() {
    // cScreenDelay(100);
    System.out.println("Inventory Control Panel");
    // TODO:Inventory
  }

  static void vsControlPanel(Enemy enemy) {
    int option = 0;
    while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != -1) {
      while (option != -1 && enemy.hp > 0 && player.hp > 0) {
        // cScreenDelay(1000);
        System.out.println("VS Control Panel");
        System.out.println("Current Map Level " + mapLevel);
        System.out.println();
        // TODO:Change name
        System.out.println("You Meet a Minion !!");
        System.out.println();
        System.out.println("Enemy Status : ");
        System.out.println(" HP : " + enemy.hp);
        System.out.println();
        System.out.println("Player Status : ");
        System.out.println(" HP : " + player.hp);
        System.out.println(" MP : " + player.mp);
        System.out.println();
        System.out.println("Control Option : ");
        System.out.println("1. Q");
        System.out.println("2. W");
        System.out.println("3. E");
        System.out.println("4. R");
        System.out.println("5. A");
        System.out.println("6. Inventory");
        System.out.println("-1. Exit");
        option = userOption();

        if (option == 1) {
          fight(enemy, XType.Q);
        } else if (option == 2) {
          fight(enemy, XType.W);
        } else if (option == 3) {
          fight(enemy, XType.E);
        } else if (option == 4) {
          fight(enemy, XType.R);
        } else if (option == 5) {
          fight(enemy, XType.A);
        } else if (option == 6) {

        }

      }
    }
    if (enemy.hp <= 0) {
      // TODO: After Enemy dead
      currentMap[enemy.x][enemy.y] = "  ";
      player.levelCheck(100);
    }
    if (player.hp <= 0) {
      System.out.println("Game Over!!");
    }
  }

  static void checkEnemy() {
    int count = 0;
    for (int i = 0; i < 5; i++) {
      if (minion[mapLevel - 1][i].hp <= 0) {
        count = +1;
      }
    }
    if (boss[mapLevel - 1].hp <= 0) {
      count += 1;
    }
    if (count == 5) {
      currentMap[boss[mapLevel - 1].x][boss[mapLevel - 1].y] = "B ";
    } else if (count == 6) {
      mapLevel += 1;
      currentMap = gameMap.getMap(mapLevel);
      initMap(currentMap);
    }
  }

  static int damageCalc(int damge, int def) {
    int calcdam = (damge - def);
    if (calcdam <= 0) {
      return 0;
    } else {
      return calcdam;
    }
  }

  static void fight(Enemy enemy, XType type) {
    // TODO:Fight
    switch (type) {
    case Q:
      applyDamage(enemy, player.xQ());
      break;
    case W:
      applyDamage(enemy, player.xW());
      break;
    case E:
      applyDamage(enemy, player.xE());
      break;
    case R:
      // AP
      applyDamage(enemy, player.xR());
      break;
    case A:
      // AD
      int pxA = player.xA();
      int eAd = enemy.adA();
      int eAp = enemy.apA();
      int enemyA = eAd + eAp;
      enemy.hp -= damageCalc(pxA, enemy.ar);
      System.out.println("Produce " + damageCalc(pxA, enemy.ar) + " Damage");

      if (enemyA > (enemy.ad + enemy.ap)) {
        System.out.println("Enemy Critical hit !! ");
      }
      // TODO:Enemy Damage
      player.hp -= damageCalc(eAd, player.ar) + damageCalc(eAp, player.mr);
      System.out.println("HP left " + player.hp);
      break;
    default:
      break;
    }
  }

  static void mainControlPanel() {
    int option = 0;
    while (option != 1 && option != 2) {
      // cScreenDelay(100);
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

  static void applyDamage(Enemy enemy, int px) {
    if (px == 0) {
      if (player.mp <= 0) {
        System.out.println("No more mp...");
      } else {
        System.out.println("Cooling Down ..");
      }

    } else {
      int eAd = enemy.adA();
      int eAp = enemy.apA();
      int enemyA = eAd + eAp;
      enemy.hp -= damageCalc(px, enemy.mr);
      System.out.println("Produce " + damageCalc(px, enemy.mr) + " Damage");
      if (enemyA > (enemy.ad + enemy.ap)) {
        System.out.println("Enemy Critical hit !! ");
      }
      player.hp -= damageCalc(eAd, player.ar) + damageCalc(eAp, player.mr);
    }
  }

  static Enemy getEnemyByXY(int x, int y, EnemyType type) {
    Enemy tEnemy = new Enemy(type, mapLevel);

    if (type == EnemyType.Minion) {
      for (Enemy enemy : minion[mapLevel - 1]) {
        if (enemy.x == x && enemy.y == y) {
          tEnemy = enemy;
        }
      }
    } else {
      tEnemy = boss[mapLevel - 1];
    }
    return tEnemy;
  }

  static void playerMove(int x, int y, String map[][]) {
    int oldx = player.x;
    int oldy = player.y;
    if (x >= 0 && y >= 0) {
      if (map[x][y] == "  ") {
        player.x = x;
        player.y = y;
        map[oldx][oldy] = "  ";
        map[player.x][player.y] = "P ";
      } else if (map[x][y] == "E ") {
        System.out.println(" You see Enemy !!");
        vsControlPanel(getEnemyByXY(x, y, EnemyType.Minion));
      } else if (map[x][y] == "B ") {
        System.out.println(" You see Boss !!");
        vsControlPanel(getEnemyByXY(x, y, EnemyType.Boss));
      } else {
        System.out.println("Don't hit the wall !!");
      }
    } else {
      System.out.println("Don't hit the wall !!");
    }
    // cScreenDelay(1000);
    printMap(map);
  }

  static void cScreenDelay(int ms) {
    try {
      Thread.sleep(ms);
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
    // cScreenDelay(1000);
    System.out.println("                                  _/_/_/_/_/    _/_/                                      ");
    System.out.println("                                     _/      _/    _/                                     ");
    System.out.println("                                    _/      _/    _/                                      ");
    System.out.println("                                   _/      _/    _/                                       ");
    System.out.println("                                  _/        _/_/                                          ");
    // cScreenDelay(1000);
    System.out.println("     _/_/_/    _/_/_/_/  _/_/_/_/  _/_/_/        _/_/_/      _/_/    _/_/_/    _/    _/   ");
    System.out.println("     _/    _/  _/        _/        _/    _/      _/    _/  _/    _/  _/    _/  _/  _/     ");
    System.out.println("    _/    _/  _/_/_/    _/_/_/    _/_/_/        _/    _/  _/_/_/_/  _/_/_/    _/_/        ");
    System.out.println("   _/    _/  _/        _/        _/            _/    _/  _/    _/  _/    _/  _/  _/       ");
    System.out.println(" _/_/_/    _/_/_/_/  _/_/_/_/  _/            _/_/_/    _/    _/  _/    _/  _/    _/       ");

  }

  static void printStageClear() {
    // cScreenDelay(1000);
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
    // cScreenDelay(1000);
    System.out.println("    _/_/_/    _/_/_/_/    _/_/    _/_/_/    _/      _/       ");
    System.out.println("   _/    _/  _/        _/    _/  _/    _/    _/  _/          ");
    System.out.println("  _/_/_/    _/_/_/    _/_/_/_/  _/    _/      _/             ");
    System.out.println(" _/    _/  _/        _/    _/  _/    _/      _/              ");
    System.out.println("_/    _/  _/_/_/_/  _/    _/  _/_/_/        _/               ");
    // cScreenDelay(1000);
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