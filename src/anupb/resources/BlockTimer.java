package anupb.resources;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A block timer is a type of <code>javax.swing.Timer</code> which allows Blocks to be added to a Listener
 * @author Anup Bagali
 * @author Teja Kocherla
 * @author Kevin Liu
 * @author Amit Rajesh
 */
public class BlockTimer extends Timer {
    /**
     * Creates a new <code>BlockTimer</code> object
     * @param delay Delay
     * @param a <code>ActionListener</code> to be called
     * @see ActionListener
     */
    public BlockTimer(int delay, ActionListener a){
        super(delay, a);
    }

    /**
     * Starts the <code>AddingListener</code> with a new Block object
     * @param a <code>AddingListener</code> to be called
     * @param b Block to be added
     * @see AddingListener
     * @see Block
     */
    public void start(AddingListener a, Block b){
        a.add(b);
        super.start();

    }
}
