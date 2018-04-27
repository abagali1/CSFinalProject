package anupb.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScorePanel extends JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private static final Color BACKGROUND = Color.BLACK;

    public ScorePanel(){
        myImage = new BufferedImage(400,400,1);
        myBuffer = (Graphics2D) myImage.getGraphics();
        myBuffer.setBackground(BACKGROUND);


    }
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

}
