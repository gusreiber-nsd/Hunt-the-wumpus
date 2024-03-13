package org.bothell.cs.wumpus;

import java.util.ArrayList;
import java.util.Arrays;

public class Dungeon {
  private int          SHAPE  = 6;
  private int          WIDTH  = 6;
  private int          HEIGHT = 6;
  private int          mt     = WIDTH * HEIGHT;
  private Location[][] LOCS   = new Location[HEIGHT][WIDTH];


  private ArrayList<Hazard> hazards;

  public Dungeon(){

    for(int i = 0; i < WIDTH * HEIGHT; i++)
      LOCS[ i/HEIGHT ][ i%WIDTH ] = null;  //new Location(this, i/HEIGHT , i%WIDTH);
    
      System.out.println("DUNGEON!");
  }

  public void addLocations(Location p){
    p.buildRndWalls(-1);
    int[][] cords = getAdjacentCords(p.getX(),p.getY());
    for(int i = 0; i < SHAPE; i++) LOCS[ cords[i][0] ][ cords[i][1] ] = new Location(this,0,0);
  }

  public void addLocation(Location p, int[] cords){
    p.setX(cords[0]);
    p.setY(cords[1]);
    if(LOCS[p.getY()][p.getX()] != null){
      this.mt--;
      this.LOCS[p.getY()][p.getX()] = p; 
    }
    else
      for(int i = 0; i< SHAPE; i++)
        if(p.getWalls()[i] != null) LOCS[ cords[1] ][ cords[0] ].addWall(i);
      
     
    
  }
  
  public Location getAdjacent(Location p, int i){
    return getAdjacent(p)[i];
  }

  public Location[] getAdjacent(int[][] cords){
    Location[] adj = new Location[SHAPE];
    for(int i = 0; i < SHAPE; i++)
      adj[i] = LOCS[ cords[i][0] ][ cords[i][1] ];
    
    return adj;
  }

  public Location[] getAdjacent(Location p){
    return getAdjacent(p.getX(), p.getY() );
  }

  public Location[] getAdjacent(int x, int y){
    int[][] cords = getAdjacentCords(x,y);
    return getAdjacent(cords);
  }
  
  public int[][] getAdjacentCords(int x, int y){
    int[][] cords = new int[SHAPE][2];
    if(SHAPE == 6){  
      cords[0] = new int[]{ prev(y), x      };
      cords[1] = new int[]{ prev(y), next(x)};
      cords[2] = new int[]{ next(y), next(x)};
      cords[3] = new int[]{ next(y), x      };
      cords[4] = new int[]{ prev(y), prev(x)};
      cords[5] = new int[]{ prev(y), prev(x)};  
    }

    return cords;
  }
  
  public int match(int i ){
    return (i+SHAPE/2) % SHAPE-1;
  }

  private int next(int i){
    return (i<SHAPE-2)? i+1    : 0;
  }

  private int prev(int i){
    return (i<1)     ? SHAPE-1 : i-1;
  }

  public ArrayList<Hazard> getHazards(){
    return this.hazards;
  }

  public void setHazards(ArrayList<Hazard> h){
    this.hazards = h;
  }

  public int getShape(){
    return this.SHAPE;
  }

}
