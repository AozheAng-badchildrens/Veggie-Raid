import greenfoot.*;
import java.util.ArrayList;

/**
 * A spike that zombies cannot eat
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class Spike extends Plant 
{
    // image
    private String images[] = new String[7];
    private double damage; // damage
    /**
     * Constructor for objects of class Spike
     */
    public Spike()
    {
        damage = 3;
        attackDuration = 45;
        attackDurationCounter = 10;
        for (int i=0; i<7; i++){
            images[i] = "spike" + String.valueOf(i + 1) + ".png";
        }
    }

    /**
     * @return - false
     */
    public boolean checkIfAttack(){
        return false; 
    }
    
    public void attack(){
        
    }
    
    /**
     * animation
     */
    public void animation(){
        normalAnimationChange++;
        if (normalAnimationChange == 8){
            setImage(images[normalAnimationCounter++]);
            normalAnimationCounter %= 7;
            normalAnimationChange = 0;
        }
    }
    
    /**
     * zombies would ignore me
     * 
     * @return - true
     */
    public boolean ignore(){
        return true;
    }
    
    /**
     * Act method
     */
    public void act(){
        animation();
        attackDurationCounter--;
        // get all zombies touching me and damage them
        if (attackDurationCounter == 0){
            ArrayList<Zombie> zombies = (ArrayList<Zombie>)getIntersectingObjects(Zombie.class);
            for (Zombie z : zombies){
                if (z != null){
                    z.damageMe(damage);
                }
            }
            attackDurationCounter = attackDuration;
        }
    }
}
