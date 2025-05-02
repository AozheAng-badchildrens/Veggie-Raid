import greenfoot.*;

/**
 * The Difficulty Screen
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class DifficultyScreen extends World
{
    // 3 buttons representing easy, medicore and hard
    private Rectangle easy, med, hard;
    private boolean miniGames;
    /**
     * Constructor for objects of class DifficultyScreen
     * 
     * @param boolean b - true for minigame unlocked and false otherwise
     */
    public DifficultyScreen(boolean b)
    {
        super(1000, 600, 1);
        // add all the buttons
        easy = new Rectangle(133, 95);
        addObject(easy, 301, 411);
        med = new Rectangle(133, 95);
        addObject(med, 508, 411);
        hard = new Rectangle(133, 95);
        addObject(hard, 708, 411);
        miniGames = b;
    }
    
    /**
     * Act method checks which button is being clicked and set the corresponding difficulty
     */
    public void act(){
        if (Greenfoot.mouseClicked(easy)){
            if (miniGames){
                Greenfoot.setWorld(new MyWorld(0));
            }
            else{
                // -2 represents minigame locked
                Greenfoot.setWorld(new MyWorld(0, -2));
            }
        }
        else if (Greenfoot.mouseClicked(med)){
            if (miniGames){
                Greenfoot.setWorld(new MyWorld(1));
            }
            else{
                Greenfoot.setWorld(new MyWorld(1, -2));
            }
        }
        else if (Greenfoot.mouseClicked(hard)){
            if (miniGames){
                Greenfoot.setWorld(new MyWorld(2));
            }
            else{
                Greenfoot.setWorld(new MyWorld(2, -2));
            }
        }
    }
}
