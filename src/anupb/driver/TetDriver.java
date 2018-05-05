package anupb.driver;

import anupb.panels.BigPanel;

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
        BigPanel b = new BigPanel();
        frame.setContentPane(b);
        b.requestFocus();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}