package tetris.resources;

import javax.swing.*;
import java.awt.*;

public class BlockLabel extends ImageLabel {

    private ImageIcon myIcon;

    public BlockLabel(ImageIcon i, boolean a){
        super(a);
        myIcon = i;
    }

    @Override
    public void showImage(ImageIcon i) {
        if(getVisibility())
            this.setIcon(myIcon);
        else
            this.setForeground(Color.BLACK);
    }
}
