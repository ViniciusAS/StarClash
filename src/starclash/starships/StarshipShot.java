package starclash.starships;

import starclash.gui.Drawable;

/**
 *
 * @author viniciusas
 */
public interface StarshipShot extends Drawable {
    
    public void start(StarshipFactory enemy);
    
    public float getShotPosX();
    
    public float getShotPosY();
    
}
