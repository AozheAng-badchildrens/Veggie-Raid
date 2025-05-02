import greenfoot.*;

/**
 * A plant with a lot of hp that acts like a wall
 * 
 * @author (Calista Kurniawan) 
 * @version (June 22, 2022)
 */
public class Walnut extends Plant
{
    // animation stuff
    private static String fullHPImages[] = new String[5];
    private static String halfHPImages[] = new String[5];
    private static String lowHPImages[] = new String[5];
    private int fullHPCounter = 0, halfHPCounter = 0, lowHPCounter = 0;
    private int fullHPChange = 0, halfHPChange = 0, lowHPChange = 0;
    /**
     * Constructor for objects of class Walnut
     */
    public Walnut()
    {
        health = 400;
        for (int i=0; i<5; i++){
            fullHPImages[i] = "Wallnut" + String.valueOf(i + 1) + ".png";
            halfHPImages[i] = "WallNutDamaged" + String.valueOf(i + 1) + ".png";
            lowHPImages[i] = "WallNutDying" + String.valueOf(i + 1) + ".png";
        }
    }

    /**
     * does not attack
     * 
     * @return - false
     */
    public boolean checkIfAttack(){
        return false;
    }
    
    /**
     * crying animation
     */
    public void lowHPAnimation(){
        lowHPChange++;
        if (lowHPChange == 8){
            setImage(lowHPImages[lowHPCounter++]);
            lowHPCounter %= 5;
            lowHPChange = 0;
        }
    }
    
    /**
     * full hp animation
     */
    public void fullHPAnimation(){
        fullHPChange++;
        if (fullHPChange == 8){
            setImage(fullHPImages[fullHPCounter++]);
            fullHPCounter %= 5;
            fullHPChange = 0;
        }
    }
    
    /**
     * half hp animation
     */
    public void halfHPAnimation(){
        halfHPChange++;
        if (halfHPChange == 8){
            setImage(halfHPImages[halfHPCounter++]);
            halfHPCounter %= 5;
            halfHPChange = 0;
        }
    }
    
    public void attack(){
        
    }
    
    /**
     * Act method basically just play animation
     */
    public void act(){
        if (health >= 300){
            fullHPAnimation();
        }
        else if (health >= 100){
            halfHPAnimation();
        }
        else{
            lowHPAnimation();
        }
    }
}
