import greenfoot.*;

/**
 * A mine
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class Mine extends Plant
{
    private int sleepCounter = 0, explosionCounter = 100; // activation counter 
    private boolean awake = false; // if activated or not
    // images
    private static String images[] = new String[7];
    private int imageCounter = 0, imageChange = 0;
    // sound
    private GreenfootSound sound = new GreenfootSound("explosion.mp3");
    /**
     * Constructor for objects of class Mine
     */
    public Mine()
    {
        health = 70;
        for (int i=0; i<7; i++){
            images[i] = "mine" + String.valueOf(i + 2) + ".png";
        }
        sound.setVolume(35);
    }

    /**
     * does not attack
     * 
     * @return - false
     */
    public boolean checkIfAttack(){
        return false;
    }
    
    public void attack(){
        
    }
    
    /**
     * activation animation
     */
    public void popOutAnimation(){
        imageChange++;
        if (imageChange == 8){
            setImage(images[imageCounter++]);
            imageCounter %= 7;
            imageChange = 0;
        }
    }
    
    public boolean ignore(){
        if (explosionCounter < 100){
            return true;
        }
        return false;
    }
    /**
     * act method
     */
    public void act(){
        // if exploded play set image to explosion
        if (explosionCounter < 100){
            setImage("explosion1.png");
            getImage().setTransparency(explosionCounter + 100);
            explosionCounter--;
            // remove me
            if (explosionCounter == 0){
                damageMe(1000);
            }
            return;
        }
        // if not activated increment activation counter
        if (!awake){
            sleepCounter++;
        }
        // activated
        else{
            // play activation animation
            imageChange++;
            if (imageChange == 20){
                if (imageCounter == 0){
                    setImage("mine7.png");
                }
                else{
                    setImage("mine6.png");
                }
                imageCounter++;
                imageCounter %= 2;
                imageChange = 0;
            }
            // if not exploded and zombies touching me
            for (int x=-20; x<=50; x+=4){
                if (getObjectsAtOffset(x, 0, Zombie.class).size() > 0 && explosionCounter == 100){
                    // find all zombies in the grid to my left and right and remove them
                    for (int i=-80; i<=80; i+=4){
                        getWorld().removeObjects(getObjectsAtOffset(i, 0, Zombie.class));
                    }
                    explosionCounter--;
                    sound.play();
                    Grid g = (Grid)getOneIntersectingObject(Grid.class);
                    if (g != null){
                        g.setOccupied(false);
                    }
                    break;
                }
            }
        }
        // activate
        if (sleepCounter == 600){
            awake = true;
            sleepCounter = 601;
            imageCounter = 0;
            imageChange = 0;
        }
        if (sleepCounter >= 546 && !awake){
            popOutAnimation();
        }
    }
}
