import greenfoot.*;

/**
 * A normal zombie with more health
 * 
 * @author (Michael) 
 * @version June 22, 2022)
 */
public class Cone extends Normal
{
    // associated number - 1
    
    // images
    private static String normalImages[] = new String[7];
    private static String eatImages[] = new String[7];

    /**
     * Constructor for objects of class Cone
     */
    public Cone()
    {
        health = 40;
        damage = 6;
        speed = 0.75;
        for (int i=0; i<7; i++){
            normalImages[i] = "cone" + String.valueOf(i + 1) + ".png";
            eatImages[i] = "coneE" + String.valueOf(i + 1) + ".png";
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
    
    /**
     * Act method same as bucket zombie
     */
    public void act(){
        Plant p = (Plant)getOneIntersectingObject(Plant.class);
        if (p != null){
            if (!p.ignore()){
                if (health > 20){
                    eatAnimation();
                }
                else{
                    super.eatAnimation();
                }
                sound.play();
                if (damageDurationCounter == 0){
                    p.damageMe(damage);
                    sound.play();
                    damageDurationCounter = damageDuration;
                    if (slowed){
                        damageDurationCounter *= 2;   
                    }
                }
                damageDurationCounter--;
            }
            else{
                boolean walk = true;
                for (int i=0; i>=-30; i--){
                    p = (Plant)getOneObjectAtOffset(i, 0, Plant.class);
                    if (p != null && !p.ignore()){
                        if (health > 20){
                            eatAnimation();
                        }
                        else{
                            super.eatAnimation();
                        }
                        if (damageDurationCounter == 0){
                            p.damageMe(damage);
                            sound.play();
                            damageDurationCounter = damageDuration;
                            if (slowed){
                                damageDurationCounter *= 2;   
                            }
                        }
                        damageDurationCounter--;
                        walk = false;
                        break;
                    }
                }
                if (walk){
                    if (sound.isPlaying()){
                        sound.stop();
                    }
                    if (health > 20){
                        walkAnimation();
                    }
                    else{
                        super.walkAnimation();
                    }
                    if (!slowed){
                        move(-1 * speed);
                    }
                    else{
                        move(-1.0 * speed * 0.7);
                    }
                }
            }
        }
        else{
            if (sound.isPlaying()){
                sound.stop();
            }
            if (health > 20){
                walkAnimation();
            }
            else{
                super.walkAnimation();
            }
            if (!slowed){
                move(-1 * speed);
            }
            else{
                move(-1.0 * speed * 0.7);
            }
        }
        if (slowed){
            slowCounter++;
            if (slowCounter == 200){
                slowCounter = 0;
                slowed = false;
            }
        }
        if (getX() <= 120){
            MyWorld mw = (MyWorld)getWorld();
            mw.endGame();
        }
    }
}
