import greenfoot.*;

/**
 * Story line
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class StoryBoard extends World
{
    // sound
    private GreenfootSound sound;
    private boolean miniGame;
    /**
     * Constructor for objects of class StoryBoard
     * 
     * @param sound - pointer to music
     * @param miniGame - true if minigame unlocked and false otherwise
     */
    public StoryBoard(GreenfootSound sound, boolean miniGame)
    {
        super(1000, 600, 1);
        this.sound = sound;
        this.miniGame = miniGame;
    }

    public void started(){
        sound.playLoop();
    }
    
    public void stopped(){
        sound.stop();
    }
    
    /**
     * Act method checks if space is pressed
     */
    public void act(){
        if (!sound.isPlaying()){
            sound.playLoop();
        }
        String key = Greenfoot.getKey();
        if (key != null){
            if (key.equals("space")){
                Greenfoot.setWorld(new Menu(sound, miniGame));
            }
        }
    }
}
