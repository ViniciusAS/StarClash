package starclash.starships;

import starclash.gui.Drawable;

/**
 *
 * @author viniciusas
 */
public interface StarshipShot extends Drawable {
    
    public void start();
    
    public float getShotPosX();
    
    public float getShotPosY();
    
}
