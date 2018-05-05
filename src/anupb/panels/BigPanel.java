package anupb.panels;

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
    private JPanel left,center,right;

    /**
     * Creates a new BigPanel
     */
    public BigPanel(){
        setLayout(new GridLayout(1,3));

        left = new JPanel();
        left.setLayout(new GridLayout(1,1));
        JLabel ic = new JLabel();
        ic.setIcon(new ImageIcon("C:\\Users\\anupb\\Desktop\\CSProject\\src\\anupb\\images\\giphy.gif"));
        left.add(ic);
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
        this.add(tetris);
        block = new BlockPanel();
        this.add(block);
        repaint();
        revalidate();
    }
}
