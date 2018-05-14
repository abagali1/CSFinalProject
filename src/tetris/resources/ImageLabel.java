package tetris.resources;

import javax.swing.*;

/**
 * An <code>ImageLabel</code> is a <code>JLabel</code> meant specifically for displaying <code>ImageIcon</code> objects
 * @author Anup Bagali
 * @author Teja Kocherla
 * @author Kevin Liu
 * @author Amit Rajesh
 * @see JLabel
 * @see ImageIcon
 */
public abstract class ImageLabel extends JLabel {

    /**
     * Determines whether the ImageLabel is visible or not
     */
    private boolean isVisible;


    /**
     * Creates a new ImageLabel object
     * @param a The starting visibility
     */
    public ImageLabel(boolean a){
        super();
        isVisible = a;
    }
    /**
     * Determines whether the label is visible or not, then displayes the <code>ImageIcon</code> accordingly
     * @param i ImageIcon to be displayed
     */
    public abstract void showImage(ImageIcon i);

    /**
     * Returns the current visibility
     * @return The current visibility
     */
    public boolean getVisibility(){
        return isVisible;
    }
}
