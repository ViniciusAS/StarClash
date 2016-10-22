
package starclash.starships.ShipVinicius;

import starclash.gui.components.Color;
import starclash.gui.components.Component;
import starclash.gui.components.Point;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Triangle;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;

/**
 *
 * @author samuel
 */
public class TheIncreadableStarshipComponents implements StarshipComponents{

    private final Triangle triangle;
    private final Rectangle retangle;
    private final StarshipFactory starship;
    
    public TheIncreadableStarshipComponents(StarshipFactory starship) {
        this.starship = starship;

        triangle = new Triangle(
                   new Point(5,0),
                   new Point(10,10),
                   new Point(0,10),
                   Color.BLUE
        );

        retangle = new Rectangle(0,10,10,10,Color.BLACK);
        
    }
   

    @Override
    public float getWidth() {
       return 10;
    }

    @Override
    public float getHeigth() {
        return 20;
    }


    @Override
    public Component[] getComponents() {
         return new Component[]{
            new Triangle(
                new Point(starship.getX()+triangle.getP0().getX(),starship.getY()+triangle.getP0().getY()), 
                new Point(starship.getX()+triangle.getP1().getX(),starship.getY()+triangle.getP1().getY()),
                new Point(starship.getX()+triangle.getP2().getX(),starship.getY()+triangle.getP2().getY()),
                triangle.getColor()
            ),
            
            new Rectangle(starship.getX()+retangle.getX(),
                          starship.getY()+retangle.getY(),
                          retangle.getWidht(),
                          retangle.getHeight(),
                          retangle.getColor()
            )
        };
    }
    

    
}
