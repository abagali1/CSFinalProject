package anupb.panels;

import anupb.resources.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The BigPanel encapsulates the <code>TetrisPanel</code>, <code>ScorePanel</code>, and <code>BlockPanel</code>
 * @see TetrisPanel
 * @see ScorePanel
 * @see BlockPanel
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @see JPanel
 */
public class BigPanel extends JPanel {

    private JButton begin;
    private JLabel title;
    private TetrisPanel tetris;
    private ScorePanel score;
    private BlockPanel block;
    private JPanel center;
    private leftPanel left, right;
    private Timer t, s;
    private Block[] nextBlocks;

    /**
     * Creates a new BigPanel
     */
    public BigPanel(){
        setLayout(new GridLayout(1,3));

        left = new leftPanel();
        this.add(left);

         center = new JPanel();
        this.add(center);

         right = new leftPanel();
        this.add(right);

        center.setLayout(new BorderLayout());
        begin = new JButton("Begin Game");
        begin.addActionListener(new Starter());
        center.add(begin, BorderLayout.SOUTH);

        left.setBackground(Color.BLACK);
        right.setBackground(Color.BLACK);

        t = new Timer(5, new Listener());
//        Audio song = new Audio();

    }

    /**
     * Nested <code>ActionListener</code> class. Switches from home panel to Tetris game
      */
    private class Starter implements ActionListener{


        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            start();
        }
    }

    /**
     * Facilitates the transition from the home panel to the Tetris game
     */
    public void start(){
        setLayout(new GridLayout(1,3));
        this.remove(left);
        this.remove(right);
        this.remove(center);
        score = new ScorePanel();
        this.add(score);
        tetris = new TetrisPanel();
        nextBlocks = tetris.getNext5Blocks();
        this.add(tetris);
        block = new BlockPanel(nextBlocks);
        this.add(block);
        repaint();
        revalidate();
    }

    private class Listener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            check();
        }
    }

    /**
     * Checks if the next 5 blocks have changed then updates accordingly
     */
    public void check(){
        if(!(java.util.Arrays.equals(nextBlocks,tetris.getNext5Blocks()))){
            block.updateNextBlocks(tetris.getNext5Blocks());
        }
    }

}
