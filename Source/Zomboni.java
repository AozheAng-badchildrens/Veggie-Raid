import greenfoot.*;
import java.util.ArrayList;

/**
 * A car that removes plant upon contact and can be destroyed by spikes
 * 
 * @author (Michael) 
 * @version (June 22, 2022)
 */
public class Zomboni extends Zombie
{
    // associated number - 4
    
    // images
    private static String driveImages[] = new String[10];
    private static String boomImages[] = new String[9];
    private boolean exploded = false;
    private GreenfootSound sound = new GreenfootSound("explosion.mp3");
    /**
     * Constructor for objects of class Zomboni
     */
    public Zomboni()
    {
        speed = 0.35;
        health = 100;
        for (int i=0; i<10; i++){
            driveImages[i] = "zomboni" + String.valueOf(i + 1) + ".png";
        }
        for (int i=0; i<9; i++){
            boomImages[i] = "boom" + String.valueOf(i + 1) + ".png";
        }
        sound.setVolume(35);
    }
    
    /**
     * drive animation
     */
    public void driveAnimation(){
        normalAnimationChange++;
        if (normalAnimationChange == 8){
            setImage(driveImages[normalAnimationCounter++]);
            normalAnimationCounter %= 10;
            normalAnimationChange = 0;
        }
    }
    
    /**
     * explode animation
     */
    public void boomAnimation(){
        eatAnimationChange++;
        if (eatAnimationChange == 8){
            setImage(boomImages[eatAnimationCounter++]);
            eatAnimationChange = 0;
        }
    }
    
    /**
     * damage me by a amount
     * 
     * @param damage - amount of damage
     */
    public void damageMe(double damage){
        health -= damage;
        // if dead explode
        if (health <= 0){
            speed = 0;
            if (!exploded){
                sound.play();
                // remove all plants within 100 pixels
                ArrayList<Plant> plants = (ArrayList<Plant>)getObjectsInRange(110, Plant.class);
                for (Plant p : plants){
                    if (p != null){
                        p.damageMe(10000);
                    }
                }
                exploded = true;
            }
        }
    }
    
    /**
     * Act method
     */
    public void act(){
        // if dead
        if (health <= 0){
            // explode
            boomAnimation();
            speed = 0;
            if (eatAnimationCounter == 9){
                getWorld().removeObject(this);
            }
            return;
        }
        // get an intersecting spike
        Spike s = (Spike)getOneIntersectingObject(Spike.class);
        if (s != null){
            // kill me and the spike
            driveAnimation();
            s.damageMe(10000);
            damageMe(10000);
            return;
        }
        // get a plant
        Plant p = (Plant)getOneIntersectingObject(Plant.class);
        if (p != null){
            // kill the plant
            driveAnimation();
            p.damageMe(10000);
        }
        else{
            driveAnimation();
        }
        // drive forward
        move(-1 * speed);
        if (getX() <= 120){
            MyWorld mw = (MyWorld)getWorld();
            mw.endGame();
        }
    }
}
