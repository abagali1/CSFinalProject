package anupb.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BigPanel extends JPanel {

    private JButton begin;
    private JLabel title;
    private TetrisPanel tetris;
    private ScorePanel score;
    private BlockPanel block;
    private JPanel left,center,right;

    public BigPanel(){
        setLayout(new GridLayout(1,3));

         left = new JPanel();
        this.add(left);

         center = new JPanel();
        this.add(center);

         right = new JPanel();
        this.add(right);

        center.setLayout(new BorderLayout());
        begin = new JButton("Begin Game");
        begin.addActionListener(new Starter());
        center.add(begin, BorderLayout.SOUTH);

        left.setBackground(Color.BLACK);
        right.setBackground(Color.BLACK);

    }

    private class Starter implements ActionListener{
        public void actionPerformed(ActionEvent e){
            start();
        }
    }
    public void start(){
        setLayout(new GridLayout(1,3));
        this.remove(left);
        this.remove(right);
        this.remove(center);
        score = new ScorePanel();
        this.add(score);
        tetris = new TetrisPanel();
        this.add(tetris);
        block = new BlockPanel();
        this.add(block);
        repaint();
        revalidate();
    }
}
