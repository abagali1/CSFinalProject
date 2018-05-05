package anupb.panels;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * A ScorePanel displays the current highscore and current score while providing functionality to save and load scores to text files
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @see JPanel
 */
public class ScorePanel extends JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private static final Color BACKGROUND = Color.BLACK;
    private JLabel highscores, logo;
    private JButton save, load;
    private int high, curr;

    /**
     * Creates a new ScorePanel
     */
    public ScorePanel(){
        myImage = new BufferedImage(400,400,1);
        myBuffer = (Graphics2D) myImage.getGraphics();
        myBuffer.setBackground(BACKGROUND);

        setLayout(new GridLayout(4,1));

        highscores = new JLabel(
                "<html>" +
                        "<ol>" +
                        "   <li><b>Highscore: " + high + "</b></li>" +
                        "   <li><b>Current Score: " + curr + "</b></li>"+
                        "</ol>" +
                        "</html>"
        );
        highscores.setForeground(Color.white);
        highscores.setBackground(Color.BLACK);
        add(highscores);

        logo = new JLabel();
        logo.setIcon(new ImageIcon("src/anupb/images/logo-game.png"));
        logo.setHorizontalAlignment(0);
        add(logo);

        save = new JButton("Save Currnet Game");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.white);
        save.addActionListener(new Saver());
        add(save);

        load = new JButton("Load Previous Game");
        load.setBackground(Color.BLACK);
        load.setForeground(Color.white);
        load.addActionListener(new Loader());
        add(load);






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
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Nexted <code>ActionListener</code> clss for saving scores to text files
     */
    private class Saver implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
                String name;
                try {
                    System.setOut(new PrintStream(new FileOutputStream("C:\\Users\\anupb\\Desktop\\CSProject\\src\\anupb\\files\\scores.txt")));
                    do {
                        name = JOptionPane.showInputDialog("What is your name?(name is case-sensitive)");
                    } while (name.isEmpty());
                    System.out.println(name);
                    System.out.println(high);
                    System.out.println("------");

                    JOptionPane.showMessageDialog(null, "Save Successful!");
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Your scores were not able to save");
                } catch(FileNotFoundException ef){
                    JOptionPane.showMessageDialog(null, "Your scores were not able to save");
                }
            }
        }

    /**
     * Nexted <code>ActionListener</code> class for loading previous games from text files
     */
    private class Loader implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Scanner infile;
            try{
                int count = 0;
                int h = 0;
                RuntimeException pnf;
                infile = new Scanner(new File("C:\\Users\\anupb\\Desktop\\CSProject\\src\\anupb\\files\\scores.txt"));

                String name = JOptionPane.showInputDialog("Which player do you want to load?(name is case-sensitive)" +
                        "\n*WARNING* THIS ACTION WILL RESET YOUR CURRENT SCORE TO 0");

                while(infile.hasNext()){
                    if(infile.next().equals(name)){
                         h = infile.nextInt();
                         break;
                    }else{
                         h = -1;
                    }
                }
                if(h == -1){
                     pnf = new RuntimeException("PlayerNotFoundException");
                    throw pnf;
                }else {
                    update(h, 0);
                    JOptionPane.showMessageDialog(null, "Game for player " + name + " was loaded successfully");
                }

            }catch (RuntimeException re){
                JOptionPane.showMessageDialog(null, "Specified Player was not found!");
            } catch(FileNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Uh-oh! Something went wrong, your file could not be saved!");
            }

        }
    }

    /**
     * Updates the scores <code>JLabel</code> to adjust to any changes in scores
      * @param hi new highscore
     * @param cu new current score
     */
    public void update(int hi, int cu){
        high = hi;
        curr = cu;
        highscores.setText(
                "<html>" +
                        "<ol>" +
                        "   <li>Highscore: " + high + "</li>" +
                        "   <li>Current Score: " + curr + "</li>"+
                        "</ol>" +
                        "</html>"
        );
    }
}
