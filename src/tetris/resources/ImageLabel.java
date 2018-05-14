package tetris.resources;

import javax.swing.*;

public abstract class ImageLabel extends JLabel {

    private boolean isVisible;

    public ImageLabel(boolean a){
        super();
        isVisible = a;
    }

    public abstract void showImage(ImageIcon i);

    public boolean getVisibility(){
        return isVisible;
    }
}
