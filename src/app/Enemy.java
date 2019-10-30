package app;

import java.util.Random;

public class Enemy {
  int hp;
  int ad;
  int ap;
  int ar;
  int mr;
  int cr;
  int dr;
  int x;
  int y;
  EnemyType enemyType;
  int mapLevel;
  Random random = new Random();

  Enemy(EnemyType type, int mapLevel) {
    this.enemyType = type;
    this.mapLevel = mapLevel;
    switch (type) {
    case Boss:
      initBossAttributes();
      break;
    case Minion:
      initMinionAttributes();
      break;
    default:
      break;
    }
  }

  void initBossAttributes() {
    this.hp = 280 * mapLevel * (random.nextInt(7) + 1);
    this.ad = 200 * mapLevel;
    this.ap = 180 * mapLevel;
    this.ar = 130 * mapLevel * (random.nextInt(2) + 1);
    this.mr = 120 * mapLevel * (random.nextInt(2) + 1);
    this.cr = 2 * mapLevel * (random.nextInt(2) + 1);
    this.dr = 80;
    this.x = (random.nextInt(13) + 1);
    this.y = (random.nextInt(13) + 1);
  }

  void initMinionAttributes() {
    this.hp = 120 * mapLevel;
    this.ad = 100 * mapLevel;
    this.ap = 100 * mapLevel;
    this.ar = 100 * mapLevel;
    this.mr = 100 * mapLevel;
    this.cr = 4 + mapLevel;
    this.dr = 20;
    this.x = (random.nextInt(13) + 1);
    this.y = (random.nextInt(13) + 1);
  }

  boolean isCC() {
    int crc = (random.nextInt(100) + 1);
    if (crc <= cr) {
      return true;
    } else {
      return false;
    }
  }

  int adA() {
    if (isCC()) {
      return ad * 2;
    } else {
      return ad;
    }
  }

  int apA() {
    if (isCC()) {
      return ap * 2;
    } else {
      return ap;
    }
  }

  boolean isDrop() {
    // TODO:Drop Item
    return false;
  }
}

enum EnemyType {
  Boss, Minion,
}