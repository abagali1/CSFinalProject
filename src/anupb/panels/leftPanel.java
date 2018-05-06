package anupb.panels;

import anupb.resources.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;

public class leftPanel extends javax.swing.JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private Timer t, t1, t2;
    private ArrayList<Block> blocks;
    private int count = 0;
    private Block temp;
    private int[] yPos = new int[] {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 0};


    public leftPanel(){
        myImage = new BufferedImage(200,401, BufferedImage.TYPE_INT_RGB);
        myBuffer = (Graphics2D) myImage.getGraphics();

        blocks = new ArrayList<>();
        blocks.add(new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7))));

        t = new Timer(5, new Listener());
        t1 = new Timer(5, new Listener1());
        t2 = new Timer(5, new Listener2());
        t.start();
        t1.start();
        t2.start();
    }
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class Listener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            temp = new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                    Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7)));

            blocks.add(temp);

            myBuffer.setColor(Color.black);
            myBuffer.fillRect(0, 0, getWidth(), getHeight());
            myBuffer.setColor(Color.WHITE);

            blocks.get(count).draw(myBuffer);
            blocks.get(count).move(10);

            Block.setFall(true);
            Block.rain(blocks.get(count), myBuffer);

            System.out.println(blocks.get(count).toString());
            if (blocks.get(count).getY() == ((blocks.get(count).getType() != 1) ? 380 : 390))
                count++;

            repaint();
            revalidate();
        }
    }

    private class Listener1 implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            temp = new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                    Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7)));

            blocks.add(temp);

            myBuffer.setColor(Color.black);
            myBuffer.fillRect(0, 0, getWidth(), getHeight());
            myBuffer.setColor(Color.WHITE);

            blocks.get(count).draw(myBuffer);
            blocks.get(count).move(10);

            Block.setFall(true);
            Block.rain(blocks.get(count), myBuffer);

            System.out.println(blocks.get(count).toString());
            if (blocks.get(count).getY() == ((blocks.get(count).getType() != 1) ? 380 : 390))
                count++;

            repaint();
            revalidate();
        }
    }
    private class Listener2 implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            temp = new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                    Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7)));

            blocks.add(temp);

            myBuffer.setColor(Color.black);
            myBuffer.fillRect(0, 0, getWidth(), getHeight());
            myBuffer.setColor(Color.WHITE);

            blocks.get(count).draw(myBuffer);
            blocks.get(count).move(10);

            Block.setFall(true);
            Block.rain(blocks.get(count), myBuffer);

            System.out.println(blocks.get(count).toString());
            if (blocks.get(count).getY() == ((blocks.get(count).getType() != 1) ? 380 : 390))
                count++;

            repaint();
            revalidate();
        }
    }
}
