package starclash.starships;

import starclash.gui.Drawable;
import starclash.gui.GameInterfaceAdaptor;

/**
 *
 * @author viniciusas
 */
public interface StarshipShot extends Drawable {
    
    public void start(GameInterfaceAdaptor gui);
    
    public int getDamage();
    
    public float getX();
    public float getY();
    
}
