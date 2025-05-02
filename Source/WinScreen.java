import greenfoot.*;

/**
 * The Win Screen
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class WinScreen extends World
{
    private Rectangle rec; // button
    // sound
    private GreenfootSound sound = new GreenfootSound("win.wav");
    /**
     * Constructor for objects of class WinScreen
     */
    public WinScreen()
    {
        super(1000, 600, 1);
        // add button
        rec = new Rectangle(310, 76);
        addObject(rec, 846, 562);
        sound.play();
    }
    
    /**
     * Act method checks if the button is pressed
     */
    public void act(){
        if (Greenfoot.mouseClicked(rec)){
            if (sound.isPlaying()){
                sound.stop();
            }
            Greenfoot.setWorld(new Menu(true));
        }
    }
}
