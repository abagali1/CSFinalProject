import java.awt.*;
import java.util.Optional;

public class Block {
    private int myX, myY, myType, myWidth, myHeight;
    private Integer myX2, myY2, myW2, myH2;
    private Color myColor;

    public Block(int x, int y, Optional<Integer> x2, Optional<Integer> y2, int t) {
        myX = x;
        myY = y;
        myType = t;
        myX2 = x2.isPresent() ? x2.get() : null;
        myY2 = y2.isPresent() ? y2.get() : null;
        switch (myType) {
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
                System.out.println("ERROR AT BLOCK CLASS LINE 42 IN CONSTRUCTOR");
                System.exit(10);
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
                System.out.println("ERROR AT BLOCK CLASS LINE 42 IN CONSTRUCTOR");
                System.exit(10);
        }
    }

    public void draw(Graphics myBuffer){
        if(myType <= 1){
            myBuffer.drawRect(myX,myY,myWidth,myHeight);
        }else{
            myBuffer.drawRect(myX,myY,myWidth,myHeight);
            myBuffer.drawRect(myX2, myY2, myW2, myH2);
        }
    }

    public void move(int x, int y){

    }

}