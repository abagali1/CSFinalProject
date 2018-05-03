package anupb.resources;


import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class Block {
    private int myX, myY, myType, myWidth, myHeight;
    private Integer myX2, myY2, myW2, myH2;
    private Color myColor;
    private boolean lacc, racc;
    private static boolean fall;

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
                myColor = Color.blue.brighter();
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
                myHeight = 20;
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

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

    public Color getColor() {
        return myColor;
    }

    public void setX(int x) {
        myX = x;
    }

    public void setY(int y) {
        myY = y;
    }

    public void setColor(Color c) {
        myColor = c;
    }

    public boolean getRAcc(boolean a){ return racc; }

    public boolean getLAcc(boolean a){ return lacc; }

    public void setRAcc(boolean a){ racc = a;}

    public void setLAcc(boolean a){ lacc = a; }

    public static boolean getFall(){ return fall; }

    public static void setFall(boolean a){ fall = a;   }

    public int getWidth() {
        if(myType <= 3)
            return myWidth;
        else if(myType == 4 || myType == 5)
            return 30;
        else
            return 30;
    }

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

    public void draw(Graphics myBuffer){
        myBuffer.setColor(myColor);
        if(myType <= 1){
            myBuffer.fillRect(myX,myY,myWidth,myHeight);
        }else{
            myBuffer.fillRect(myX,myY,myWidth,myHeight);
            myBuffer.fillRect(myX2, myY2, myW2, myH2);
        }
    }

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
    private void move(int x, String a){
        myY += x;
        myY2 = (myY2 != null) ? myY2+x : null;
    }
    public static void rain(ArrayList<Block> blocks, int index, Graphics myBuffer){
        Block current;
        int count = index;
        if(getFall()){
            current = blocks.get(index);
            current.draw(myBuffer);
            if(current.getX() != 400)
                current.move(10, null);
        }
    }



}