package anupb.input;

import anupb.resources.Block;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class KeyInput implements KeyListener {
    private Block b;

    public KeyInput(Block b){
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
        if(e.getKeyCode() == KeyEvent.VK_A && b.getX()>0){
            b.setLAcc(true);
            System.out.println("L");
        }
        if(e.getKeyCode() == KeyEvent.VK_D && b.getX()+b.getWidth()<200 ){
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
