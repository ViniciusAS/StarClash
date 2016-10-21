package starclash.starships;

/**
 *
 * @author viniciusas
 */
public interface StarshipFactory {
    
    public StarshipDraw newStarshipDraw();
    
    public StarshipCollision newStarshipCollision();
    
    public StarshipShot newShot();
    
    
    public void doSpecial();    
    
    public float getShipSpeed();
    public void setShipSpeed(float speed);
    
    public float getX();
    public float getY();
    
    public void setX(float x);
    public void setY(float y);
    
}
