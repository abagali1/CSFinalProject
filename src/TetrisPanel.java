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

    public TetrisPanel() {
        myImage = new BufferedImage(400, 401, BufferedImage.TYPE_INT_RGB );
        myBuffer = (Graphics2D) myImage.getGraphics();
        b = new Block(0, 200,Optional.empty(), Optional.empty(), 1);
        t = new Timer(5, new Listener());

        t.start();
        b.draw(myBuffer);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
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
            repaint();
        }
    }
}
