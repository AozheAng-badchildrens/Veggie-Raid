import greenfoot.*;

/**
 * A plant that shoot out peas
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class Peashooter extends Plant
{
    // animation images
    private static String attackImages[] = new String[8];
    private static String normalImages[] = new String[8];

    /**
     * Constructor for objects of class Peashooter
     */
    public Peashooter()
    {
        attackDuration = 120;
        for (int i=0; i<8; i++){
            normalImages[i] = "pea" + String.valueOf(i + 1) + ".png";
            attackImages[i] = "pea" + String.valueOf(i + 1) + "S.png";
        }
    }

    /**
     * shoot a pea
     */
    public void attack(){
        int x = getX(), y = getY();
        getWorld().addObject(new Pea(), x + 35, y - 10);
        sound.play();
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
            attackAnimationCounter %= 8;
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
            normalAnimationCounter %= 8;
            normalAnimationChange = 0;
        }
    }
    
    /**
     * Act method 
     */
    public void act(){
        // if not attacking play normal animation
        if (!checkIfAttack()){
            normalAnimation();
            return;
        }
        // attacking play attack animation
        else{
            if (attackDurationCounter <= 55){
                attackAnimation();
            }
            else{
                normalAnimation();
            }
        }
        // attack
        if (attackDurationCounter == 0){
            attack();
            attackDurationCounter = attackDuration;
        }
        attackDurationCounter--;
    }
}
