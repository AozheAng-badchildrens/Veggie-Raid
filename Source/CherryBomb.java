import greenfoot.*;

/**
 * A plant that removes zombies within 3x3 grid range
 * 
 * @author (Aarav Shah) 
 * @version (June 22, 2022)
 */
public class CherryBomb extends Plant
{
    // animation images
    private String images[] = new String[6];
    private boolean exploded = false; // exploded or not
    // sound
    private GreenfootSound sound;
    /**
     * Constructor for objects of class CherryBomb
     */
    public CherryBomb()
    {
        // initialize images
        for (int i=0; i<6; i++){
            images[i] = "cherry" + String.valueOf(i + 1) + ".png";
        }
        sound = new GreenfootSound("explosion.mp3");
        sound.setVolume(35);
    }

    /**
     * the plant doens't attack it explodes upon placement
     * 
     * @return - false
     */
    public boolean checkIfAttack(){
        return false;
    }
    
    public void attack(){
        
    }
    
    public void damageMe(double damage){
        
    }
    
    /**
     * Act method
     */
    public void act(){
        // exploded
        if (health <= 0 || exploded){
            setImage("boomboom1.png");
            attackAnimationChange++;
            if (attackAnimationChange == 30){
                getWorld().removeObject(this);
            }
            return;
        }
        normalAnimationChange++;
        if (normalAnimationChange == 8){
            setImage(images[normalAnimationCounter++]);
            normalAnimationChange = 0;
        }
        if (normalAnimationCounter == 6){
            // remove all zombies within 170 pixels of radius
            getWorld().removeObjects(getObjectsInRange(170, Zombie.class));
            sound.play();
            exploded = true;
            Grid g = (Grid)getOneIntersectingObject(Grid.class);
            if (g != null){
                g.setOccupied(false);
                g.setPlant(null);
            }
        }
    }
}
