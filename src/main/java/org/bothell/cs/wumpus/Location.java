package org.bothell.cs.wumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Location {
  private boolean[] exits;   
  
  private int               x;
  private int               y;
  private int               minWalls;
  private int               exit;
  private Dungeon           dungeon;
  private ArrayList<Hazard> hazards; 
  private Player            player;
  private Random            die;

  ////////////////////////////////////
  // Constructors
  ////////////////////////////////////

  public Location(Dungeon dungeon, int x, int y, int entrance){
    this.dungeon = dungeon;
    this.x       = x;
    this.y       = y;
    this.minWalls = 3;
    this.exits   = new boolean[dungeon.getShape()];
    this.die     = new Random();

    findPaths(entrance);

    System.out.println("LOCATION!");
  }

  public Location(Dungeon dungeon, int x, int y){
    this(dungeon, x, y, -1);
  }

  ////////////////////////////////////

  public void findPaths(int entrance){
    // entrance
    // randExit = this.die.nextInt(5) ;
    // exit     = (randExit < entrance)? randExit : randExit + 1 ;
    // also     = this.die.nextInt(6) ;
    this.exit = this.die.nextInt(5) ;

    int[] paths = new int[exits.length - minWalls];
   
    paths[0] = (entrance < 0)? exit : entrance;
    paths[1] = (exit <= paths[0] )? exit : exit + 1 ;
    paths[2] = this.die.nextInt(6) ;
    System.out.println("entrance: " + entrance);
    System.out.println("entrance: " + paths[0]);
    System.out.println("Exit: " + paths[1]);
    System.out.println("also: " + paths[2]);

    System.out.println(Arrays.toString(paths));
    for(int i:paths) this.exits[i] = true;

    System.out.println(Arrays.toString(this.exits));
  }

  public void pickWalls(int entrance){

    Random die = new Random();
    // build wall slots excluding the entrance possition
    // as required by the Map cells.
    int[] tmp = new int[exits.length];
    for(int i=0; i < exits.length; i++){
      if(entrance > i) tmp[i] = i;
      else{
        tmp[i] = exits.length - 1;
        entrance = exits.length - 1;
      }
    }
    // randomly draw from the remaining wall slots until there are
    // the number required by the Map.
    for(int i = exits.length - 1; i > minWalls; i--){
      int rnd = die.nextInt(i);
      int draw = tmp[rnd];
      int swap = tmp[i-1];
      exits[draw] = true;
      // clean-up by moving selected out of range
      tmp[rnd] = swap;
      tmp[i-1] = draw;
    }
    System.out.println(Arrays.toString(this.walls));
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
