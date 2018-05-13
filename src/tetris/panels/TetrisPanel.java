package tetris.panels;

import tetris.resources.AddingListener;
import tetris.resources.Audio;
import tetris.resources.Block;
import tetris.resources.BlockTimer;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;

/**
 * The TetrisPanel facilitates the actual Tetris game
 * TetrisPanel shows the blocks falling down and provides functionality for moving the blocks left to right
 * TetrisPanel uses the <code>Block</code> class to show The Tetris blocks
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @see JPanel
 */
public class TetrisPanel extends JPanel{
    /**
     * BufferedImage to draw onto
     */
    private BufferedImage myImage;
    /**
     * <code>Graphics2D</code> object drawing onto <code>myImage</code>
     */
    private Graphics2D myBuffer;
    /**
     * Timer objects to control <code>ActionListeners</code>
     */
    private Timer t;
    /**
     * Timer object to control <code>ActionListener</code> Listener
     */
    private Timer l;
    /**
     * Timer to control a <code>Keeper</code> class which draws all the constant blocks
     */
    private BlockTimer k;
    /**
     * <code>ArrayLists</code> of blocks. blocks stores random blocks, kblocks stores constant blocks
     */
    private ArrayList<Block> blocks;
    /**
     * <code>ArrayList</code> of constant blocks. These blocks are in the finished state
     */
    private ArrayList<Block> kblocks;
    /**
     * Stores the full and empty spots in the game
     */
    private boolean[][] gameboard;
    /**
     * EXPERIMENTAL Audio object to play a song
     */
    private Audio song;
    /**
     * Stores the current index of blocks
     */
    private int blockCount = 0;
    /**
     * Temporary block object for <code>Rainfall</code>
     */
    private Block temp;
    /**
     * <code>AddingListener</code> class for drawing constant objects
     * @see AddingListener
     */
    private Keeper keep;
    /**
     * Stores all the possible y positions for a new block
     */
    private int[] yPos;

    private ScorePanel scorePanel;

    private BlockPanel blockPanel;

