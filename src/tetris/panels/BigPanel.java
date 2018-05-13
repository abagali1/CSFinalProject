package tetris.panels;

import tetris.resources.Block;

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

    /**
     * Facilitates the transition from introduction panels to game panels
     */
    private JButton begin;
    /**
     * Displays the title logo
     */
    private JLabel title;
    /**
     * JPanel for the actual tetris game
     */
    private TetrisPanel tetris;
    /**
     * JPanel which shows the highscore, current score, and provides functionality for saving and loading games from
     * .txt file
     */
    private ScorePanel score;
    /**
     * JPanel which shows the upcoming blocks in the game
     */
    private BlockPanel block;
    /**
     * Formatting JPanel
     */
    private JPanel center;
    /**
     * Decoration JPanels
     */
    private leftPanel left, right;
    /**
     * Calls the start() method which changes the introduction over to the game
     */
    private Timer t;
    /**
     * Called every 5 milliseconds to check if the next 5 blocks have changed
     */
    private Timer s;
    /**
     * Stores the next 5 blocks
     */
    private Block[] nextBlocks;

    private JFrame myFrame;

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


//        Audio song = new Audio();    left.setBackground(Color.BLACK);
//        right.setBackground(Color.BLACK);
//
//        t = new Timer(5, new Listener());
//        setFocusable(true);

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
        myFrame = new JFrame("Tetris");
        myFrame.setSize(400,400);
        myFrame.setLocation(100,50);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setUndecorated(true);
        tetris = new TetrisPanel();
        myFrame.setContentPane(tetris);
        tetris.requestFocus();
        myFrame.setVisible(true);
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
     * Checks if the next 5 blocks have changed then updates accordingly`
     */
    public void check(){
        if(!(java.util.Arrays.equals(nextBlocks,tetris.getNext5Blocks()))){
            block.updateNextBlocks(tetris.getNext5Blocks());
        }
    }

}
