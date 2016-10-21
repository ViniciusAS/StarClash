package starclash.starships.ShipVinicius;

import starclash.gui.DrawAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Point;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Triangle;
import starclash.starships.StarshipDraw;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipDraw implements StarshipDraw {

    private final TheIncredableStarship starship;

    public TheIncredableStarshipDraw(TheIncredableStarship starship) {
        this.starship = starship;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        
        drawAdaptor.drawTriangle(
            new Triangle(
                new Point(
                    starship.getX()+5,
                    starship.getY()
                ),
                new Point(
                    starship.getX()+10,
                    starship.getY()+10
                ),
                new Point(
                    starship.getX(),
                    starship.getY()+10
                ),
                Color.BLUE
            )
        );
        drawAdaptor.drawRectangle(
            new Rectangle(
                starship.getX(),
                starship.getY()+10,
                10, 10,
                Color.BLACK
            )
        );

        
    }

}
