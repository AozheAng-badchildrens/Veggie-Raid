import greenfoot.*;

/**
 * PlantCard abstract superclass
 * 
 * @author (Michael) 
 * @version (June 22, 2022)
 * 
 * dimentions: 71 x 44
 */
public abstract class PlantCard extends SuperSmoothMover
{
    protected boolean isSelected; // is selected or not
    protected boolean removed; // removed or not
    protected String relatedPlant; // string of my related plant
    protected boolean moveable; // move able or not
    
    /**
     * Constructor for objects of class PlantCard
     * 
     * @param moveable - true if moveable and false otherwise
     */
    public PlantCard(boolean moveable)
    {
        isSelected = false;
        removed = false;
        this.moveable = moveable;
    }
    
    /**
     * set removed
     * 
     * @param b - true/false
     */
    public void setRemoved(boolean b){
        removed = b;
    }
    
    /**
     * set selected
     * 
     * @param b - true/false
     */
    public void setSelected(boolean b){
        isSelected = b;
    }
    
    /**
     * Act method
     */
    public void act(){
        if (removed){
            getWorld().removeObject(this);
        }
        MyWorld w = (MyWorld)getWorld();
        // if clicked on me tell the main world I am selected
        if (Greenfoot.mouseClicked(this)){
            isSelected = true;
            w.setisPlantSelected(true);
            w.setCurrentPlant(relatedPlant);
            w.setSelectedCard(this);
            return;
        }
        // if selected
        if (isSelected){
            // set transparency
            getImage().setTransparency(100);
            // if clicked on a place other than grid unselect me
            MouseInfo m = Greenfoot.getMouseInfo();
            if (m != null && m.getButton() == 1){
                int x = m.getX(), y = m.getY();
                if (x <= 200 || x >= 982 || y <= 83 || y >= 546){
                    isSelected = false;
                    w.setisPlantSelected(false);
                }
            }
        }
        else{
            getImage().setTransparency(255);
        }
        // if moveable 
        if (moveable && !removed){
            // move up until there is a card above me
            if (getOneObjectAtOffset(0, -25, PlantCard.class) == null && getY() > 30){
                setLocation(getX(), getY() - 1);
            }
        }
    }
}
