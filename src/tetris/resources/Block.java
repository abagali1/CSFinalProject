package tetris.resources;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Optional;

/**
 * A Block is a combination of one or two rectangles to resemble a Tetrimino block. Two blocks only require one rectangle
 * while the others require two rectangles. A block knows its x and y coordinate along with its width and height. If the block
 * requires two rectangles then it also knows its second x and y coordinate along with its second width and height.
 *
 * @author Anup Bagali
 * @author Teja Kocherla
 * @author Kevin Liu
 * @author Amit Rajesh
 * @version 1.1
 */
public class Block {
    /**
     *
     */
    private int myX, myY, myType, myWidth, myHeight;
    private Integer myX2, myY2, myW2, myH2;
    private Color myColor;
    private boolean lacc, racc;
    private static boolean fall;
    private boolean ifFinished;
    private static int[] yPos = new int[] {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140,
            150, 160, 170, 0};
    public static int count = 0;
    public static ArrayList<Block> constantBlocks = new ArrayList<>();

    /**
     * Constructor for a block.
     * @param x Starting x1 coordinate
     * @param y Starting y1 coordinate
     * @param x2 Optional parameter for x2 coordinate
     * @param y2 Optional parameter for y2 coordinate
     * @param t Type of tetrimino to be created
     */
    public Block(int x, int y, Optional<Integer> x2, Optional<Integer> y2, int t) {
        this.myX = x;
        myY = y;
        myType = t;
        myX2 = x2.isPresent() ? x2.get() : null;
        myY2 = y2.isPresent() ? y2.get() : null;
        if((myX2 == null || myY2 == null) && t > 1)
            throw new NullPointerException();
        switch (myType) {
            case 0:
                myWidth = 20;
                myHeight = 20;
                myW2 = null;
                myH2 = null;
                myColor = Color.yellow;
                break;
            case 1:
                myHeight = 10;
                myWidth = 40;
                myW2 = null;
                myH2 = null;
                myColor = Color.cyan;
                break;
            case 2:
                myWidth = 30;
                myHeight = 10;
                myX2 = myX + 20;
                myY2 = myY + 10;
                myW2 = 10;
                myH2 = 10;
                myColor = Color.orange;
                break;
            case 3:
                myWidth = 30;
                myHeight = 10;
                myX2 = myX;
                myY2 = myY + 10;
                myW2 = 10;
                myH2 = 10;
                myColor = Color.blue;
                break;
            case 4:
                myWidth = 20;
                myHeight = 10;
                myX2 = myX + 10;
                myY2 = myY + 10;
                myW2 = myWidth;
                myH2 = myHeight;
                myColor = Color.green;
                break;
            case 5:
                myWidth = 20;
                myHeight = 10;
                myX2 = myX + 10;
                myY2 = myY - 10;
                myW2 = myWidth;
                myH2 = myHeight;
                myColor = Color.red;
                break;
            case 6:
                myWidth = 30;
                myHeight = 10;
                myX2 = myX + 10;
                myY2 = myY + 10;
                myW2 = 10;
                myH2 = 10;
                myColor = Color.magenta;
                break;
            default:
                System.out.println("ERROR AT BLOCK CLASS LINE 76 IN CONSTRUCTOR");
                System.exit(76);
        }
    }

    /**
     * Accessor method for the x1 coordinate
     * @return current x1 coordinate
     */
    public int getX() {
        return myX;
    }

    /**
     * Accessor method for the y1 coordinate
     * @return current y1 coordinate
     */
    public int getY() {
        return myY;
    }

    /**
     * Accessor method for the color
     * @return Current color of the block
     */
    public Color getColor() {
        return myColor;
    }

    /**
     * Modifier method for the x1 coordinate
     * @param x desired new x1 coordinate
     */
    public void setX(int x) {
        myX = x;
    }

    /**
     * Modifier method for the y1 coordinate
     * @param y desired new y1 coordinate
     */
    public void setY(int y) {
        myY = y;
    }

    /**
     * Modifier method for the color
     * @param c desired new color
     */
    public void setColor(Color c) {
        myColor = c;
    }

    /**
     * Accessor method for the right acceleration
     * @return current right acceleration
     */
    public boolean getRAcc(){ return racc; }

    /**
     * Accessor method for the left acceleration
     * @return current left acceleration
     */
    public boolean getLAcc(){ return lacc; }

    /**
     * Modifier method for the right acceleration
     * @param a desired right acceleration
     */
    public void setRAcc(boolean a){ racc = a;}

    /**
     * Modifier method for the left acceleration
     * @param a desired left acceleration
     */
    public void setLAcc(boolean a){ lacc = a; }

    /**
     * Accessor method for the type
     * @return current block type
     */
    public int getType(){ return myType; }

    /**
     * Accessor method for the fall variable
     * @return current fall state
     */
    public static boolean getFall(){ return fall; }

    /**
     * Modifier method for the fall variable
     * @param a desired fall starte
     */
    public static void setFall(boolean a){ fall = a;   }

    /**
     * Accessor method for the block width
     * @return the current width
     */
    public int getWidth() {
        if(myType <= 3)
            return myWidth;
        else if(myType == 4 || myType == 5)
            return 30;
        else
            return 30;
    }

