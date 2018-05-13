package tetris.driver;

import tetris.panels.BigPanel;
import tetris.panels.TetrisPanel;

import javax.swing.*;

/**
 * TetDriver runs a Tetris game
 * @author Anup Bagali
 * @author Teja Kocherla
 * @author Kevin Liu
 * @author Amit Rajesh
 */
public class TetDriver
{
   /**
    * Main method which creates the <code>JFrame</code> which shows the Tetris game
    * @see BigPanel
    * @see JFrame
    * @param args Unused
    */
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Tetris");
      frame.setSize(400, 400);
      frame.setLocation(100, 50);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      TetrisPanel b = new TetrisPanel();
      frame.setContentPane(b);
      b.requestFocus();
      frame.setVisible(true);
   }
}