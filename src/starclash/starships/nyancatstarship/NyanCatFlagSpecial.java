package starclash.starships.nyancatstarship;

import starclash.gui.DrawAdaptor;
import starclash.gui.Drawable;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Rectangle;

/**
 *
 * @author samuel
 */
public class NyanCatFlagSpecial implements Drawable{
    
    public static final Image spacialFlagEnemy = new Image("/resources/backgrounds/special.png", new Rectangle(0.05f,0.05f,0.1f,0.08f, Color.TRANSPARENT));
    public static final Image spacialFlag = new Image("/resources/backgrounds/special.png", new Rectangle(0.95f,0.95f,0.1f,0.08f, Color.TRANSPARENT));
    private final boolean isenemy;
    public NyanCatFlagSpecial(boolean isenemy) {
        this.isenemy = isenemy;
    }
    
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        if(isenemy)
             drawAdaptor.drawImage(spacialFlagEnemy);
        else
            drawAdaptor.drawImage(spacialFlag);
    }
    
    
}
