import greenfoot.*;

/**
 * A shovel that removes plant
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class Shovel extends Actor  
{
    private boolean selected; // selected or not
    
    public Shovel()
    {
        // initially not selected
        selected = false;
    }

    /**
     * Act method tells the world if I am selected or not
     */
    public void act(){
        MyWorld w = (MyWorld)getWorld();
        if (w.getShovelSelected() != selected){
            selected = w.getShovelSelected();
            return;
        }
        if (Greenfoot.mouseClicked(this)){
            selected = !selected;
            w.setShovelSelected(selected);
            return;
        }
        if (selected){
            getImage().setTransparency(100);
            MouseInfo m = Greenfoot.getMouseInfo();
            if (m != null && m.getButton() == 1){
                int x = m.getX(), y = m.getY();
                if (x <= 200 || x >= 982 || y <= 83 || y >= 546 && !(x >= 912 && x <= 985 && y >= 530)){
                    selected = false;
                    w.setShovelSelected(false);
                }
            }
        }
        else{
            getImage().setTransparency(254);
        }
    }
}
