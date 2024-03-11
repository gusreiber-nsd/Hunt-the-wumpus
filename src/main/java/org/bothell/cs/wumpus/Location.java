package org.bothell.cs.wumpus;

import java.util.ArrayList;
import java.util.Random;

public class Location {
  private boolean[] walls;   
  
  private int               x;
  private int               y;
  private Dungeon           dungeon;
  private ArrayList<Hazard> hazards; 
  private Player            player;

  ////////////////////////////////////
  // Constructors
  ////////////////////////////////////
  public Location(Dungeon dungeon, int x, int y){
    this.dungeon = dungeon;
    this.x       = x;
    this.y       = y;
    this.walls   = new boolean[dungeon.getShape()];
  }

  ////////////////////////////////////

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

  ////////////////////////////////////
  // Basic Methods
  ////////////////////////////////////

  public Dungeon getDungeon(){
    return this.dungeon;
  }
  public void setDungeon(Dungeon dungeon){
    this.dungeon = dungeon;
  }

  public int getX() {
    return x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return y;
  }
  public void setY(int y) {
    this.y = y;
  }
  public void addHazard(Hazard h){
    this.hazards.add(h);
  }
  public void removeHazard(int i){
    this.hazards.remove(i);
  }
  public void removeHazard(Hazard h){
    this.hazards.remove(h);
  }
  public Hazard getHazard(int i){
    return this.hazards.get(i);
  }
  public ArrayList<Hazard> getHazards() {
    return hazards;
  }
  public void setHazards(ArrayList<Hazard> hazards) {
    this.hazards = hazards;
  }
  public Player getPlayer() {
    return player;
  }
  public void setPlayer(Player player) {
    this.player = player;
  }

}
