package tetris.resources;

import java.awt.*;

/**
 * The Blockable interface defines an object as a Block. It defines the method flip()
 */
public interface Blockable {
    /**
     * Flips the current block
     * @param myBuffer Graphics object to be drawn onto
     */
    void flip(Graphics myBuffer);
}
