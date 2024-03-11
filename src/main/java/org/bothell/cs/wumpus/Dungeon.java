package org.bothell.cs.wumpus;

import java.util.ArrayList;

public class Dungeon {
  private int          SHAPE  = 6;
  private int          WIDTH  = 6;
  private int          HEIGHT = 6;
  private Location[][] LOCS   = new Location[HEIGHT][WIDTH];

  private ArrayList<Hazard> hazards;

  public Dungeon(){

  }

  public Location[] getAdjacent(Location l){
    return getAdjacent(l.getX(), l.getY());
  }
  
  public Location[] getAdjacent(int x, int y){
    Location[] adj = new Location[SHAPE];
    if(SHAPE == 4){
      adj[0] = LOCS[ prev(y) ][ x       ];
      adj[1] = LOCS[ y       ][ next(x) ];
      adj[2] = LOCS[ next(y) ][ x       ];
      adj[3] = LOCS[ y       ][ prev(x) ];  
    }
    if(SHAPE == 6){
      adj[0] = LOCS[ prev(y) ][ x       ];
      adj[1] = LOCS[ prev(y) ][ next(x) ];
      adj[2] = LOCS[ next(y) ][ next(x) ];
      adj[3] = LOCS[ next(y) ][ x       ];
      adj[4] = LOCS[ next(y) ][ prev(x) ];
      adj[5] = LOCS[ prev(y) ][ prev(x) ];
    }
    if(SHAPE == 8){
      adj[0] = LOCS[ prev(y) ][ x       ];
      adj[1] = LOCS[ prev(y) ][ next(x) ];
      adj[2] = LOCS[ y       ][ next(x) ];
      adj[3] = LOCS[ next(y) ][ next(x) ];
      adj[4] = LOCS[ next(y) ][ x       ];
      adj[5] = LOCS[ next(y) ][ prev(x) ];
      adj[6] = LOCS[ y       ][ prev(x) ];
      adj[7] = LOCS[ prev(y) ][ prev(x) ];
    }

    return adj;
  }

  private int next(int i){
    return (i<SIZE-1)? i+1    : 0;
  }

  private int prev(int i){
    return (i<1)     ? SIZE-1 : i-1;
  }

  public ArrayList<Hazard> getHazards(){
    return this.hazards;
  }

  public void setHazards(ArrayList<Hazard> h){
    this.hazards = h;
  }

}
