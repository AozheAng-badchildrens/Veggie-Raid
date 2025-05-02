import greenfoot.*;

/**
 * A pea that slows down zombie
 * 
 * @author (Daniel Chung) 
 * @version (June 22, 2022)
 */
public class SnowPea extends Projectile
{
    private boolean slow = true; // if defrosted or not
    private boolean burnt = false; // burnt or not
    
    public SnowPea() {
        speed = 2.5;
        damage = 2;
    }
    
    /**
     * defrost me
     */
    public void burn(){
        setImage("peaBull.png");
        slow = false;
        burnt = true;
        damage = 3;
    }
    
    /**
     * true if defrosted and false otherwise
     * 
     * @param burnt - true/false
     */
    public boolean isBurnt(){
        return burnt;
    }
    
    /**
     * Act method
     */
    public void act()
    {
        move(speed * direction); // move forward
        // if hit a zombie damage it and apply slow
        Zombie target = (Zombie)getOneIntersectingObject(Zombie.class);
        if (target != null){
            target.damageMe(damage);
            sound.play();
            target.setSlowed(slow);
            getWorld().removeObject(this);
        }
        else if (getX() < 10 || getX() > 950){
            getWorld().removeObject(this);
        }
    }
}