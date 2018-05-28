package tetris.panels;

import tetris.resources.Block;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
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

        t = new Timer(75, e -> decorate());
        t1 = new Timer(75, e -> decorate());
        t2 = new Timer(75, e -> decorate());
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

        Block.setFall(true);
        Block.prettyRain(blocks, myBuffer);

        if (blocks.get(count).getY() == ((blocks.get(count).getType() != 1) ? 380 : 390))
            count++;

        repaint();
        revalidate();
    }

    /**
     * Stops all timers
     */
    public void stop(){
        t.stop();
        t1.stop();
        t2.stop();
        myBuffer.setColor(Color.BLACK);
        myBuffer.fillRect(0,0,myImage.getWidth(), myImage.getHeight());
        repaint();
        revalidate();


    }

    /**
     * Stops the decorations and displays the instructions on how to play tetris
     */
    public void showInstructions(){
        stop();
        String content = ("" +
                "<html>" +
                "<ol>" +
                "   <li>Press Begin Game button on starting screen</li>\n" +
                "   <li>Move blocks left, right using the arrow keys. The blocks move downward automatically\n" +
                "   <li>Moving the blocks left or right changes the horizontal placement of the block on the board\n" +
                "   <li>Moving the blocks down accelerates the process of placing a block on the board\n" +
                "   <li>Once a row is completely filled horizontally, the row will disappear\n" +
                "   <li>Press the spacebar to oriente the block in different ways\n" +
                "   <li>The objective of the game is to completely fill " +
                "   as many rows as possible, and take as long as possible to reach the top of the board</li>\n" +
                "   <li>Your score increases as you fill more rows</li>\n" +
                "   <li>Once the top is reached, your game is over</li>\n");
        myBuffer.setColor(Color.WHITE);
        myBuffer.setFont(new Font("Arial",Font.ITALIC,10));
        myBuffer.drawString("1.Press Begin Game to begin a game of ",0,30);
        myBuffer.drawString("tetris",0,50);
        myBuffer.drawString("2. Move blocks left and right using ", 0,70);
        myBuffer.drawString("arrow keys, blocks will move down", 0, 90);
        myBuffer.drawString("3.Press the up arrow to flip blocks", 0, 110);
        myBuffer.drawString("4.Find a way to manipulate the blocks ",0,130);
        myBuffer.drawString("to complete an entire row", 0, 150);
        myBuffer.drawString("5. Once a row is completed, 10 points", 0,170);
        myBuffer.drawString("are rewarded",0,190);
        myBuffer.drawString("6. Once the tower of blocks hits the ",0,210);
        myBuffer.drawString("of the screen, the game is over", 0, 230);
        ImageIcon pic = new ImageIcon("tetris/images/pic.gif");
        myBuffer.drawImage(pic.getImage(),20,250, 100,100,null);
        repaint();
        revalidate();
    }

    /**
     * Displays the credits for the construction of this game
     */
    public void rollCredits(){
        stop();

    }

}