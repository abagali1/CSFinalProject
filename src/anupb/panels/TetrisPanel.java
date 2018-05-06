package anupb.panels;

import anupb.input.KeyInput;
import anupb.resources.AddingListener;
import anupb.resources.Audio;
import anupb.resources.Block;
import anupb.resources.BlockTimer;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;

/**
 * The TetrisPanel facilitates the actual Tetris game
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @see JPanel
 */
public class TetrisPanel extends JPanel{
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private Timer t;
    private BlockTimer k;
    private ArrayList<Block> blocks;
    private boolean[][] gameboard;
    private Audio song;
    private int blockCount = 0;
    private Block temp;
    private Keeper keep;
    private int[] yPos;

    /**
     * Creates a new TetrisPanel
     */
    public TetrisPanel() {
        this.myImage = new BufferedImage(201, 401, BufferedImage.TYPE_INT_RGB);
        this.myBuffer = (Graphics2D) myImage.getGraphics();
        blocks = new ArrayList<>();

        this.t = new Timer(75, new Listener());

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

        this.setFocusable(true);
        requestFocus();

        this.t.start();
        //asfd;ajsdf
    }

    /**
     * Sets the current <code>KeyListener</code> new a new <code>KeyListener</code> to adjust to a new Block
     * @param b Block to be changed to
     * @see KeyInput
     * @see java.awt.event.KeyListener
     * @see Block
     */
    public void setKeyListener(Block b) {
        this.addKeyListener(new KeyInput(b));
    }

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
            temp = new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                    Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7)));

            blocks.add(temp);
            setKeyListener(temp);

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
            Block.rain(blocks.get(blockCount), myBuffer);

            if (blocks.get(blockCount).getY() == ((blocks.get(blockCount).getType() != 1) ? 380 : 390))
                blockCount++;

            if(blocks.get(blockCount).getY() == 400)
                k.start(keep,blocks.get(blockCount));

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

        }
    }


}

