import greenfoot.*;

/**
 * A plant that shoots out boomerangs
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class Bloomerrang extends Plant
{
    // animation images
    private static String attackImages[] = new String[8];
    private static String normalImages[] = new String[16];
    /**
     * Constructor for objects of class Peashooter
     */
    public Bloomerrang()
    {
        attackDuration = 160;
        // initalize images
        for (int i=0; i<16; i++){
            normalImages[i] = "Bloomerrang" + String.valueOf(i + 1) + ".png";
        }
        for (int i=0; i<4; i++){
            attackImages[i] = "BloomerrangD" + String.valueOf(i + 1) + ".png";
        }
    }

    /**
     * Shoot out a boomerang
     */
    public void attack(){
        int x = getX(), y = getY();
        getWorld().addObject(new Boomerang(), x + 35, y - 10);
    }
    
    /**
     * If there is a zombie within 6 blocks return true and false otherwise
     * 
     * @return - true/false if I should attack
     */
    public boolean checkIfAttack(){
        for (int i=0; i<=444; i+=5){
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
            attackAnimationCounter %= 4;
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
            normalAnimationCounter %= 16;
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
        // if attacking play attack animation
        else{
            if (attackDurationCounter <= 57){
                attackAnimation();
            }
            else{
                normalAnimation();
            }
        }
        // if ready to shoot 
        if (attackDurationCounter == 0){
            attack();
            sound.play();
            attackDurationCounter = attackDuration;
        }
        attackDurationCounter--;
    }
}
