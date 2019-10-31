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
    this.mp = 200;
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

  void itemUse(int index) {
    Item item = inventory.get(index);
    inventory.remove(index);

    hp += item.hp;
    mp += item.mp;
    ad += item.ad;
    ap += item.ap;
    ar += item.ar;
    mr += item.mr;
    cr += item.cr;
    System.out.println("Item used " + item.name + ". ");
  }

  void levelCheck(int xpAdd) {
    xp += xpAdd;
    if (xp >= (level * 1.5)) {
      level += 1;
      xp -= (level * 1.5);
      levelUp();
    }

  }

  void levelUp() {
    hp *= 1.4;
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
    int mpCost = (int) (10 * level * 0.5);
    if (qCd > 0) {
      return 0;
    } else if (mp <= mpCost) {
      return -1;
    } else {
      cdReduce(XType.Q);
      qCd += 3;
      mp -= mpCost;
      return ad + (random.nextInt(100) + 1);
    }

  }

  int xW() {
    int mpCost = (int) (10 * level * 0.5);
    if (wCd > 0) {
      return 0;
    } else if (mp <= mpCost) {
      return -1;
    } else {
      cdReduce(XType.W);
      wCd += 4;
      mp -= mpCost;
      return ap + (random.nextInt(100) + 1);
    }
  }

  int xE() {
    int mpCost = (int) (20 * level * 0.5);

    if (eCd > 0) {
      return 0;
    } else if (mp <= mpCost) {
      return -1;
    } else {
      cdReduce(XType.E);
      eCd += 5;
      mp -= mpCost;
      return ad + (random.nextInt(100) + 1);

    }
  }

  int xR() {
    int mpCost = (int) (30 * level * 0.5);

    if (rCd > 0) {
      return 0;
    } else if (mp <= mpCost) {
      return -1;
    } else {
      cdReduce(XType.R);
      rCd += 8;
      mp -= mpCost;
      return ap * 2 + (random.nextInt(100) + 1);
    }
  }

  int xA() {
    mp += 10;
    cdReduce(XType.A);
    if (isCC()) {
      return ad * 2;
    } else {
      return ad;
    }
  }

  void cdReduce(XType type) {
    mp += 10;
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

}

enum XType {
  Q, W, E, R, A,
}