import greenfoot.*;

/**
 * Act as a button
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class Rectangle extends Actor
{
    private GreenfootImage image;

    /**
     * A transparent rectangular button
     * 
     * @param x - length
     * @param y - width
     */
    public Rectangle(int x, int y)
    {
        // draw the rectangle
        this.image = new GreenfootImage(x, y);
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, x, y);
        image.setColor(Color.RED);
        image.fillRect(0, 0, x, y);
        setImage(image);
        getImage().setTransparency(0);
    }
}
