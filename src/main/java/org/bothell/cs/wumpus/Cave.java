package org.bothell.cs.wumpus;

import java.util.Random;

public class Cave{

  private Hazard hazard;
  private Player player;
  private Map map;
  private int[] cords;
  private boolean[] walls;
  private int minWalls;

  public Cave(Map map){
     build(map, 0, 0);
  }

  public Cave(Map map, int x, int y, Hazard hazard){
    build(map, x, y);
    this.hazard = hazard;
  }

  private void build(Map map, int x, int y){
    this.map = map;
    this.cords = new int[]{x,y};
    this.walls = new boolean[map.CELL];
    this.minWalls = map.WALLS;
  }

  public void pickWalls(int entrance){

    Random die = new Random();
    // build wall slots excluding the entrance possition
    // as required by the Map cells.
    int[] tmp = new int[walls.length];
    for(int i=0; i < walls.length; i++){
      if(entrance > i) tmp[i] =i;
      else{
        tmp[i] = walls.length - 1;
        entrance = walls.length - 1;
      }
    }
    // randomly draw from the remaining wall slots until there are
    // the number required by the Map.
    for(int i = walls.length - 1; i > minWalls; i--){
      int rnd = die.nextInt(i);
      int draw = tmp[rnd];
      int swap = tmp[i-1];
      walls[draw] = true;
      // clean-up by moving selected out of range
      tmp[rnd] = swap;
      tmp[i-1] = draw;
    }
  }

  public int[] getExits(){
    int open = walls.length - wallCount();
    int[] exits = new int[open];
    for(int i = 0; i < walls.length; i++) 
      if(!walls[i]) exits[i] = i;

    return exits;
  }

  public int wallCount(){
    int count = 0;
    for(boolean w:walls) if(w) count++;
    return count;
  }

  public String getContent(){
    return "" + this.hazard.show();
  }

  public void cords(){
    System.out.printf("[ %n,%n] \n", cords[0],cords[1]);
  }

  public int[] getCords(){
    return this.cords;
  }

  public boolean[] getWalls(){
    return this.walls;
  }

  public void setWalls(boolean[] walls){
    this.walls = walls;
  }

  public void addWall(int pos){
    this.walls[pos] = true;
  }

  @Override
  public String toString(){
    return "[" + cords[0] + "," + cords[1] + "] ";
  }
}