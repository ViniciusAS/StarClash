package starclash.starships;

import starclash.gui.Drawable;
import starclash.gui.GameInterfaceAdaptor;

/**
 *
 * @author viniciusas
 */
public interface StarshipShot extends Drawable {
    
    public boolean start(GameInterfaceAdaptor gui,StarshipFactory enemy);
    
    public int getDamage();
    
    public float getX();
    public float getY();
}
