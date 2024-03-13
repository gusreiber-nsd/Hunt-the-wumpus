package org.bothell.cs.wumpus;

public class Wall {
  private boolean joined;
  private int i;

  public Wall(int i){
    this.i = i;
  }
  public Wall(){

  }

  public void join(){
    this.joined = true;
  } 

  public boolean joined(){
    return this.joined;
  } 

  @Override
  public String toString(){
    return "==";
  }
}
