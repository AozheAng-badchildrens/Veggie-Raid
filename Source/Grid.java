import greenfoot.*;

/**
 * A rectangle
 * 
 * @author (Michael) 
 * @version (June 22, 2022)
 */
public class Grid extends Actor
{
    private boolean occupied; // plant in the grid or not
    
    private Plant currentPlant = null; // current plant on the grid
    
    /**
     * Constructor for objects of class Grid
     */
    public Grid()
    {
        // intially not occupied
        occupied = false;
        // invisible
        getImage().setTransparency(0);
    }
    
    /**
     * set occupied
     * 
     * @param b - boolean to set occupied true/false
     */
    public void setOccupied(boolean b)
    {
        occupied = b;
    }
    
    /**
     * true if occupied and false otherwise
     * 
     * @return - true/false
     */
    public boolean getOccupied()
    {
        return occupied;
    }
    
    /**
     * set the current plant on me
     * 
     * @param p - the plant
     */
    public void setPlant(Plant p){
        currentPlant = p;
    }
    
    /**
     * get the plant on me or null if none
     * 
     * @return - current plant on me
     */
    public Plant getPlant(){
        return currentPlant;
    }
}
