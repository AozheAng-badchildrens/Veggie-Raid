import greenfoot.*;

/**
 * A pea
 * 
 * @author (Daniel Chung & Michael Chen) 
 * @version (June 22, 2022)
 */
public class Pea extends Projectile
{
    private boolean burnt = false; // is burnt
    /**
     * Constructor for objects of class Pea
     */
    public Pea()
    {
        damage = 3;
        speed = 2.5;
    }
    
    /**
     * return true if burnt and false otherwise
     * 
     * @return - true/false
     */
    public boolean isBurnt(){
        return burnt;    
    }
    
    /**
     * Turn me into a fireball
     */
    public void burn(){
        burnt = true;
        getWorld().addObject(new Fireball(), getX(), getY());
        getWorld().removeObject(this);
    }
}