    /**
     * Redraws the block to adjust for any changes
     * @param a type of block to be redrawn
     */
    public void rebuild(int a) {
        switch (a) {
            case 0:
                myWidth = 20;
                myHeight = 20;
                myW2 = null;
                myH2 = null;
                break;
            case 1:
                myHeight = 10;
                myWidth = 40;
                myW2 = null;
                myH2 = null;
                break;
            case 2:
                myWidth = 30;
                myHeight = 10;
                myX2 = myX + 20;
                myY2 = myY + 10;
                myW2 = 10;
                myH2 = 10;
                break;
            case 3:
                myWidth = 30;
                myHeight = 10;
                myX2 = myX;
                myY2 = myY + 10;
                myW2 = 10;
                myH2 = 10;
                break;
            case 4:
                myWidth = 20;
                myHeight = 10;
                myX2 = myX + 10;
                myY2 = myY + 10;
                myW2 = myWidth;
                myH2 = myHeight;
                break;
            case 5:
                myWidth = 20;
                myHeight = 20;
                myX2 = myX + 10;
                myY2 = myY - 10;
                myW2 = myWidth;
                myH2 = myHeight;
                break;
            case 6:
                myWidth = 30;
                myHeight = 10;
                myX2 = myX + 10;
                myY2 = myY + 10;
                myW2 = 10;
                myH2 = 10;
                break;
            default:
                System.out.println("ERROR AT BLOCK CLASS LINE 160 IN REBUILD");
                System.exit(160);
        }
    }

    /**
     * Draws the block onto a buffered image
     * @param myBuffer desired buffered image to be drawn on
     * @see java.awt.image.BufferedImage
     */
    public void draw(Graphics myBuffer){
        myBuffer.setColor(myColor);
        if(myType <= 1){
            myBuffer.fillRect(myX,myY,myWidth,myHeight);
        }else{
            myBuffer.fillRect(myX,myY,myWidth,myHeight);
            myBuffer.fillRect(myX2, myY2, myW2, myH2);
        }
    }

    /**
     * Moves the block to the left or right
     * @param x amount of spaces to be moved
     */
    public void move(int x){
        if(racc){
            myX += x;
            myX2 = (myX2!=null) ? myX2+x : null;
        }
        else if(lacc){
            myX -= x;
            myX2 = (myX2 != null) ? myX2-x : null;
        }
    }

    /**
     * Moves the block down
     * @param x amount of spaces to be moved
     * @param a Direction to move
     */
    private void move(int x, String a) {
        if (a.toLowerCase().equals("down")) {
            myY += x;
            myY2 = (myY2 != null) ? myY2 + x : null;
        }
        if(a.toLowerCase().equals("up")){
            myY -= x;
            myY2 = (myY2 != null) ? myY2 - x:null;
        }
    }

    /**
     * Facilitates the automatic downward movement of the blocks during the Tetris game
     * @param blocks blocks to be rainfallen
     * @param myBuffer BufferedImage to be drawn on
     * @see java.awt.image.BufferedImage
     */
    public static void rain(ArrayList<Block> blocks, Graphics myBuffer) {
        Block temp;
        if(getFall()){
            temp = blocks.get(count);
            temp.draw(myBuffer);
            if(temp.getY() != ((temp.getType()!=1)?(380):(390))){
                temp.move(10, "down");
            }else{
                constantBlocks.add(temp);
                count++;
            }
            for(Block b: constantBlocks)
                b.draw(myBuffer);
        }
    }

    /**
     * Rainfall animation used solely for decoration
     * @param blocks blocks to be used
     * @param myBuffer BufferedImage to be drawn on
     * @see java.awt.image.BufferedImage
     */
    public static void prettyRain(ArrayList<Block> blocks, Graphics myBuffer) {
        if (getFall()) {
            Block temp;
            for (Block b : blocks) {
                temp = b;
                temp.draw(myBuffer);
                temp.move(10, "down");
                temp.draw(myBuffer);
            }
        }
    }



















            /*Block current;
        int count = 0;
        if(getFall()) {
            current = blocks.get(count);
            current.draw(myBuffer);
            if (current.getY() != ((current.getType() != 1) ? (380) : (390))) {
                current.move(10, null);
                current.draw(myBuffer);
            }
            if (current.getY() == ((current.getType() != 1) ? (380) : (390))) {
                current.setFinished(current);
                count++;
            }
        }*/


    /**
     * Serts a block to be in a finished state
     * @param a block to be adjusted
     */
    public void setFinished(Block a){
        Block temp = a;


    }

    /**
     * Prints current type, x1, y1, width, and height of the block
     * @return String of current state
     */
    public String toString(){
        return "Type: " + myType + " X: " + myX + " Y: " + myY + " Height: " + myHeight + " Width: " + myWidth;
    }

    /**
     * Converts the block into an <code>ArrayList</code> of <code>Rectangle2D.Double</code>
     * @return <code>ArrayList</code> of new <code>Rectangle2D.Double</code>
     */
    public ArrayList<Rectangle2D.Double> convert() {
        ArrayList<Rectangle2D.Double> arr = new ArrayList<>();
        if (myX2 != null) {
            arr.add(new Rectangle2D.Double(myX, myY, myWidth, myHeight));
        } else {
            arr.add(new Rectangle2D.Double(myX, myY, myWidth, myHeight));
            arr.add(new Rectangle2D.Double(myX2, myY2, myW2, myH2));
        }
        return arr;
    }
}