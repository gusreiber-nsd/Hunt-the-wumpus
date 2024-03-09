package org.bothell.cs.wumpus;

import java.util.Arrays;
import java.util.Random;

public class Map{
  public static final int SIZE  = 6;
  public static final int CELL  = 6;
  public static final int WALLS = 3;

  private Cave[][] grid;
  private Painter p;
  private int crawlCount;

  public Map(){
    this.crawlCount = 0;
    this.grid = new Cave[SIZE][SIZE];
    for(int y = 0; y < grid.length; y++){
      for(int x = 0; x < grid[y].length; x++)
        grid[y][x] = new Cave(this,x,y, new Hazard() );
    }
    this.p = new Painter(this);    
  }

  public void draw(){
    p.draw();
  }

  public Cave[][] getGrid(){
    return this.grid;
  }

  private Cave[] getAdjacent(Cave c){
    return getAdjacent(c.getCords()[0], c.getCords()[1]);
  }

  private Cave[] getAdjacent(int x, int y){
    Cave[] sides = new Cave[CELL];
    sides[4] = grid[ y ][ prev(x) ];
    sides[1] = grid[ y ][ next(x) ];
    sides[5] = grid[ prev(y) ][ (y%2==0)? prev(x):x ];
    sides[0] = grid[ prev(y) ][ (y%2==1)? next(x):x ];
    sides[3] = grid[ next(y) ][ (y%2==0)? prev(x):x ];
    sides[2] = grid[ next(y) ][ (y%2==1)? next(x):x ];

    return sides;
  }

  private int next(int i){
    return (i<SIZE-1)? i+1    : 0;
  }

  private int prev(int i){
    return (i<1)     ? SIZE-1 : i-1;
  }

  private void crawl(Cave start){
    crawlCount++;
    start.pickWalls(0);
    boolean[] walls = start.getWalls();
    Cave[] nextCaves = getAdjacent(start);
    int sides = walls.length;

    for(int w=0; w < sides; w++)
      for(int c=0; c < sides; c++){
        if(walls[w]) 
          nextCaves[c].addWall((w+sides/2) % sides);
      }

    int[] paths = start.getExits();
    Cave[] connects = new Cave[paths.length];
    for(int i=0; i < paths.length; i++) 
      connects[i] = nextCaves[paths[i]];

    /*
      x    y   ==> (x+3)%6
      0 => 3
      1 => 4
      2 => 5
      3 => 0
      4 => 1
      5 => 2
    */
  }


}