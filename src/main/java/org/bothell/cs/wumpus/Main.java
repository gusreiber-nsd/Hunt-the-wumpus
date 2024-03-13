package org.bothell.cs.wumpus;

public class Main{
  public static void main(String[] args){

    Dungeon d = new Dungeon();
    new MazeGen(d);

    new Controller();
    
    System.out.println("OH no!!!!!!!");
  }
}