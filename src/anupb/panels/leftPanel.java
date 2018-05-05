package anupb.panels;

import anupb.resources.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class leftPanel extends javax.swing.JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private Timer t;

    public leftPanel(){
        myImage = new BufferedImage(201,401, BufferedImage.TYPE_INT_RGB);
        myBuffer = (Graphics2D) myImage.getGraphics();

        t = new Timer(5, new Listener());
        t.start();
    }

    private class Listener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Block.automaticRain(myBuffer);
        }
    }
}
