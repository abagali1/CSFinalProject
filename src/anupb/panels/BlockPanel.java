package anupb.panels;

import anupb.resources.Block;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * A BlockPanel shows which Blocks are coming next in a Tetris game
 * @see TetrisPanel
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @see JPanel
 */
public class BlockPanel extends JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private Timer t;
    private static final Color BACKGROUND = Color.BLACK;
    private Block[] nextBlocks;
    private HashMap<String, ImageIcon> images;

    /**
     * Creates a new BlockPanel
     */
    public BlockPanel(Block[] nextBlocks){
        myImage = new BufferedImage(400,400,1);
        this.myBuffer = (Graphics2D)myImage.getGraphics();
        myBuffer.setBackground(BACKGROUND);
        t = new Timer(5, new Starter());
        this.nextBlocks = nextBlocks;
        images = new HashMap<>();
        images.put("cyan", new ImageIcon("anupb/images/cyan.png"));
        images.put("blue", new ImageIcon("anupb/images/blue.png"));
        //images.put("red", new ImageIcon("anupb/images/red.png"));

        t.start();

    }
    public void updateNextBlocks(Block[] arr){
        this.nextBlocks = arr;
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
     * Nexted <code>ActionListener</code> class which starts showing upcoming blocks
     */
    private class Starter implements ActionListener {


        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            myBuffer.setColor(Color.white);
            myBuffer.drawRect(50,50,250,250);
            myBuffer.setFont(new Font("Arial",Font.BOLD,35));
            myBuffer.drawString("Next Blocks:", 80,75);
            repaint();
            revalidate();
        }
    }
}
