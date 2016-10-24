package starclash.starships;

/**
 *
 * @author viniciusas
 */
public interface StarshipCollision {
    
    public float wallCollisionVerticalFilter(float y);
    
    public float wallCollisionHorizontalFilter(float x);
    
    public boolean shotCollision(StarshipShot starshipShot, StarshipFactory enemyShip, StarshipComponents components);
    
    
}
