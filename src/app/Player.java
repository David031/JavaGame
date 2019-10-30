package app;

import java.util.ArrayList;

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
  void levelCheck(int xp){}
  void xQ(){}
  void xW(){}
  void xE(){}
  void xR(){}

}