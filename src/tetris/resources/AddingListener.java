package tetris.resources;

import java.awt.event.ActionListener;

/**
 * Interface which extends the <code>ActionListener</code> interface adds compatibility for adding Blocks to ActionListeners
 * @see ActionListener
 * @see Block
 * @see BlockTimer
 * @author Anup Bagali
 * @author Teja Kocherla
 * @author Kevin Liu
 * @author Amit Rajesh
 */
public interface AddingListener extends ActionListener {

    /**
     * Adds block to constant block queue
     * @param b Block to be added
     */
    public abstract void add(Block b);

}
