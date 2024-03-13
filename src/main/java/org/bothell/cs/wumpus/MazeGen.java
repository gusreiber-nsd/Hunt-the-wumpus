package org.bothell.cs.wumpus;

import java.util.Arrays;

public class MazeGen {

  private Dungeon dungeon;

  public MazeGen(Dungeon dungeon){
    this.dungeon = dungeon;
    Location p = new Location(dungeon, 0, 0);

    System.out.println( Arrays.deepToString( dungeon.getAdjacentCords(0, 0)) );

    p.buildRndWalls(-1);

  }

}
