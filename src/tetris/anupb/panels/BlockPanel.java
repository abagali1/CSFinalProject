package tetris.anupb.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class BlockPanel extends JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private Timer t;
    private static final Color BACKGROUND = Color.BLACK;
    public BlockPanel(){
        myImage = new BufferedImage(400,400,1);
        myBuffer = (Graphics2D)myImage.getGraphics();
        myBuffer.setBackground(BACKGROUND);
        t = new Timer(5, new Starter());
        t.start();

    }
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class Starter implements ActionListener {
        public void actionPerformed(ActionEvent e){

        }
    }
}
