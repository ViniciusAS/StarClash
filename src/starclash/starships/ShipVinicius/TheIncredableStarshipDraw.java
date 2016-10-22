package starclash.starships.ShipVinicius;

import starclash.gui.DrawAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Point;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Triangle;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipDraw implements StarshipDraw {

    private final TheIncredableStarship starship;
    private final  StarshipComponents components;

    public TheIncredableStarshipDraw(TheIncredableStarship starship, StarshipComponents components) {
        this.components = components;
        this.starship = starship;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        
        
    }

}
