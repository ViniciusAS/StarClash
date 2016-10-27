package starclash.starships.o_rangestarship;

import starclash.gui.components.Color;
import starclash.gui.components.Component;
import starclash.gui.components.Image;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;

/**
 *
 * @author Vinicius Santos
 */
public class ORangeStarshipComponents implements StarshipComponents{

    private final float width  = 0.08f;
    private final float height = 0.095f;
    
    private final Image image  = new Image("/resources/nyancat/o-range.png", new Rectangle(
            0, 0,
            width, height,
            Color.TRANSPARENT
    ));
    
    private final StarshipFactory starship;
    
    public ORangeStarshipComponents(StarshipFactory starship) {
        this.starship = starship;
        if ( starship.isEnemy() ){
            image.getRectangle().setHeight( -image.getRectangle().getHeight() );
        }
    }

    @Override
    public float getWidth() {
       return width;
    }

    @Override
    public float getHeigth() {
        return height;
    }


    @Override
    public Component[] getComponents() {
        image.getRectangle().setX(starship.getX());
        image.getRectangle().setY(starship.getY());
        return new Component[] { image };
    }
    

    
}
