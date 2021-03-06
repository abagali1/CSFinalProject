import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
     * Stores the next 5 blocks
     */
    private Block[] nextBlocks;
    /**
     * JFrame to be displayed onto
     */
    private JFrame myFrame;

    /**
     * Creates a new BigPanel
     * @param a JFrame to be displayed onto
     * @see JFrame
     */
    public BigPanel(JFrame a) {
        setLayout(new GridLayout(1, 3));

        left = new leftPanel();
        this.add(left);

        center = new JPanel();
        this.add(center);

        right = new leftPanel();
        this.add(right);

        center.setLayout(new BorderLayout());

        JButton begin = new JButton("Begin Game");
        begin.addActionListener(
                e -> start()
        );
        center.add(begin, BorderLayout.SOUTH);


        JButton instructions = new JButton("Instructions");
        instructions.addActionListener(
                e -> left.showInstructions()
        );
        instructions.setSize(new Dimension(100, 100));
        center.add(instructions, BorderLayout.WEST);


        JButton credits = new JButton("Credits     ");
        credits.addActionListener(
                e -> right.rollCredits()
        );
        credits.setSize(new Dimension(100, 100));
        center.add(credits, BorderLayout.EAST);

        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("logo-game.png"));

            JLabel logo = new JLabel(new ImageIcon(image));
            center.add(logo);

        } catch (IOException ignored) {
        }

        t = new Timer(3, e ->
                check()
        );

        myFrame = a;


    }

    /**
     * Facilitates the transition from the home panel to the Tetris game
     */
    public void start(){
        left.stop();
        right.stop();
        setLayout(new GridLayout(1,3));

        this.remove(left);
        this.remove(center);
        this.remove(right);


        score = new ScorePanel();
        this.add(score);

        tetris = new TetrisPanel();
        this.add(tetris);

        try{
            block = new BlockPanel(tetris.getNext5Blocks());
        }catch (Exception e){
            new Exception().addSuppressed(e);
        }
        this.add(block);

        t.start();


        myFrame.setContentPane(this);
        tetris.requestFocus();
    }

    /**
     * Checks if the next 5 blocks have changed then updates accordingly`
     */
    public void check(){
        if(!(java.util.Arrays.equals(nextBlocks,tetris.getNext5Blocks())))
            block.updateNextBlocks(tetris.getNext5Blocks());

        if(score.isClicked()) {
            tetris.requestFocus();
            score.setClicked(false);
        }

        if(score.getCScore() != tetris.getCScore())
            score.update(tetris.getCScore());



    }


}