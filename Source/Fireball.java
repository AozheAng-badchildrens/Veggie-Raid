import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A fireball that leaves fire on the ground
 * 
 * @author (Daniel Chung) 
 * @version (June 22, 2022)
 */
public class Fireball extends Projectile
{
    private int c = 0, change = 0; // animation stuff
    
    public Fireball() {
        speed = 3.5;
        direction = 1;
        damage = 2;
    }

    /**
     * Act method
     */
    public void act()
    {
        // play animation
        change++;
        if (change == 8){
            c++;
            c %= 2;
            if (c == 0){
                setImage("fireBull1.png");
            }
            else{
                setImage("fireBull2.png");
            }
            change = 0;
        }
        // move forward
        move(speed * direction);
        // if hit zombie
        Zombie z = (Zombie)getOneIntersectingObject(Zombie.class);
        if(z != null){
            // add a fire to the place of contact
            int x = z.getX();
            int y = z.getY(); 
            getWorld().addObject(new Fire(), x, y);
            z.damageMe(damage); // damage the zombie
            sound.play();
            // remove me
            getWorld().removeObject(this);
        }
        // reached end of screen remove me
        else if (getX() < 10 || getX() > 950){
            getWorld().removeObject(this);
        }
    }
}