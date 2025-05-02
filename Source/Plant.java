import greenfoot.*;

/**
 * Abstract plant superclass 
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public abstract class Plant extends Actor 
{
    // instance variables
    protected double health;  // hp of plant
    protected int attackDuration, attackDurationCounter = 50; // how often do the plant shoot
    // for animation purposes
    protected GreenfootImage image;
    protected int attackAnimationCounter = 0, normalAnimationCounter = 0;
    protected int attackAnimationChange = 0, normalAnimationChange = 0;
    
    // sound
    protected GreenfootSound sound;
    /**
     * Constructor for objects of class Plant
     */
    public Plant()
    {
        health = 50;
        attackDuration = 120;
        sound = new GreenfootSound("plant shooting.mp3");
        sound.setVolume(50);
    }
    
    public abstract void attack();
    
    public abstract boolean checkIfAttack();
    
    /**
     * return true if plant is untargetable (spike) or false otherwise
     * 
     * @return - true/false
     */
    public boolean ignore(){
        return false;
    }
    
    /**
     * damage me
     * 
     * @param damage - amount of damage
     */
    public void damageMe(double damage){
        health -= damage; // subtract damage from health
        // if dead
        if (health <= 0){
            // get the grid I am in and make it no longer occupied
            Grid g = (Grid)getOneIntersectingObject(Grid.class);
            if (g != null){
                g.setOccupied(false);
                g.setPlant(null);
            }
            getWorld().removeObject(this);
        }
    }
    
    public void act(){
        
    }
}
