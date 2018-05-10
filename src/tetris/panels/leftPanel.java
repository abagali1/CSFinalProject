package tetris.panels;

import tetris.resources.Block;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;

/**
 * A leftPanel is a panel used only for aesthetic purposes. A leftPanel holds information about its Graphical
 * properties, along with 3 timers, an <code>ArrayList</code> of <code>Blocks</code>, and an array of possible y-positions
 *
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @see Block
 *
 */
public class leftPanel extends javax.swing.JPanel {
    /**
     * BufferedImage to draw onto
     */
    private BufferedImage myImage;
    /**
     * Graphics2D object which draws onto myImage
     */
    private Graphics2D myBuffer;
    /**
     * Timers to control decorations
     */
    private Timer t, t1, t2;
    /**
     * Large <code>ArrayList</code> of blocks. Used in decorations
     */
    private ArrayList<Block> blocks;
    /**
     * Used to index <code>blocks</code>
     */
    private int count = 0;
    /**
     * Used to rainfall blocks
     */
    private Block temp;
    /**
     * Stores all possible y positions for new blocks
     */
    private int[] yPos = new int[] {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 0};

    /**
     * Creates a new leftPanel object
     */
    public leftPanel(){
        myImage = new BufferedImage(200,401, BufferedImage.TYPE_INT_RGB);
        myBuffer = (Graphics2D) myImage.getGraphics();

        blocks = new ArrayList<>();
        blocks.add(new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7))));

        t = new Timer(5, new Listener());
        t1 = new Timer(5, new Listener1());
        t2 = new Timer(5, new Listener2());
        t.start();
        t1.start();
        t2.start();
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
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Shows the decorations onto the panel
     */
    public void decorate(){
        temp = new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7)));

        blocks.add(temp);

        myBuffer.setColor(Color.black);
        myBuffer.fillRect(0, 0, getWidth(), getHeight());
        myBuffer.setColor(Color.WHITE);

        blocks.get(count).draw(myBuffer);
        blocks.get(count).move(10);

        Block.setFall(true);
        Block.prettyRain(blocks, myBuffer);

        if (blocks.get(count).getY() == ((blocks.get(count).getType() != 1) ? 380 : 390))
            count++;

        repaint();
        revalidate();
    }

    /**
     * A nested class for decorations
     * @see ActionListener
     */
    private class Listener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            decorate();
        }
    }

    /**
     * A nested class for more decorations
     * @see ActionListener
     */
    private class Listener1 implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            decorate();
        }
    }

    /**
     * A nested for even more decorations
     * @see ActionListener
     */
    private class Listener2 implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            decorate();
        }
    }

    /**
     * Stops all timers
     */
    public void stop(){
        t.stop();
        t1.stop();
        t2.stop();
    }
}