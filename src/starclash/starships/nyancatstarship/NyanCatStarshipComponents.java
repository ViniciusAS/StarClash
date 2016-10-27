package starclash.starships.nyancatstarship;

import starclash.gui.components.Color;
import starclash.gui.components.Component;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipComponents implements StarshipComponents{
    
    private final Image image = new Image("/resources/nyancat/nyanCatShip.png", new Rectangle(0, 0, WIDTH, HEIGTH, Color.TRANSPARENT));
    private static final float WIDTH = 0.08f;
    private static final float HEIGTH = 0.08f;
    private final StarshipFactory starship;

    public NyanCatStarshipComponents(StarshipFactory starship) {
        this.starship = starship;
        if (starship.isEnemy())image.getRectangle().setHeight( -image.getRectangle().getHeight() ); 
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
        image.getRectangle().setX(starship.getX());
        image.getRectangle().setY(starship.getY());
        return new Component[] { image };
    }   
    
   
    
}
