import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
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
    /**
     * BufferedImage to draw onto
     */
    private BufferedImage myImage;
    /**
     * Constant background for the entire panel
     */
    private static final Color BACKGROUND = Color.BLACK;
    /**
     * Displays the scores
     */
    private JLabel highscores;
    /**
     * Stores the current score
     */
    private int curr;
    /**
     * Becomes true if a button was clicked
     */
    private boolean buttonClicked;
    /**
     * Creates a new ScorePanel
     */
    public ScorePanel(){
        myImage = new BufferedImage(768/3,401,1);
        /*
      Graphics2D object which draws onto myImage
     */
        Graphics2D myBuffer = (Graphics2D) myImage.getGraphics();
        myBuffer.setBackground(BACKGROUND);

        setLayout(new GridLayout(4,1));

        highscores = new JLabel(
                "<html>" +
                        "<ol>" +
                        "   <li><h1>Current Score: " + curr + "</h1></li>"+
                        "</ol>" +
                        "</html>"
        );
        highscores.setForeground(Color.white);
        highscores.setBackground(Color.BLACK);
        add(highscores);

        /*
      Displays the Tetris logo
     */
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon("src/anupb/images/logo-game.png"));
        logo.setHorizontalAlignment(0);
        add(logo);

        /*
      When pressed the current highscore, along with a name is saved to a txt file
     */
        JButton save = new JButton("Save Current Game");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.white);
        save.addActionListener(e -> save());
        add(save);

        /*
      When pressed, a previous game can be referenced by name and loaded to the current game
     */
        JButton load = new JButton("Load Previous Game");
        load.setBackground(Color.BLACK);
        load.setForeground(Color.white);
        load.addActionListener(e -> load());
        add(load);

        buttonClicked = false;
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
     * Links the current highscore to a username and saves the information to a external file
     */
    public void save(){
        String name;
        try {
            Writer out = new FileWriter("scores.txt",true);
            do {
                name = JOptionPane.showInputDialog("What is your name?(name is case-sensitive, ONLY LETTERS)");
            } while (name.isEmpty() || name.matches(".*\\d+.*"));
            out.write(name+"\n");
            out.write(String.valueOf(curr)+"\n");
            out.write("------\n");
            out.flush();
            out.close();

            JOptionPane.showMessageDialog(null, "Save Successful!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Your scores were not able to save");
        }
        buttonClicked = true;
        repaint();
        revalidate();
    }


    /**
     * Loads a highscore from an external text file by referencing a username
     */
    public void load(){
        Scanner infile;
        try{
            int h = 0;
            RuntimeException threw;
            infile = new Scanner(new File("scores.txt"));

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
                JOptionPane.showMessageDialog(null,"Specified Player was not found!");
            }else {
                update(h);
                JOptionPane.showMessageDialog(null, "Game for player " + name + " was loaded successfully");
            }

        }catch (RuntimeException re){
            JOptionPane.showMessageDialog(null, "Specified Player was not found!");
        } catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Uh-oh! Something went wrong, your file could not be saved!");
        }
        buttonClicked = true;
        repaint();
        revalidate();

    }


    /**
     * Updates the scores <code>JLabel</code> to adjust to any changes in scores
     * @param cu new current score
     */
    public void update( int cu){

        curr = cu;
        highscores.setText(
                "<html>" +
                        "<ol>" +
                        "   <li><h1>Current Score: " + curr + "</h1></li>"+
                        "</ol>" +
                        "</html>"
        );
        repaint();
        revalidate();
    }

    /**
     * Returns true if a button has been clicked
     * @return whether a button has been clicked or not
     */
    boolean isClicked(){
        return buttonClicked;
    }

    /**
     * Returns the current score
     * @return the current score
     */
    int getCScore() {
        return curr;
    }

    public void reset() {
        curr = 0;
    }

    void setClicked(boolean b) {
        buttonClicked = b;
    }
}