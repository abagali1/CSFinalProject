package tetris.resources;

import javax.swing.*;
import java.awt.*;

/**
 * A <code>BlockLabel</code> is a unique <code>JLabel</code> which is meant specifically for displaying a
 * <code>Block</code> object, in the form of an <code>ImageIcon</code>. BlockLabel extends the <code>ImageLabel</code>
 * abstract class
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @version 1
 * @see JLabel
 * @see ImageIcon
 * @see ImageLabel
 * @see Block
 */
public class BlockLabel extends ImageLabel {

    /**
     * The <code>ImageIcon</code> object to be displayed
     * */
    private ImageIcon myIcon;

    /**
     * Creates a new BlockLabel
     * @param i ImageIcon to be displayed
     * @param a Determines whether the label is visible or not
     */
    public BlockLabel(ImageIcon i, boolean a){
        super(a);
        myIcon = i;
    }

    @Override
    /**
     * Determines whether the label is visible or not, then displayes the <code>ImageIcon</code> accordingly
     * @param i ImageIcon to be displayed
     */
    public void showImage(ImageIcon i) {
        if(getVisibility())
            this.setIcon(myIcon);
        else
            this.setForeground(Color.BLACK);
    }
}
