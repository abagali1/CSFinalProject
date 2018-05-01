package anupb.panels;

import anupb.resources.Audio;
import anupb.resources.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;


public class TetrisPanel extends JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private static final int HEIGHT = JFrame.MAXIMIZED_VERT;
    private static final int WIDTH = JFrame.MAXIMIZED_HORIZ;
    private Timer t;
    private Block b;
    private Audio song;

    public TetrisPanel() {
        this.myImage = new BufferedImage(400, 401, BufferedImage.TYPE_INT_RGB );
        this.myBuffer = (Graphics2D) myImage.getGraphics();
        this.b = new Block(0, 200,Optional.of(6), Optional.of(5), 2);
        this.t = new Timer(5, new Listener());
        /*
        try {
            song = new Audio("C:\\Users\\anupb\\Desktop\\CSProject\\src\\anupb\\audio\\TetrisTheme.wav");
            this.song.play();
        }catch(Exception e){ e.getCause(); } */

        this.t.start();
        addKeyListener(new Key());
        this.setFocusable(true);
        //asfd;ajsdf
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }


    private class Key extends KeyAdapter{
        /**
         * Checks if a key has been pressed and which key has been pressed
         * e KeyEvent
         */

        public void keyPressed(KeyEvent e)
        {
            if ((e.getKeyCode() == KeyEvent.VK_LEFT)){// && (b.getX() > 10)) {
                System.out.println("L");
                b.setLAcc(true);
            } else if ((e.getKeyCode()== KeyEvent.VK_RIGHT)){// && (b.getX() < 290)) {
                System.out.println("R");
                b.setRAcc(true);
            }
        }
        /**
         * Checks if a key has been released and which key is released
         * @param e KeyEvent
         */
        public void keyReleased(KeyEvent e){
            if ((e.getKeyCode() == KeyEvent.VK_LEFT)){// && (bbot.getX() > 10)) {
                System.out.println("L");
                b.setLAcc(false);
            }
            else if ((e.getKeyCode()== KeyEvent.VK_RIGHT)){// && (bbot.getX() < 290)) {
                System.out.println("R");
                b.setRAcc(false);
            }
        }
    }


    private class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            myBuffer.setColor(Color.black);
            myBuffer.fillRect(0,0,getWidth(), getHeight());
            myBuffer.setColor(Color.WHITE);
            for(int i=0;i<=400;i+=10) {
                myBuffer.drawLine(i, 0, i, 400 );
                myBuffer.drawLine(0, i, 400, i);
            }
            myBuffer.drawLine(400,0, 400,400);
            myBuffer.drawLine(0,400,400,400);
            b.draw(myBuffer);
            b.move(10);
            repaint();
            revalidate();
        }
    }
}
