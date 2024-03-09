package org.bothell.cs.wumpus;

public class Painter{

  public static final String   OFF  = "    ";
  public static final String[] CELL = {
    " /\"\"\"  \\",
    "||     |",
    "|\\__%s__/"
  };

  private Map map;

  public Painter(Map map){
    this.map = map;
  }

  public void draw(){

    for(int y = 0; y < map.SIZE; y++){
      row(map.getGrid()[y], y); 
    }
    row(map.getGrid()[0], -1);
  }

  private void row(Cave[] row, int count){
    int p = (count < 0)? 1 : CELL.length; 
    for(int i = 0; i < p; i++){ 
      if(count%2 == 1) System.out.print(OFF);
      for(Cave c:row) cell(c,i);
      System.out.println("|");
    }

  }

  private void cell(Cave cave, int part){
    System.out.printf(
      CELL[part], cave.getContent()
    );
  }
}