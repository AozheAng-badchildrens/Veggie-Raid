import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * A projectile that shoots forward and comes backward
 * 
 * @author (Daniel & Michael) 
 * @version (June 22, 2022)
 */
public class Boomerang extends Projectile
{
    private int motionCounter;
    private int damageCounter;
    private GreenfootSound sounds[] = new GreenfootSound[20];
    private int soundCounter = 0;
    
    // speed paramter for boomerang is useless for boomerang objects
    /**
     * Constructor of boomerang
     */
    public Boomerang() {
        direction = 1;
        damage = 3;
        motionCounter = -1;
        damageCounter = 0;
        for (int i=0; i<20; i++){
            sounds[i] = new GreenfootSound("bullet hitting.mp3");
            sounds[i].setVolume(40);
        }
    }
    
    /**
     * act method
     */
    public void act()
    {
        attack();
        motion();
    }
    
    /**
     * projectile motion
     */
    public void motion() {
        //moving back and forth
        motionCounter++;
        // calculate speed
        speed = motionCounter * (motionCounter - 150) / 375 + 15;
        move(speed * direction);
        if(motionCounter == 75) {
            direction *= -1;
        } else if(motionCounter == 150) {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Attack method
     */
    public void attack() {
        // if hit a zombie damage it and add me to its queue of boomerangs
        Zombie z = (Zombie)getOneIntersectingObject(Zombie.class);
        if(z != null) {
            // forward direction
            if (direction == 1 && !z.isAdded(this)){
                z.damageMe(damage);
                sounds[soundCounter++].play();
                soundCounter %= 20;
                z.addBoomerang(this);
            }
            // coming backwards
            else if (direction == -1){
                if (z.isAdded(this)){
                    z.damageMe(damage);
                    sounds[soundCounter++].play();
                    soundCounter %= 20;
                    z.popBoomerang();
                }
            }
        }
    }
}