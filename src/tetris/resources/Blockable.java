package tetris.resources;

import java.awt.*;

/**
 * The Blockable interface defines an object as a Block. It defines the method flip() and move(int x)
 */
public interface Blockable {
    /**
     * Flips the current block
     * @param myBuffer Graphics object to be drawn onto
     */
    void flip(Graphics myBuffer);
    /**
     * Moves the block to the left or right
     * @param x amount of spaces to be moved
     */
    void move(int x);

}
