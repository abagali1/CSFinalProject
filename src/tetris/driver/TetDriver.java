package tetris.driver;

import tetris.panels.BigPanel;

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
      frame.setLocation(400, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setUndecorated(true);
      BigPanel b = new BigPanel(frame);
      frame.setContentPane(b);
      b.requestFocus();
      frame.setVisible(true);
      //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
   }
}