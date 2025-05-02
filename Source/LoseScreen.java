import greenfoot.*;

/**
 * The Lose Screen
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class LoseScreen extends World
{
    private Rectangle r, i, p; // 3 buttons
    private int mode, miniGame; // mode
    // sound
    private GreenfootSound sound = new GreenfootSound("gameover.wav");
    /**
     * Constructor for objects of class LoseScreen
     * 
     * @param mode - mode of the game for redo
     * @param miniGame - miniGame number
     */
    public LoseScreen(int mode, int miniGame)
    {
        super(1000, 600, 1);
        // Add all buttons
        this.mode = mode;
        this.miniGame = miniGame;
        r = new Rectangle(214, 65);
        addObject(r, 510, 221);
        i = new Rectangle(214, 65);
        addObject(i, 510, 288);
        p = new Rectangle(214, 65);
        addObject(p, 510, 352);
        sound.play();
    }

    /**
     * Act method checks which key/button is pressed and directs to the corresponding screen
     */
    public void act(){
        String key = Greenfoot.getKey();
        if (Greenfoot.mouseClicked(r) || (key != null && key.equals("r"))){
            if (sound.isPlaying()){
                sound.stop();
            }
            Greenfoot.setWorld(new MyWorld(mode, miniGame));
        }
        else if (Greenfoot.mouseClicked(i) || (key != null && key.equals("i"))){
            if (sound.isPlaying()){
                sound.stop();
            }
            Greenfoot.setWorld(new DifficultyScreen(miniGame == -2 ? false : true));
        }
        else if (Greenfoot.mouseClicked(p) || (key != null && key.equals("p"))){
            // -2 represents miniGame locked
            if (sound.isPlaying()){
                sound.stop();
            }
            if (miniGame == -2){
                Greenfoot.setWorld(new Menu(false));
            }
            else{
                Greenfoot.setWorld(new Menu(true));
            }
        }
    }
}
