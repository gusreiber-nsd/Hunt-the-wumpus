package org.bothell.cs.wumpus;

public class Hazard{
  private boolean discovered;
  private char symbol;

  public Hazard(){
    this.discovered = false; 
    this.symbol = '@';
  }

  public char show(){
    return (discovered)? symbol : ' ';
  }

  public char discover(){
    this.discovered = true;
    return this.symbol;
  }
}