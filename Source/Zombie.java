import greenfoot.*;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Abstract zombie superclass
 * 
 * @author (Michael) 
 * @version (June 22, 2022)
 */
public abstract class Zombie extends SuperSmoothMover  
{
    // zombie stats
    protected double health;
    protected double speed;
    protected double damage;
    protected double damageDuration, damageDurationCounter = 10;
    protected Queue<Boomerang> boomerangmarks; // queue of boomerangs to avoid single boomerang doing multiple damage
    // animation stuff
    protected int eatAnimationCounter = 0, normalAnimationCounter = 0;
    protected int eatAnimationChange = 0, normalAnimationChange = 0;
    protected boolean slowed; // slowed or not
    protected int slowCounter = 0; // slowed time
    
    // sound
    protected GreenfootSound sound;
    /**
     * Constructor for objects of class Zombie
     */
    public Zombie()
    {
        health = 50;
        speed = 0.5;
        damage = 10;
        damageDuration = 30;
        slowed = false;
        boomerangmarks = new LinkedList<Boomerang>();
        sound = new GreenfootSound("zombieeat.wav");
    }
    
    /**
     * damage me by some amount
     * 
     * @param damage - the amount of damage
     */
    public void damageMe(double damage){
        health -= damage; // subtract damage from health
        // if dead remove me
        if (health <= 0){
            if (sound.isPlaying()){
                sound.stop();
            }
            getWorld().removeObject(this);
        }
    }
    
    /**
     * add a boomerang 
     * 
     * @param b - the boomerang to add
     */
    public void addBoomerang(Boomerang b){
        boomerangmarks.add(b);
    }
    
    /**
     * pop a boomerang from the head of the queue
     * 
     */
    public void popBoomerang(){
        boomerangmarks.remove();    
    }
    
    /**
     * does boomerang exist in queue
     * 
     * @param b - the boomerang to check
     */
    public boolean isAdded(Boomerang b){
        return boomerangmarks.contains(b);
    }
    
    /**
     * is slowed or not
     * 
     * @return - true if slowed and false otherwise
     */
    public boolean isSlowed(){
        return slowed;
    }
    
    /**
     * set slowed
     * 
     * @param b - true/false
     */
    public void setSlowed(boolean b){
        slowed = b;
        if (slowed){
            slowCounter = 0;
        }
    }
    
    public void eatAnimation(){
        
    }
    
    public void walkAnimation(){
        
    }
    
    /**
     * Act method
     */
    public void act(){
        // get a intersecting plant
        Plant p = (Plant)getOneIntersectingObject(Plant.class);
        if (p != null){
            // if not spike eat it
            if (!p.ignore()){
                eatAnimation();
                if (damageDurationCounter == 0){
                    p.damageMe(damage);
                    sound.play();
                    damageDurationCounter = damageDuration;
                    if (slowed){
                        damageDurationCounter *= 2;   
                    }
                }
                damageDurationCounter--;
            }
            // standing on a spike
            else{
                // check if there are any plants forward
                boolean walk = true;
                for (int i=0; i>=-30; i--){
                    p = (Plant)getOneObjectAtOffset(i, 0, Plant.class);
                    // if there is a plant forward that is not a spike eat it 
                    if (p != null && !p.ignore()){
                        eatAnimation();
                        if (damageDurationCounter == 0){
                            p.damageMe(damage);
                            sound.play();
                            damageDurationCounter = damageDuration;
                            if (slowed){
                                damageDurationCounter *= 2;   
                            }
                        }
                        damageDurationCounter--;
                        walk = false;
                        break;
                    }
                }
                // there is no plant forward then walk 
                if (walk){
                    walkAnimation();
                    if (sound.isPlaying()){
                        sound.stop();
                    }
                    if (!slowed){
                        move(-1 * speed);
                    }
                    else{
                        move(-1.0 * speed * 0.7);
                    }
                }
            }
        }
        // walk
        else{
            walkAnimation();
            if (sound.isPlaying()){
                sound.stop();
            }
            if (!slowed){
                move(-1 * speed);
            }
            else{
                move(-1.0 * speed * 0.7);
            }
        }
        // slow duration
        if (slowed){
            slowCounter++;
            if (slowCounter == 200){
                slowCounter = 0;
                slowed = false;
            }
        }
        // reached left of the screen game over
        if (getX() <= 120){
            MyWorld mw = (MyWorld)getWorld();
            mw.endGame();
        }
    }
}
