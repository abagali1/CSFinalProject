package tetris.tetris.anupb.driver;

import tetris.anupb.panels.BigPanel;
import javax.swing.JFrame;

public class TetDriver
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Tetris");
        frame.setSize(400, 400);
        frame.setLocation(100, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BigPanel());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
