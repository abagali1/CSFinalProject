package anupb.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ScorePanel extends JPanel {
    private BufferedImage myImage;
    private Graphics2D myBuffer;
    private static final Color BACKGROUND = Color.BLACK;
    private JLabel highscores, logo;
    private JButton save, load;
    private int high, curr;


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
    public void paintComponent(Graphics g){
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class Saver implements ActionListener{
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
