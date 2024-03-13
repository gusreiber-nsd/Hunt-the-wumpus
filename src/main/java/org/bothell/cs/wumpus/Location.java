package org.bothell.cs.wumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Location {
  private Wall[] walls;   
  
  private int               x;
  private int               y;
  private int               minWalls;
  private int               exit;
  private int               entrance;
  private Dungeon           dungeon;
  private ArrayList<Hazard> hazards; 
  private Player            player;
  private Random            die;

  ////////////////////////////////////
  // Constructors
  ////////////////////////////////////

  public Location(Dungeon dungeon, boolean[] walls, int x, int y, int entrance){
    this.dungeon = dungeon;
    this.x       = x % dungeon.getShape();
    this.y       = y % dungeon.getShape();
    this.minWalls = 3;
    this.walls   = new Wall[dungeon.getShape()];
    this.die     = new Random();

    for(int i = 0; i < walls.length; i++) if(walls[i]) this.walls[i] = new Wall(i); 
    //buildRndWalls(entrance);

    System.out.println("LOCATION! " + this);
  }
  
  public Location(Dungeon dungeon, int[] cords, int entrance){
    this(dungeon, new boolean[0], cords[0], cords[1], entrance);
  }
  
  public Location(Dungeon dungeon, int x, int y){
    this(dungeon, new boolean[0], x, y, -1);
  }

  public Location(Dungeon dungeon, int entrance){
    this(dungeon, new boolean[0], -1, -1, -1);
  }

  ////////////////////////////////////

  public void buildRndWalls(int entrance){

    this.exit = this.die.nextInt(5) ;

    int[] paths = new int[walls.length - minWalls];
   
    paths[0] = (entrance < 0)? exit : entrance;
    paths[1] = (exit <= paths[0] )? exit : exit + 1 ;
    paths[2] = this.die.nextInt(6) ;

    for(int i = 0; i < walls.length; i++) addWall(i);
    for(int i:paths) removeWall(i);

    System.out.println(Arrays.toString(this.walls));
  }

  public void pickWalls(int entrance){

    Random die = new Random();
    // build wall slots excluding the entrance possition
    // as required by the Map cells.
    int[] tmp = new int[walls.length];
    for(int i=0; i < walls.length; i++){
      if(entrance > i) tmp[i] = i;
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
      walls[draw] = new Wall(draw);
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

  public Wall[] getWalls(){
    return this.walls;
  }


  public void removeWall(int i){
    this.walls[i] = null;
    if(dungeon != null) return;
    
    Location p = this.dungeon.getAdjacent(this, i);
    if(p != null) p.removeWall( dungeon.match(i) );  
  }

  public void addWall(int i){
    this.walls[i] = new Wall(i);
    if(dungeon != null) return;

    Location p = this.dungeon.getAdjacent(this, i);
    if(p != null) p.addWall( dungeon.match(i) );
  }

  public Player getPlayer() {
    return player;
  }
  public void setPlayer(Player player) {
    this.player = player;
  }

  @Override
  public String toString(){
    return "["+ x + "," + y +"]";
  }
}
