package starclash.starships.nyancatstarship;

import starclash.gui.components.Color;
import starclash.gui.components.Component;
import starclash.gui.components.Image;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipComponents;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipComponents implements StarshipComponents{
    
    private final Image image;
    private static final float WIDTH = 0.0463f;
    private static final float HEIGTH = 0.0463f;

    public NyanCatStarshipComponents() {
        this.image = new Image("/nyanCatShip.png", new Rectangle(0f, 0.03f, WIDTH, HEIGTH, Color.BLUE));
    }

    @Override
    public float getWidth() {
        return WIDTH; 
    }

    @Override
    public float getHeigth() {
        return HEIGTH;
    }

    @Override
    public Component[] getComponents() {
        return new Component[]{
            new Image(image.getFilename(), image.getRectangle())
        };
    }
    
}
