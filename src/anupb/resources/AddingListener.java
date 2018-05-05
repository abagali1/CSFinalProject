package anupb.resources;

import java.awt.event.ActionListener;

public interface AddingListener extends ActionListener {

    /**
     * Adds block to constant block queue
     * @param b Block to be added
     */
    public abstract void add(Block b);

}
