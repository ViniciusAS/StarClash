package starclash.starships;

/**
 *
 * @author viniciusas
 */
public interface StarshipFactory {
    
    public String getName();
    
    public StarshipFactory getNext();
    
    public StarshipDraw newStarshipDraw();
    
    public StarshipCollision newStarshipCollision();
    
    public StarshipShot newShot();
    public StarshipShot newShot(float x, float y);
    
    public interface DieListener {
        public void dead();
    }
    
    public void setDieListener(DieListener dieListener);
    
    /** proccess taken damage.
     *
     * call die listener when dead
     * 
     * @param shot
     * @return if enemy is dead
     */
    public boolean takeDamage(StarshipShot shot);
    
    public void doSpecial();
    public void doSpecial(float x, float y);
    
    public boolean isEnemy();
    
    public float getShipSpeed();
    public void setShipSpeed(float speed);
    
    public float getWidth();
    public float getHeight();
    
    public float getX();
    public float getY();
    
    public void setX(float x);
    public void setY(float y);
    
}
