/**
 * Title Screen
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
import greenfoot.*;
public class TitleScreen extends World
{
    private UserInfo user;
    private Rectangle rec; // start button
    private boolean miniGame = false;
    // theme music
    private GreenfootSound backgroundS = new GreenfootSound("mainTheme.mp3");
    /**
     * Constructor for objects of class TitleScreen
     */
    public TitleScreen()
    {
        super(1000, 600, 1);
        // add button
        rec = new Rectangle(164, 95);
        addObject(rec, 483, 498);
        // get user info to see if mini games are unlocked or no
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            if (user != null){
                if (user.getInt(0) == 1){
                    miniGame = true;
                }
            }
        } 
    }
    
    public void started(){
        backgroundS.playLoop();
    }
    
    public void stopped(){
        backgroundS.stop();
    }
    
    /**
     * Act method checks if the button is pressed
     */
    public void act(){
        if (!backgroundS.isPlaying()){
            backgroundS.playLoop();
        }
        if (Greenfoot.mouseClicked(rec)){
            Greenfoot.setWorld(new StoryBoard(backgroundS, miniGame));
        }
    }
}
