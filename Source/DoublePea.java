import greenfoot.*;

/**
 * A plant that shoots 2 peas at a time
 * 
 * @author (Cheuk W Kwong) 
 * @version (June 22, 2022)
 */
public class DoublePea extends Plant
{
    private int shootOneMorePea = 0; // shoot another pea duration
    // animation images
    private static String normalImages[] = new String[5];
    private static String attackImages[] = new String[5];
    /**
     * Constructor for objects of class DoubleShooter
     */
    public DoublePea()
    {
        attackDuration = 150;
        // initialize images
        for (int i=0; i<5; i++){
            attackImages[i] = "DoublePeaS" + String.valueOf(i + 1) + ".png";
            normalImages[i] = "DoublePea" + String.valueOf(i + 1) + ".png";
        }
    }

    /**
     * Add a pea and shoot another pea 25 acts later
     */
    public void attack(){
        int x = getX(), y = getY();
        getWorld().addObject(new Pea(), x + 35, y - 10);
        shootOneMorePea = 25;
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
            setImage(attackImages[attackAnimationCounter++]);
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
            if (attackDurationCounter <= 35){
                attackAnimation();
            }
            else{
                normalAnimation();
            }
        }
        // attack
        if (attackDurationCounter == 0){
            attack();
            sound.play();
            attackDurationCounter = attackDuration;
        }
        // shoot another pea
        if (shootOneMorePea > 0){
            shootOneMorePea--;
            if (shootOneMorePea == 0){
                getWorld().addObject(new Pea(), getX() + 35, getY() - 10);
            }
        }
        attackDurationCounter--;
    }
}
