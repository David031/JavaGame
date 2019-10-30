package app;

import java.util.ArrayList;
import java.util.Random;

public class Player {
  int hp;
  int mp;
  int ad;
  int ap;
  int ar;
  int mr;
  int cr;
  int x;
  int y;
  int level;
  int xp;
  int qCd;
  int wCd;
  int eCd;
  int rCd;
  ArrayList<Item> inventory = new ArrayList<Item>();
  Random random = new Random();

  Player() {
    this.hp = 120;
    this.mp = 100;
    this.ad = 100;
    this.ap = 100;
    this.ar = 100;
    this.mr = 100;
    this.cr = 0;
    this.x = 2;
    this.y = 0;
    this.level = 1;
    this.xp = 0;
    this.qCd = 0;
    this.wCd = 0;
    this.eCd = 0;
    this.rCd = 0;
  }

  void levelCheck(int xpAdd) {
    xp += xpAdd;
    if (xp >= (level * 5)) {
      level += 1;
      xp -= (level * 5);
      levelUp();
    }

  }

  void levelUp() {
    hp *= 1.2;
    mp *= 1.2;
    ad *= 1.2;
    ap *= 1.2;
    ar *= 1.2;
    mr *= 1.2;
  }

  boolean isCC() {
    int crc = (random.nextInt(100) + 1);
    if (crc <= cr) {
      return true;
    } else {
      return false;
    }
  }

  int xQ() {
    qCd += 3;
    cdReduce(XType.Q);
    if (qCd > 0) {
      return 0;
    } else {
      return 100;
    }
  }

  int xW() {
    qCd += 4;
    cdReduce(XType.W);
    return 120;
  }

  int xE() {
    qCd += 5;
    cdReduce(XType.E);
    return 130;
  }

  int xR() {
    qCd += 8;
    cdReduce(XType.R);
    return 200;
  }

  void cdReduce(XType type) {
    switch (type) {
    case Q:
      wCd -= 1;
      eCd -= 1;
      rCd -= 1;
      break;
    case W:
      qCd -= 1;
      eCd -= 1;
      rCd -= 1;
      break;
    case E:
      qCd -= 1;
      wCd -= 1;
      rCd -= 1;
      break;
    case R:
      qCd -= 1;
      wCd -= 1;
      eCd -= 1;
      break;
    case A:
      qCd -= 1;
      wCd -= 1;
      eCd -= 1;
      rCd -= 1;
      break;

    default:
      break;
    }
    if (qCd < 0) {
      qCd = 0;
    }
    if (wCd < 0) {
      wCd = 0;
    }
    if (eCd < 0) {
      eCd = 0;
    }
    if (rCd < 0) {
      rCd = 0;
    }
  }

  int xA() {
    if (isCC()) {
      return ad * 2;
    } else {
      return ad;
    }
  }
}

enum XType {
  Q, W, E, R, A,
}