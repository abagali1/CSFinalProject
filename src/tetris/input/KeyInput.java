package tetris.input;

import tetris.resources.Block;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * KeyInput reads input from a keyboard and adjusts a Block respectively
 * @author Anup Bagali
 * @author Teja Kocherla
 * @author Kevin Liu
 * @author Amit Rajesh
 * @see Block
 */
public class KeyInput implements KeyListener {
    private Block b;

    /**
     * Creates a new KeyInput object
     * @param b Block to be adjusted
     */
    public KeyInput(Block b){
        this.b = b;
    }

    /**
     * Sets the current block to a new block
     * @see Block
     * @param b Block to be set to
     */
    public void setBlock(Block b){
            this.b = b;
        }
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A ){
            b.setLAcc(true);
            System.out.println("L");
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            b.setRAcc(true);
            System.out.println("R");
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            b.setLAcc(false);
            System.out.println("L");
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            b.setRAcc(false);
            System.out.println("R");
        }

    }
}
