package anupb.panels;

import anupb.input.KeyInput;
import anupb.resources.Audio;
import anupb.resources.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
=======
>>>>>>> d87ebeb76c05d4910ff00f359412e4f72c349bd0
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;


public class TetrisPanel extends JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private Timer t;
    private ArrayList<Block> blocks;
    private Audio song;
    private int blockCount = 0;

    public TetrisPanel() {
        this.myImage = new BufferedImage(201, 401, BufferedImage.TYPE_INT_RGB );
        this.myBuffer = (Graphics2D) myImage.getGraphics();
        blocks = new ArrayList<>();
        this.t = new Timer(70, new Listener());

        this.setFocusable(true);
        requestFocus();

        this.t.start();
        //asfd;ajsdf
    }
    public void setKeyListener(Block b){
        this.addKeyListener(new KeyInput(b));
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            revalidate();
            Block temp = new Block(((int)(Math.random()*401)),((int)(Math.random()*401)), Optional.of(((int)(Math.random()*401))), Optional.of(((int)(Math.random()*401))), ((int)(Math.random()*7)) );
            blocks.add(temp);
            setKeyListener(temp);
            myBuffer.setColor(Color.black);
            myBuffer.fillRect(0,0,getWidth(), getHeight());
            myBuffer.setColor(Color.WHITE);
            for(int i=0;i<=400;i+=10) {
                myBuffer.drawLine(i, 0, i, 400 );
                myBuffer.drawLine(0, i, 401, i);
            }
            myBuffer.drawLine(400,0, 400,400);
            myBuffer.drawLine(0,400,400,400);
            blocks.get(blockCount).draw(myBuffer);
            blocks.get(blockCount).move(10);
            Block.setFall(true);
            Block.rain(blocks,blockCount, myBuffer);
            repaint();
            revalidate();
        }
    }
}
