import greenfoot.*;

/**
 * The Main Menu
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class Menu extends World
{
    private Rectangle easy, medium, hard; // 3 difficulty buttons
    private Rectangle mainGame, spikeGame1, mineGame1, spikeGame2, mineGame2; // game buttons
    private Rectangle info; // info button
    private boolean miniGames = false; // are mini games unlocked
    private UserInfo user;
    // sound
    private GreenfootSound backgroundS;
    /**
     * Constructor for objects of class Menu
     */
    public Menu()
    {
        super(1000, 600, 1);
        // add all buttons
        easy = new Rectangle(262, 75);
        addObject(easy, 167, 320);
        medium = new Rectangle(262, 75);
        addObject(medium, 167, 418);
        hard = new Rectangle(262, 75);
        addObject(hard, 167, 521);
        mainGame = new Rectangle(262, 127);
        addObject(mainGame, 167, 181);
        spikeGame2 = new Rectangle(189, 58);
        addObject(spikeGame2, 580, 440);
        mineGame2 = new Rectangle(189, 58);
        addObject(mineGame2, 860, 440);
        spikeGame1 = new Rectangle(189, 157);
        addObject(spikeGame1, 580, 322);
        mineGame1 = new Rectangle(189, 157);
        addObject(mineGame1, 859, 322);
        info = new Rectangle(80, 80);
        addObject(info, 932, 56);
        backgroundS = new GreenfootSound("mainTheme.mp3");
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            if (user != null){
                if (miniGames){
                    user.setInt(0, 1);
                }
                else{
                    user.setInt(0, 0);
                }
                user.store();
            }
        } 
        if (miniGames){
            setBackground("FreeMenu.png");
        }
        else{
            setBackground("LockedMenu.png");
        }
    }
    
    /**
     * Another constructor 
     * 
     * @param miniGames - boolean if mini games are unlocked
     */
    public Menu(boolean b){
        this();
        miniGames = b;
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            if (user != null){
                if (miniGames){
                    user.setInt(0, 1);
                }
                else{
                    user.setInt(0, 0);
                }
                user.store();
            }
        } 
        if (miniGames){
            setBackground("FreeMenu.png");
        }
        else{
            setBackground("LockedMenu.png");
        }
    }
    
    /**
     * Another constructor
     * 
     * @param sound - pointer to theme song
     * @param b - boolean if mini games are unlocked
     */
    public Menu(GreenfootSound sound, boolean b){
        this();
        backgroundS = sound;
        miniGames = b;
        if (UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            if (user != null){
                if (miniGames){
                    user.setInt(0, 1);
                }
                else{
                    user.setInt(0, 0);
                }
                user.store();
            }
        } 
        if (miniGames){
            setBackground("FreeMenu.png");
        }
        else{
            setBackground("LockedMenu.png");
        }
    }
    
    /**
     * Another constructor
     * 
     * @param sound - pointer to theme song
     */
    public Menu(GreenfootSound sound){
        this();
        backgroundS = sound;
        if (miniGames){
            setBackground("FreeMenu.png");
        }
        else{
            setBackground("LockedMenu.png");
        }
    }
    
    public void started(){
        backgroundS.playLoop();
    }
    
    public void stopped(){
        backgroundS.stop();
    }
    
    /**
     * set if mini games are unlocked or not
     * 
     * @praram b - true/false
     */
    // private static void setMiniGame(boolean b){
        // miniGames = b;
    // }
    
    /**
     * Act method
     */
    public void act(){
        if (!backgroundS.isPlaying()){
            backgroundS.playLoop();
        }
        // checks which buttons are clicked and do corresponding tasks
        if (Greenfoot.mouseClicked(easy)){
            backgroundS.stop();
            if (miniGames){
                Greenfoot.setWorld(new MyWorld(0));
            }
            else{
                Greenfoot.setWorld(new MyWorld(0, -2));
            }
        }
        else if (Greenfoot.mouseClicked(medium)){
            backgroundS.stop();
            if (miniGames){
                Greenfoot.setWorld(new MyWorld(1));
            }
            else{
                Greenfoot.setWorld(new MyWorld(1, -2));
            }
        }
        else if (Greenfoot.mouseClicked(hard)){
            backgroundS.stop();
            if (miniGames){
                Greenfoot.setWorld(new MyWorld(2));
            }
            else{
                Greenfoot.setWorld(new MyWorld(2, -2));
            }
        }
        else if (Greenfoot.mouseClicked(mainGame)){
            backgroundS.stop();
            Greenfoot.setWorld(new DifficultyScreen(miniGames));
        }
        else if (Greenfoot.mouseClicked(info)){
            Greenfoot.setWorld(new PlantInfo(backgroundS, miniGames));
        }
        if (miniGames){
            if (Greenfoot.mouseClicked(spikeGame1) || Greenfoot.mouseClicked(spikeGame2)){
                backgroundS.stop();
                Greenfoot.setWorld(new MyWorld(1, 0));
            }
            else if (Greenfoot.mouseClicked(mineGame1) || Greenfoot.mouseClicked(mineGame2)){
                backgroundS.stop();
                Greenfoot.setWorld(new MyWorld(0, 1));
            }
        }
    }
}