    private TetrisPanel tetrisPanel;
    /**
     * Creates a new TetrisPanel
     * Initializes a new BufferedImage, and Graphics2D object
     * Initializes all the <code>ArrayLists</code> and <code>Arrays</code> with random blocks and specific values
     * respectively
     */
    public TetrisPanel() {
        setLayout(new GridLayout(1,3));

        this.myImage = new BufferedImage(200, 400, BufferedImage.TYPE_INT_RGB);
        this.myBuffer = (Graphics2D) myImage.getGraphics();
        blocks = new ArrayList<>();
        kblocks = new ArrayList<>();

        this.t = new Timer(750, new Listener());

        this.keep = new Keeper();
        this.k = new BlockTimer(1, keep);

        gameboard = new boolean[20][40];
        for(int r=0;r<=gameboard.length-1;r++){
            for(int c=0;c<=gameboard[0].length-1;c++){
                gameboard[r][c] = false;
            }
        }

        yPos = new int[19];
        for(int r=0;r<=200-30;r+=10)
            yPos[r/10] = r;


        for(int i=0;i<=Integer.MAX_VALUE/1000;i++)
            blocks.add(new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                    Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7))));


        scorePanel = new ScorePanel();
        this.add(scorePanel);


        blockPanel = new BlockPanel(getNext5Blocks());
        this.add(blockPanel);


        this.t.start();

        addKeyListener(new Key());
        requestFocus();
        setFocusable(true);

    }

    private class Key extends KeyAdapter {
        public Key(){
            System.out.println("new key");
        }
        /**
         * Invoked when a key has been pressed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key pressed event.
         *
         * @param ek the event to be processed
         */
        @Override
        public void keyPressed(KeyEvent ek) {
            if(ek.getKeyCode() == KeyEvent.VK_LEFT ){
                blocks.get(blockCount).setLAcc(true);
                System.out.println("L");
            }
            if(ek.getKeyCode() == KeyEvent.VK_RIGHT){
                blocks.get(blockCount).setRAcc(true);
                System.out.println("R");
            }
        }

        /**
         * Invoked when a key has been released.
         * See the class description for {@link KeyEvent} for a definition of
         * a key released event.
         *
         * @param ekk the event to be processed
         */
        @Override
        public void keyReleased(KeyEvent ekk) {
            if(ekk.getKeyCode() == KeyEvent.VK_LEFT ){
                blocks.get(blockCount).setLAcc(false);
                System.out.println("L");
            }
            if(ekk.getKeyCode() == KeyEvent.VK_RIGHT){
                blocks.get(blockCount).setRAcc(false);
                System.out.println("R");
            }
        }
    }


    /*/**
     * Sets the current <code>KeyListener</code> new a new <code>KeyListener</code> to adjust to a new Block
     * @param b Block to be changed to
     * @see KeyInput
     * @see java.awt.event.KeyListener
     * @see Block
     */
  /* public void setKeyListener(Block b) {
       key.setBlock(b);
   }*/

    @Override
    /**
     * Calls the UI delegate's paint method, if the UI delegate
     * is non-<code>null</code>.  We pass the delegate a copy of the
     * <code>Graphics</code> object to protect the rest of the
     * paint code from irrevocable changes
     * (for example, <code>Graphics.translate</code>).
     * <p>
     * If you override this in a subclass you should not make permanent
     * changes to the passed in <code>Graphics</code>. For example, you
     * should not alter the clip <code>Rectangle</code> or modify the
     * transform. If you need to do these operations you may find it
     * easier to create a new <code>Graphics</code> from the passed in
     * <code>Graphics</code> and manipulate it. Further, if you do not
     * invoke super's implementation you must honor the opaque property, that is
     * if this component is opaque, you must completely fill in the background
     * in an opaque color. If you do not honor the opaque property you
     * will likely see visual artifacts.
     * <p>
     * The passed in <code>Graphics</code> object might
     * have a transform other than the identify transform
     * installed on it.  In this case, you might get
     * unexpected results if you cumulatively apply
     * another transform.
     *
     * @param g the <code>Graphics</code> object to protect
     * @see #paint
     * @see ComponentUI
     */
    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Neseted <code>ActionListener</code> class to actually run the Tetris game
     */
    private class Listener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            myBuffer.setColor(Color.black);
            myBuffer.fillRect(0, 0, getWidth(), getHeight());
            myBuffer.setColor(Color.WHITE);

            for (int i = 0; i <= 400; i += 10) {
                myBuffer.drawLine(i, 0, i, 400);
                myBuffer.drawLine(0, i, 401, i);
            }

            myBuffer.drawLine(400, 0, 400, 400);
            myBuffer.drawLine(0, 400, 400, 400);


            blocks.get(blockCount).draw(myBuffer);
            blocks.get(blockCount).move(10);


            Block.setFall(true);
            Block.rain(blocks, myBuffer);

            //setKeyListener(blocks.get(blockCount));

            System.out.println(blocks.get(blockCount).toString() + "fasdfa:" + blockCount+"\t" + getHeight());

            for(int i = Block.count-1; i>=blocks.size();i--) {
                blocks.get(i).draw(myBuffer);
            }

            if(blocks.get(blockCount).getY() == ((blocks.get(blockCount).getType()!=1)?(380):(390)))
                blockCount++;


            repaint();
            revalidate();
        }
    }

    /**
     * Sets a block into the finished state
     * @param b Block to be set
     */
    public void finished(Block b){
        ArrayList<Rectangle2D.Double> temp = b.convert();
        for(Rectangle2D.Double d: temp){
            myBuffer.fill(d);
        }
    }

    /**
     * Nested <code>ActionListener</code> class to keep finished blocks set in the gameboard
     */
    private class Keeper implements AddingListener{
        private ArrayList<Block> constantBlocks = new ArrayList<>();

        /**
         * Creates a new Keeper
         */
        public Keeper() {}
        /**
         * Adds block to constant block queue
         *
         * @param b Block to be added
         */
        @Override
        public void add(Block b) {
            constantBlocks.add(b);
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            for(Block b: constantBlocks){
                b.draw(myBuffer);
            }
        }
    }

    /**
     * Gets the next 5 blocks after the <code>blockCount</code> index in the <code>blocks</code> ArrayList
     * @return Array of the next 5 blocks
     */
    public Block[] getNext5Blocks(){
        Block[] temp = new Block[5];
        for(int i =0;i<=temp.length-1;i++){
            temp[i] = blocks.get(blockCount + i + 1);
        }
        return temp;
    }
}