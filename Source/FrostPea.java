import greenfoot.*;

/**
 * A plant that shoot out snowpeas
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class FrostPea extends Plant
{
    // animation images
    private static String attackImages[] = new String[5];
    private static String normalImages[] = new String[5];
    /**
     * Constructor for objects of class Peashooter
     */
    public FrostPea()
    {
        attackDuration = 150;
        for (int i=0; i<5; i++){
            normalImages[i] = "FrostPea" + String.valueOf(i + 1) + ".png";
            attackImages[i] = "FrostPeaS" + String.valueOf(i + 1) + ".png";
        }
    }

    /**
     * shoot a snowpea
     */
    public void attack(){
        int x = getX(), y = getY();
        getWorld().addObject(new SnowPea(), x + 35, y - 10);
    }
    
    /**
     * Check if any zombie infront of me
     * 
     * @return - true if there are zombies infront of me and false otherwise
     */
    public boolean checkIfAttack(){
        for (int i=0; i<=900; i+=5){
            if (getOneObjectAtOffset(i, 0, Zombie.class) != null){
                return true;
            }
        }
        return false;    
    }
    
    /**
     * attack animation
     */
    public void attackAnimation(){
        normalAnimationCounter = 0;
        attackAnimationChange++;
        if (attackAnimationChange == 7){
            setImage(attackImages[attackAnimationCounter]);
            attackAnimationCounter += 1;
            attackAnimationCounter %= 5;
            attackAnimationChange = 0;
        }
    }
    
    /**
     * normal animation
     */
    public void normalAnimation(){
        attackAnimationCounter = 0;
        normalAnimationChange++;
        if (normalAnimationChange == 8){
            setImage(normalImages[normalAnimationCounter]);
            normalAnimationCounter += 1;
            normalAnimationCounter %= 5;
            normalAnimationChange = 0;
        }
    }
    
    /**
     * act method almost same as peashoter
     */
    public void act(){
        if (!checkIfAttack()){
            normalAnimation();
            return;
        }
        else{
            if (attackDurationCounter <= 35){
                attackAnimation();
            }
            else{
                normalAnimation();
            }
        }
        if (attackDurationCounter == 0){
            attack();
            sound.play();
            attackDurationCounter = attackDuration;
        }
        attackDurationCounter--;
    }
}
