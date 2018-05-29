package tetris.panels;

import tetris.resources.Block;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;

/**
 * A BlockPanel shows which Blocks are coming next in a Tetris game
 * @see TetrisPanel
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @see JPanel
 */
public class BlockPanel extends JPanel {
    /**
     * BufferedImage to draw onto
     */
    private BufferedImage myImage;
    /**
     * Graphics2D object which draws onto myImage
     */
    private Graphics2D myBuffer;
    /**
     * Displays the next blocks onto the JPanel
     */
    private Timer t;
    /**
     * Constant background for the entire panel
     */
    private static final Color BACKGROUND = Color.BLACK;
    /**
     * Stores the next 5 blocks
     */
    private Block[] nextBlocks;
    /**
     * Used to easily reference ImageIcons for the 7 Tetriminos
     * @see ImageIcon
     */
    private HashMap<Integer, String> images;
    /**
     * JLabels to show the upcoming block images
     */
    private JLabel jb1,jb2,jb3,jb4,jb5;

    private boolean isReset;

    /**
     * Creates a new BlockPanel
     * @param nextBlocks upcoming 5 blocks in the <code>TetrisPanel</code> ArrayList block queue
     * @see TetrisPanel
     */
    public BlockPanel(Block[] nextBlocks){
        setLayout(new BorderLayout());
        myImage = new BufferedImage(768/3,401,1);
        this.myBuffer = (Graphics2D)myImage.getGraphics();

        myBuffer.setBackground(BACKGROUND);

        this.nextBlocks = nextBlocks;
        images = new HashMap<>();


        JButton exit = new JButton("Exit");
        exit.addActionListener(
                e ->  System.exit(0)
        );
        this.add(exit,BorderLayout.SOUTH);





        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3,1));
        buttons.setPreferredSize(new Dimension(500,500));
        buttons.setBackground(Color.BLACK);
        buttons.setBorder(new LineBorder(Color.WHITE,5));
        add(buttons,BorderLayout.NORTH);

        JPanel title = new JPanel();
        title.setBackground(Color.BLACK);
        title.setLayout(new BorderLayout());

        JLabel label = new JLabel("     Next Blocks: ");
        label.setFont(new Font("Arial",Font.BOLD,50));
        label.setForeground(Color.WHITE);
        title.add(label, BorderLayout.CENTER);

        buttons.add(title);


        JPanel topButtons = new JPanel();
        topButtons.setLayout(new FlowLayout());
        topButtons.setBackground(Color.BLACK);
        topButtons.add(new JLabel(""));
        jb1 = new JLabel();
        topButtons.add(jb1);


        jb2 = new JLabel();
        topButtons.add(jb2);

        jb3 = new JLabel();
        topButtons.add(jb3);

        buttons.add(topButtons);

        JPanel botButtons = new JPanel();
        botButtons.setLayout(new FlowLayout());
        botButtons.setBackground(Color.BLACK);
        botButtons.add(new JLabel(""));

        jb4 = new JLabel();
        botButtons.add(jb4);

        jb5 = new JLabel();
        botButtons.add(jb5);

        buttons.add(botButtons);






        images.put(1, "tetris/images/cyan.png");
        images.put(3, "tetris/images/blue.png");
        images.put(5, "tetris/images/red.png");
        images.put(6, "tetris/images/purple.png");
        images.put(4, "tetris/images/green.png");
        images.put(2, "tetris/images/orange.png");
        images.put(0, "tetris/images/yellow.png");



        new Timer(5, new Starter()).start();

    }



    /**
     * Updates nextblocks to adjust for any changes
     * @param arr update block array
     */
    public void updateNextBlocks(Block[] arr){
        this.nextBlocks = arr;
    }


    /**
     * Calls the UI delegate's paint method, if the UI delegate
     * is non-<code>null</code>.  We pass the delegate a copy of the
     * <code>Graphics</code> object to protect the rest of the
     * paint code from irrevocable changes
     * (for example, <code>Graphics.translate</code>).
     * <p>
     * If you override this in a subclass you should not make permanent
     * changes to the passed in <code>Graphics</code>. For example, you
     * should not alter the clip <code>Rectangle</code> or modify the
     * transform. If you need to do these operations you may find it
     * easier to create a new <code>Graphics</code> from the passed in
     * <code>Graphics</code> and manipulate it. Further, if you do not
     * invoke super's implementation you must honor the opaque property, that is
     * if this component is opaque, you must completely fill in the background
     * in an opaque color. If you do not honor the opaque property you
     * will likely see visual artifacts.
     * <p>
     * The passed in <code>Graphics</code> object might
     * have a transform other than the identify transform
     * installed on it.  In this case, you might get
     * unexpected results if you cumulatively apply
     * another transform.
     *
     * @param g the <code>Graphics</code> object to protect
     * @see #paint
     * @see ComponentUI
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Nexted <code>ActionListener</code> class which starts showing upcoming blocks
     */
    private class Starter implements ActionListener {


        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            setIcons(nextBlocks);
            repaint();
            revalidate();
        }
    }

    /**
     * Sets the icons of the JLabels to adjust to any changes in the next blocks
     * @param nextBlocks array of the upcoming blocks
     */
    private void setIcons(Block[] nextBlocks) {
        try {
            InputStream a1 = getClass().getClassLoader().getResourceAsStream(images.get(nextBlocks[0].getType()));
            BufferedImage b1 = ImageIO.read(a1);
            jb1.setIcon(new ImageIcon(b1));

            InputStream a2 = getClass().getClassLoader().getResourceAsStream(images.get(nextBlocks[1].getType()));
            BufferedImage b2 = ImageIO.read(a2);
            jb2.setIcon(new ImageIcon(b2));

            InputStream a3 = getClass().getClassLoader().getResourceAsStream(images.get(nextBlocks[2].getType()));
            BufferedImage b3 = ImageIO.read(a3);
            jb3.setIcon(new ImageIcon(b3));

            InputStream a4 = getClass().getClassLoader().getResourceAsStream(images.get(nextBlocks[3].getType()));
            BufferedImage b4 = ImageIO.read(a4);
            jb4.setIcon(new ImageIcon(b4));

            InputStream a5 = getClass().getClassLoader().getResourceAsStream(images.get(nextBlocks[4].getType()));
            BufferedImage b5 = ImageIO.read(a5);
            jb5.setIcon(new ImageIcon(b5));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}