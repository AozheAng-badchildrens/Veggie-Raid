import greenfoot.*;

/**
 * Mine Card 
 * 
 * @author (Michael Chen) 
 * @version (June 22, 2022)
 */
public class MineCard extends PlantCard
{
    // associated number - 5
    private int x;

    /**
     * Constructor for objects of class MineCard
     */
    public MineCard(boolean moveable)
    {
        super(moveable);
        relatedPlant = "Mine";
    }

    
}
