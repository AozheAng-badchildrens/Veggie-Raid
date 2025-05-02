import greenfoot.*;
import java.util.ArrayList;

/**
 * A plant that turns peas into fireballs and defrost frost peas
 * 
 * @author (Michael) 
 * @version (June 22, 2022)
 */
public class Torchwood extends Plant
{
    private static String images[] = new String[8];
    private GreenfootSound[] sound = new GreenfootSound[10];
    private int soundCounter = 0;
    /**
     * Constructor for objects of class Torchwood
     */
    public Torchwood()
    {
        health = 100;
        for (int i=0; i<8; i++){
            images[i] = "torch" + String.valueOf(i + 1) + ".png";
        }
        for (int i=0; i<10; i++){
            sound[i] = new GreenfootSound("fireball.mp3");
        }
    }

    public void attack(){
        
    }
    
    public boolean checkIfAttack(){
        return false;
    }
    
    public void animation(){
        normalAnimationChange++;
        if (normalAnimationChange == 8){
            setImage(images[normalAnimationCounter++]);
            normalAnimationCounter %= 8;
            normalAnimationChange = 0;
        }
    }
    
    /**
     * Act method
     */
    public void act(){
        animation();
        // get all peas touching me and set them on fire
        ArrayList<Pea> peas = (ArrayList<Pea>)getIntersectingObjects(Pea.class);
        for (Pea p : peas){
            if (p != null && !p.isBurnt()){
                p.burn();
                sound[soundCounter].setVolume(30);
                soundCounter %= 10;
                sound[soundCounter++].play();
                soundCounter %= 10;
            }
        }
        // get all snowpeas touching me and defrost them
        ArrayList<SnowPea> snowpeas = (ArrayList<SnowPea>)getIntersectingObjects(SnowPea.class);
        for (SnowPea p : snowpeas){
            if (p != null && !p.isBurnt()){
                p.burn();
                sound[soundCounter].setVolume(30);
                soundCounter %= 10;
                sound[soundCounter++].play();
                soundCounter %= 10;
            }
        }
    }
}
