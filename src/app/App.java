package app;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
  static Map gameMap = new Map();
  static Enemy boss[] = new Enemy[15];
  static Enemy minion[][] = new Enemy[15][5];
  static Player player = new Player();
  static int mapLevel;
  static Scanner scanner = new Scanner(System.in);
  static String currentMap[][];
  static Item allItem[] = new Item[5];
  static Random random = new Random();

  static void itemInit() {
    allItem[0] = new Item("Red Buff", 0, 0, 100, 0, 0, 0, 10);
    allItem[1] = new Item("Blue Buff", 0, 0, 0, 100, 0, 0, 10);
    allItem[2] = new Item("First Aid Kit", 200, 0, 0, 0, 0, 0, 0);
    allItem[3] = new Item("Super Armor", 500, 0, 0, 0, 500, 500, 0);
    allItem[4] = new Item("Super Weapon", 0, 0, 500, 500, 0, 0, 0);
  }

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
      map[minion[mapLevel - 1][i].x][minion[mapLevel - 1][i].y] = "E ";
    }
    map[player.x][player.y] = "P ";
  }

  static void gameControPanel() {

    int option = 0;
    while (option != 1 && option != 2 && option != 3 && option != 4 && option != -1) {
      while (option != -1 && player.hp > 0) {
        // cScreenDelay(100);
        System.out.println();
        System.out.println("---------------------------");
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
        System.out.println("---------------------------");
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
    int option = 0;

    while (option != -1) {
      System.out.println();
      System.out.println("---------------------------");
      System.out.println("Inventory Control Panel");
      System.out.println();
      System.out.println("Enter the number of item you want to use.");
      System.out.println();
      for (int i = 0; i < player.inventory.size(); i++) {
        System.out.println(i + ". " + player.inventory.get(i).name);
      }
      System.out.println("-1. Exit");
      System.out.println("---------------------------");
      option = userOption();
      if (option == -1) {

      } else if (option < player.inventory.size() && option >= 0) {
        player.itemUse(option);
      }
    }
  }

  static void vsControlPanel(Enemy enemy) {
    int option = 0;
    while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != -1) {
      while (option != -1 && enemy.hp > 0 && player.hp > 0) {
        // cScreenDelay(1000);
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("VS Control Panel");
        System.out.println("Current Map Level " + mapLevel);
        System.out.println();
        // TODO:Change name
        System.out.println("You Meet a " + enemy.enemyType.toString() + " !!");
        System.out.println();
        System.out.println("Enemy Status : ");
        System.out.println(" HP : " + enemy.hp);
        System.out.println();
        System.out.println("Player Status : ");
        System.out.println(" HP : " + player.hp + "| MP : " + player.mp);
        System.out.println(" AD : " + player.ad + "| AP : " + player.ap);
        System.out.println(" AR : " + player.ar + "| MR : " + player.mr);
        System.out.println(" CR : " + player.cr);
        System.out.println(" Level : " + player.level);
        System.out.println();
        System.out.println("Cool Down Counting : ");
        System.out.println(" Q-CD : " + player.qCd + "| W-CD : " + player.wCd);
        System.out.println(" E-CD : " + player.eCd + "| R-CD : " + player.rCd);
        System.out.println();
        System.out.println("Control Option : ");
        System.out.println("1. Q");
        System.out.println("2. W");
        System.out.println("3. E");
        System.out.println("4. R");
        System.out.println("5. A");
        System.out.println("6. Inventory");
        System.out.println("-1. Exit");
        System.out.println("---------------------------");
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
          inventoryControlPanel();
        }

      }
    }
    if (enemy.hp <= 0) {
      currentMap[enemy.x][enemy.y] = "  ";
      player.levelCheck(100);
      if (enemy.isDrop() && enemy.enemyType == EnemyType.Boss) {
        int drc = (random.nextInt(100) + 1);
        if (drc == 26) {
          player.inventory.add(allItem[3]);
          System.out.println("You get a " + allItem[3].name);
        } else if (drc == 30) {
          player.inventory.add(allItem[4]);
          System.out.println("You get a " + allItem[4].name);
        } else {
          itemDrop();
        }
      } else if (enemy.isDrop() && enemy.enemyType == EnemyType.Minion) {
        itemDrop();
      } else {
        System.out.println("No item drop....");
      }
    }
    if (player.hp <= 0) {
      System.out.println("Game Over!!");
    }
    checkEnemy();
  }

  static void itemDrop() {
    int randItem = (random.nextInt(3) + 1);
    switch (randItem - 1) {
    case 0:
      player.inventory.add(allItem[0]);
      System.out.println("You get a " + allItem[0].name);
      break;
    case 1:
      player.inventory.add(allItem[1]);
      System.out.println("You get a " + allItem[1].name);
      break;
    case 2:
      player.inventory.add(allItem[2]);
      System.out.println("You get a " + allItem[2].name);
      break;
    default:
      break;
    }

  }

  static void checkEnemy() {
    int count = 0;
    ArrayList<Enemy> enemiesLrft = new ArrayList<Enemy>();
    for (int i = 0; i < 5; i++) {
      if (minion[mapLevel - 1][i].hp <= 0) {
        count += 1;
      } else {
        enemiesLrft.add(minion[mapLevel - 1][i]);
      }
    }
    if (boss[mapLevel - 1].hp <= 0) {
      count += 1;
    }
    if (count == 5) {
      currentMap[boss[mapLevel - 1].x][boss[mapLevel - 1].y] = "B ";
    } else if (count == 6) {
      printStageClear();
      stageClearMap();
    } else {
      for (Enemy enemy : enemiesLrft) {
        currentMap[enemy.x][enemy.y] = "E ";
      }
    }
  }

  static void stageClearMap() {
    currentMap[11][14] = "⇀ ";
    currentMap[12][14] = "  ";
    currentMap[13][14] = "⇁ ";
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

      int enemyTA = damageCalc(eAd, player.ar) + damageCalc(eAp, player.mr);

      enemy.hp -= damageCalc(pxA, enemy.ar);
      System.out.println("You produce " + damageCalc(pxA, enemy.ar) + " Damage");

      if (enemyA > (enemy.ad + enemy.ap)) {
        System.out.println("Enemy Critical hit !! " + "Enemy produce " + enemyTA + " Damage");
      } else {
        System.out.println("Enemy produce " + enemyTA + " Damage");
      }
      player.hp -= enemyTA;
      System.out.println("Player HP left " + player.hp);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      break;
    default:
      break;
    }
  }

  static void mainControlPanel() {
    int option = 0;
    while (option != 1 && option != 2) {
      // cScreenDelay(100);
      System.out.println();
      System.out.println("Wellcome to DeepDark ");
      System.out.println();
      System.out.println("---------------------------");
      System.out.println("Control Option : ");
      System.out.println("1. Start");
      System.out.println("2. Exit");
      System.out.println("---------------------------");
      option = userOption();
    }
    if (option == 1) {
      printStart();
      gameControPanel();
    }
  }

  static void applyDamage(Enemy enemy, int px) {
    if (px == 0) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("Cooling Down ..");
    } else if (px == -1) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("No more mp...");
      System.out.println("Use A can increase your MP..");
    } else {
      int eAd = enemy.adA();
      int eAp = enemy.apA();
      int enemyA = eAd + eAp;
      int enemyTA = damageCalc(eAd, player.ar) + damageCalc(eAp, player.mr);
      enemy.hp -= damageCalc(px, enemy.mr);
      System.out.println("Produce " + damageCalc(px, enemy.mr) + " Damage");
      if (enemyA > (enemy.ad + enemy.ap)) {
        System.out.println("Enemy Critical hit !! " + "Enemy produce " + enemyTA + " Damage");
      } else {
        System.out.println("Enemy produce " + enemyTA + " Damage");
      }
      player.hp -= enemyTA;
      System.out.println("Player HP left " + player.hp);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
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
      if (x == 12 && y == 14) {
        mapLevel += 1;
        if (mapLevel >= 15) {
          // TODO: Game End
          System.out.println("Mission Complete");
        }
        currentMap[11][14] = "# ";
        currentMap[12][14] = "# ";
        currentMap[13][14] = "# ";

        player.x = 2;
        player.y = 0;
        // currentMap = gameMap.getMap(mapLevel);
        initMap(map);
      }
    } else {
      System.out.println("Don't hit the wall !!");
    }
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
    System.out.println();
    System.out.println("    _/          _/  _/_/_/_/  _/        _/          _/_/_/    _/_/    _/      _/  _/_/_/_/");
    System.out.println("    _/          _/  _/        _/        _/        _/        _/    _/  _/_/  _/_/  _/      ");
    System.out.println("   _/    _/    _/  _/_/_/    _/        _/        _/        _/    _/  _/  _/  _/  _/_/_/   ");
    System.out.println("    _/  _/  _/    _/        _/        _/        _/        _/    _/  _/      _/  _/        ");
    System.out.println("     _/  _/      _/_/_/_/  _/_/_/_/  _/_/_/_/    _/_/_/    _/_/    _/      _/  _/_/_/_/   ");
    // cScreenDelay(1000);
    System.out.println();
    System.out.println("                                  _/_/_/_/_/    _/_/                                      ");
    System.out.println("                                     _/      _/    _/                                     ");
    System.out.println("                                    _/      _/    _/                                      ");
    System.out.println("                                   _/      _/    _/                                       ");
    System.out.println("                                  _/        _/_/                                          ");
    // cScreenDelay(1000);
    System.out.println();
    System.out.println("     _/_/_/    _/_/_/_/  _/_/_/_/  _/_/_/        _/_/_/      _/_/    _/_/_/    _/    _/   ");
    System.out.println("     _/    _/  _/        _/        _/    _/      _/    _/  _/    _/  _/    _/  _/  _/     ");
    System.out.println("    _/    _/  _/_/_/    _/_/_/    _/_/_/        _/    _/  _/_/_/_/  _/_/_/    _/_/        ");
    System.out.println("   _/    _/  _/        _/        _/            _/    _/  _/    _/  _/    _/  _/  _/       ");
    System.out.println(" _/_/_/    _/_/_/_/  _/_/_/_/  _/            _/_/_/    _/    _/  _/    _/  _/    _/       ");
    System.out.println();
  }

  static void printStageClear() {
    // cScreenDelay(1000);
    System.out.println();
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
    System.out.println();
  }

  static void printStart() {
    // cScreenDelay(1000);
    System.out.println();
    System.out.println("    _/_/_/    _/_/_/_/    _/_/    _/_/_/    _/      _/       ");
    System.out.println("   _/    _/  _/        _/    _/  _/    _/    _/  _/          ");
    System.out.println("  _/_/_/    _/_/_/    _/_/_/_/  _/    _/      _/             ");
    System.out.println(" _/    _/  _/        _/    _/  _/    _/      _/              ");
    System.out.println("_/    _/  _/_/_/_/  _/    _/  _/_/_/        _/               ");
    // cScreenDelay(1000);
    System.out.println();
    System.out.println("       _/_/_/    _/_/        _/      _/       ");
    System.out.println("    _/        _/    _/      _/      _/        ");
    System.out.println("   _/  _/_/  _/    _/      _/      _/         ");
    System.out.println("  _/    _/  _/    _/                          ");
    System.out.println("   _/_/_/    _/_/        _/      _/           ");
    System.out.println();
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
    itemInit();
    printBanner();
    mainControlPanel();
  }
}