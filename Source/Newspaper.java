import greenfoot.*;

/**
 * A zombie that can rage 
 * 
 * @author (Michael) 
 * @version (June 22, 2022)
 */
public class Newspaper extends Zombie
{
    // associated number - 3
    
    // animation stuff
    private static String normalImages1[] = new String[7];
    private static String eatImages1[] = new String[7];
    private static String normalImages2[] = new String[7];
    private int rageWalkCounter = 0, rageWalkChange = 0;
    
    /**
     * Constructor for objects of class Newspaper
     */
    public Newspaper()
    {
        health = 50;
        for (int i=0; i<7; i++){
            normalImages1[i] = "NewsPaperZomb" + String.valueOf(i + 1) + ".png";
            eatImages1[i] = "NPZBasicEat" + String.valueOf(i + 1) + ".png";
            normalImages2[i] = "NoNewsPaper" + String.valueOf(i + 1) + ".png";
        }
    }

    /**
     * eat animation 
     */
    public void eatAnimation1(){
        normalAnimationCounter = 0;
        eatAnimationChange++;
        if (eatAnimationChange == 8){
            setImage(eatImages1[eatAnimationCounter++]);
            eatAnimationCounter %= 7;
            eatAnimationChange = 0;
        }
    }
    
    /**
     * regular walk animation
     */
    public void walkAnimation1(){
        eatAnimationCounter = 0;
        normalAnimationChange++;
        if (normalAnimationChange == 8){
            setImage(normalImages1[normalAnimationCounter++]);
            normalAnimationCounter %= 7;
            normalAnimationChange = 0;
        }
    }
    
    /**
     * rage walk animation
     */
    public void walkAnimation2(){
        rageWalkChange++;
        if (rageWalkChange == 8){
            setImage(normalImages2[rageWalkCounter++]);
            rageWalkCounter %= 7;
            rageWalkChange = 0;
        }
    }
    
    /**
     * Act method similar to normal zombie except added rage 
     */
    public void act(){
        Plant p = (Plant)getOneIntersectingObject(Plant.class);
        if (p != null){
            if (!p.ignore()){
                // not rage 
                if (health > 25){
                    eatAnimation1();
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
                // rage eat increase damage and frequency of damage
                else{
                    walkAnimation2();
                    if (damageDurationCounter == 0){
                        p.damageMe(damage + 2);
                        sound.play();
                        damageDurationCounter = damageDuration / 2;
                        if (slowed){
                            damageDurationCounter *= 2;
                        }
                    }
                    damageDurationCounter--;
                }
            }
            else{
                boolean walk = true;
                for (int i=0; i>=-30; i--){
                    p = (Plant)getOneObjectAtOffset(i, 0, Plant.class);
                    if (p != null && !p.ignore()){
                        if (health > 25){
                            eatAnimation1();
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
                            walkAnimation2();
                            if (damageDurationCounter == 0){
                                p.damageMe(damage + 2);
                                sound.play();
                                damageDurationCounter = damageDuration / 2;
                                if (slowed){
                                    damageDurationCounter *= 2;
                                }
                            }
                            damageDurationCounter--;
                        }
                        walk = false;
                        break;
                    }
                }
                if (walk){
                    if (sound.isPlaying()){
                        sound.stop();
                    }
                    if (health > 25){
                        walkAnimation1();
                        if (!slowed){
                            move(-1 * speed);
                        }
                        else{
                            move(-1.0 * speed * 0.7);
                        }
                    }
                    else{
                        speed = 1.2;
                        walkAnimation2();
                        if (!slowed){
                            move(-1 * speed);
                        }
                        else{
                            move(-1.0 * speed * 0.7);
                        }
                    }
                }
            }
        }
        else{
            if (sound.isPlaying()){
                sound.stop();
            }
            if (health > 25){
                walkAnimation1();
                if (!slowed){
                    move(-1 * speed);
                }
                else{
                    move(-1.0 * speed * 0.7);
                }
            }
            else{
                speed = 1.2;
                walkAnimation2();
                if (!slowed){
                    move(-1 * speed);
                }
                else{
                    move(-1.0 * speed * 0.7);
                }
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
