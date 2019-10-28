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
  int level;
  Random random = new Random();

  Enemy(EnemyType type, int level) {
    this.enemyType = type;
    this.level = level;
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
    hp = 280 * level * random.nextInt(7) + 1;
    ad = 200 * level * random.nextInt(2) + 1;
    ap = 180 * level * random.nextInt(2) + 1;
    ar = 130 * level * random.nextInt(2) + 1;
    mr = 120 * level * random.nextInt(2) + 1;
    cr = 2 * level * random.nextInt(2) + 1;
    dr = 80;
    x = random.nextInt(13) + 1;
    y = random.nextInt(13) + 1;
  }

  void initMinionAttributes() {
    hp = 120 * level * random.nextInt(4) + 1;
    ad = 100 * level * random.nextInt(3) + 1;
    ap = 100 * level * random.nextInt(3) + 1;
    ar = 100 * level * random.nextInt(3) + 1;
    mr = 100 * level * random.nextInt(3) + 1;
    cr = 4 * level;
    dr = 20;
    x = random.nextInt(13) + 1;
    y = random.nextInt(13) + 1;
  }

  boolean isCC() {
    return false;
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
    return false;
  }
}

enum EnemyType {
  Boss, Minion,
}