package anupb.driver;

import anupb.panels.BigPanel;

import javax.swing.*;

public class TetDriver
{
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