package anupb.panels;

import anupb.input.KeyInput;
import anupb.resources.AddingListener;
import anupb.resources.Audio;
import anupb.resources.Block;
import anupb.resources.BlockTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;


public class TetrisPanel extends JPanel{
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private Timer t;
    private BlockTimer k;
    private ArrayList<Block> blocks;
    private boolean[][] gameboard;
    private Audio song;
    private int blockCount = 0;
    private Block temp;
    private Keeper keep;
    private int[] yPos;

    public TetrisPanel() {
        this.myImage = new BufferedImage(201, 401, BufferedImage.TYPE_INT_RGB);
        this.myBuffer = (Graphics2D) myImage.getGraphics();
        blocks = new ArrayList<>();

        this.t = new Timer(75, new Listener());

        this.keep = new Keeper();
        this.k = new BlockTimer(1, keep);

        gameboard = new boolean[20][40];
        for(int r=0;r<=gameboard.length-1;r++){
            for(int c=0;c<=gameboard[0].length-1;c++){
                gameboard[r][c] = false;
            }
        }

        yPos = new int[18];
        for(int r=0;r<=200-30;r+=10)
            yPos[r/10] = r;
        System.out.println(java.util.Arrays.toString(yPos));

        this.setFocusable(true);
        requestFocus();

        this.t.start();
        //asfd;ajsdf
    }

    public void setKeyListener(Block b) {
        this.addKeyListener(new KeyInput(b));
    }

    @Override
    public void paintComponent(Graphics g) {
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
            temp = new Block(((int) (Math.random() * 401)), 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                    Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7)));

            blocks.add(temp);
            setKeyListener(temp);

            myBuffer.setColor(Color.black);
            myBuffer.fillRect(0, 0, getWidth(), getHeight());
            myBuffer.setColor(Color.WHITE);

            for (int i = 0; i <= 400; i += 10) {
                myBuffer.drawLine(i, 0, i, 400);
                myBuffer.drawLine(0, i, 401, i);
            }

            myBuffer.drawLine(400, 0, 400, 400);
            myBuffer.drawLine(0, 400, 400, 400);

            blocks.get(blockCount).draw(myBuffer);
            blocks.get(blockCount).move(10);

            Block.setFall(true);
            Block.rain(blocks.get(blockCount), myBuffer);

            System.out.println(blocks.get(blockCount).toString());
            if (blocks.get(blockCount).getY() == ((blocks.get(blockCount).getType() != 1) ? 380 : 390))
                blockCount++;

            if(blocks.get(blockCount).getY() == 400)
                k.start(keep,blocks.get(blockCount));

            repaint();
            revalidate();
        }
    }

    public void finished(Block b){
        ArrayList<Rectangle2D.Double> temp = b.convert();
        for(Rectangle2D.Double d: temp){
            myBuffer.fill(d);
        }
    }

    private class Keeper implements AddingListener{
        private ArrayList<Block> constantBlocks = new ArrayList<>();

        public Keeper() {}
        /**
         * Adds block to constant block queue
         *
         * @param b Block to be added
         */
        @Override
        public void add(Block b) {
            constantBlocks.add(b);
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}

