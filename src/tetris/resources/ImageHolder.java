package tetris.resources;

import javax.swing.*;
import java.awt.*;

public class ImageHolder extends Ball {
    String filename;
    public ImageHolder(String filename, double x, double y, double d, Color c){
        super(x,y,d,c);
        this.filename = filename;
    }
    @Override
    public void draw(Graphics myBuffer){
        ImageIcon pic = new ImageIcon(filename);
        myBuffer.drawImage(pic.getImage(), (int)(getX() - getRadius()), (int)(getY() - getRadius()), 50, 50, null);
    }
}
