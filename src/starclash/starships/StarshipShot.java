package starclash.starships;

import starclash.gui.Drawable;
import starclash.gui.GameInterfaceAdaptor;

/**
 *
 * @author viniciusas
 */
public interface StarshipShot extends Drawable {
    
    public interface EndShotLifeListener {
        
        public void onExit();
        public void onHit();
        
    }
    
    public boolean start(StarshipFactory enemy, EndShotLifeListener endShotLifeListener);
    
    public int getDamage();
    
    public float getX();
    public float getY();
    
    public float getSize();
    
}
