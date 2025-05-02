import greenfoot.*;

/**
 * Plant information
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class PlantInfo extends World
{
    private Rectangle exit; // exit button
    private boolean miniGames;
    // sound
    private GreenfootSound sound;
    /**
     * Constructor for objects of class PlantInfo
     * 
     * @param sound - pointer to music
     * @param boolean b - true if miniGame unlocked and false otherwise
     */
    public PlantInfo(GreenfootSound sound, boolean b)
    {
        super(1000, 600, 1);
        // add exit button
        exit = new Rectangle(52, 59);
        addObject(exit, 893, 60);
        this.sound = sound;
        miniGames = b;
    }
    
    public void started(){
        sound.playLoop();
    }
    
    public void stopped(){
        sound.stop();
    }
    
    public void act(){
        if (!sound.isPlaying()){
            sound.playLoop();
        }
        if (Greenfoot.mouseClicked(exit)){
            Greenfoot.setWorld(new Menu(sound, miniGames));
        }
    }
}
