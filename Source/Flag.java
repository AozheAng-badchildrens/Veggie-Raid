import greenfoot.*;

/**
 * Flag zombie spawns at the start of a wave
 * Act method is the same as Zombie class
 * 
 * @author (Michael) 
 * @version (June 22, 2022)
 */
public class Flag extends Zombie
{
    // animation images
    private static String normalImages[] = new String[7];
    private static String eatImages[] = new String[7];
    /**
     * Constructor for objects of class Flag
     */
    public Flag()
    {
        health = 20;
        damage = 6;
        for (int i=0; i<7; i++){
            normalImages[i] = "flag" + String.valueOf(i + 1) + ".png";
            eatImages[i] = "flagE" + String.valueOf(i + 1) + ".png";
        }
    }

    /**
     * eat animation
     */
    public void eatAnimation(){
        normalAnimationCounter = 0;
        eatAnimationChange++;
        if (eatAnimationChange == 8){
            setImage(eatImages[eatAnimationCounter++]);
            eatAnimationCounter %= 7;
            eatAnimationChange = 0;
        }
    }
    
    /**
     * walk animation
     */
    public void walkAnimation(){    
        eatAnimationCounter = 0;
        normalAnimationChange++;
        if (normalAnimationChange == 8){
            setImage(normalImages[normalAnimationCounter++]);
            normalAnimationCounter %= 7;
            normalAnimationChange = 0;
        }
    }
}
