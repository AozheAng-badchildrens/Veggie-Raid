import greenfoot.*;

 /** Abstract Projectile superclass 
 * 
 * @author (Michael) 
 * @version (June 22, 2022)
 */
public abstract class Projectile extends SuperSmoothMover
{
    protected double speed; // speed
    protected int direction; // direction
    protected double damage; // damage
    
    // sound
    protected GreenfootSound sound;
    /**
     * 
     */
    public Projectile()
    {
        speed = 2.5;
        // forward direction
        direction = 1;
        sound = new GreenfootSound("bullet hitting.mp3");
        sound.setVolume(40);
    }
    
    /**
     * Act method
     */
    public void act(){
        // move forward
        move(speed * direction);
        // get a intersecting zombie and damage it
        Zombie z = (Zombie)getOneIntersectingObject(Zombie.class);
        if (z != null){
            z.damageMe(damage);
            sound.play();
            getWorld().removeObject(this);
        }
        // reach end of screen remove me
        else if (getX() < 10 || getX() > 950){
            getWorld().removeObject(this);
        }
    }
}
