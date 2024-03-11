package org.bothell.cs.wumpus;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

public class GUI extends JFrame{
  
  private Location[] loi;

  public GUI(){
    setTitle("Wumpus");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(640, 480);
    setLayout(new FlowLayout());

    add(new WB());
    
    setResizable(true);
    setLocationRelativeTo(null);
    setVisible(true);
  }

}

class WB extends JButton implements ActionListener{

  WB(){
    int width = 200;
    int height = 300;
    String pic = "/pirate.jpg";

    setPreferredSize(new Dimension(width, height));
    addActionListener( this );
    
    try {
      Image img = ImageIO.read(getClass().getResource(pic));
      Image scale = img.getScaledInstance( width, height, Image.SCALE_SMOOTH ) ;
      setIcon(new ImageIcon(scale));
    } catch (Exception ex) {
      System.out.println(ex);
    } 
  }

  @Override
  public void actionPerformed(ActionEvent e){
    System.out.println("-----------");
    System.out.println("vvvvvvv");
    System.out.println();
  }
}

