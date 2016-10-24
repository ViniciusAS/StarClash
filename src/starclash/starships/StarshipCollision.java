package starclash.starships;

/**
 *
 * @author viniciusas
 */
public interface StarshipCollision {
    
    public boolean wallCollision();
    public boolean shotCollision(StarshipShot starshipShot);
    
    
}
