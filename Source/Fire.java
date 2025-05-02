import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Damages zombies that intersects with it
 * 
 * @author (Daniel Chung) 
 * @version (June 22, 2022)
 */
public class Fire extends Actor
{
    private int c; // damage counter
    private double dmg; // damage 
    // animation stuff
    private static String images[] = new String[7];
    private int counter = 0, change = 0;
    private GreenfootSound sound;
    
    /**
     * Constructor of Fire
     */
    public Fire() {
        // initializes counter, damage, animation images and sound
        c = -1;
        dmg = 0.75;
        for (int i=0; i<7; i++){
            images[i] = "fire" + String.valueOf(i + 1) + ".png";
        }
        sound = new GreenfootSound("fireball.mp3");
        sound.setVolume(30);
    }

    /**
     * Plays animation
     */
    public void animation(){    
         change++;
         if (change == 8){
             setImage(images[counter++]);
             counter %= 7;
             change = 0;
         }
    }
    
    /**
     * Act method
     */
    public void act()
    {
        animation(); // play animation
        c++; // increment counter
        // get all zombies and damage them but also removes slow
        if(isTouching(Zombie.class)) {
            if(c % 30 == 0) {
                ArrayList<Zombie> list = (ArrayList<Zombie>) getIntersectingObjects(Zombie.class);
                for(Zombie z: list) {
                    if (z != null){
                        z.damageMe(dmg);
                        sound.play();
                        z.setSlowed(false);
                    }
                }
            }
        }
        if(c == 150) {
            getWorld().removeObject(this);
        }
    }
}