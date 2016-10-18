package starclash.starships;

/**
 *
 * @author viniciusas
 */
public interface StarshipFactory {
    
    public void newStarshipDraw();
    
    public void newShot();
    
    public void newSpecial();
    
    public float getShipSpeed();
    
}
