package app;

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
    hp = 280 * level * (int) Math.round(Math.random() * 7);
    ad = 200 * level * (int) Math.round(Math.random() * 2);
    ap = 180 * level * (int) Math.round(Math.random() * 2);
    ar = 130 * level * (int) Math.round(Math.random() * 2);
    mr = 120 * level * (int) Math.round(Math.random() * 2);
    cr = 2 * level * (int) Math.round(Math.random() * 2);
    dr = 80;
    x = (int) Math.round(Math.random() * 14);
    y = (int) Math.round(Math.random() * 14);
  }

  void initMinionAttributes() {
    hp = 120 * level * (int) Math.round(Math.random() * 4);
    ad = 100 * level * (int) Math.round(Math.random() * 3);
    ap = 100 * level * (int) Math.round(Math.random() * 3);
    ar = 100 * level * (int) Math.round(Math.random() * 3);
    mr = 100 * level * (int) Math.round(Math.random() * 3);
    cr = 4 * level;
    dr = 20;
    x = (int) Math.round(Math.random() * 14);
    y = (int) Math.round(Math.random() * 14);
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