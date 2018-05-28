package tetris.resources;
//Name: Anup Bagali           Date:11/6/17
import java.awt.*;
public class Ball extends Polkadot
{
    private double dx;       // pixels to move each time step() is called.
    private double dy;
    // constructors

    public Ball()         //default constructor
    {
        super(200, 200, 50, Color.BLACK);
        dx = Math.random() * 12 - 6;          // to move vertically
        dy = Math.random() * 12 - 6;          // to move sideways
    }
    public Ball(double x, double y, double d, Color c)
    {
        super(x, y, d, c);
        dx = Math.random()* 12 - 6;
        dy = Math.random() * 12 - 6;
    }

    //modifier methods
    public void setdx(double x)
    {
        dx = x;
    }
    public void setdy(double y)
    {
        dy = y;
    }

    //accessor methods
    public double getdx()
    {
        return dx;
    }
    public double getdy()
    {
        return dy;
    }

    //instance methods
    public void move(double rightEdge, double bottomEdge)
    {
        setX(getX() + dx);
        setY(getY() + dy);
        if (getX() >= rightEdge - getRadius())
        {
            setX(rightEdge - getRadius());
            dx *= -1.0;
        }
        else if (getX() <= getRadius())
        {
            setX(getRadius());
            dx *= -1.0;
        }
        if (getY() >= bottomEdge - getRadius())
        {
            setY(bottomEdge - getRadius());
            dy *= -1.0;
        }
        else if (getY() <= getRadius())
        {
            setY(getRadius());
            dy *= -1.0;
        }
    }
}
