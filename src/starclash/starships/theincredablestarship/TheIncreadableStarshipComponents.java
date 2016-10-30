package starclash.starships.theincredablestarship;

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
            new Point( 0.02f, 0f ),
            new Point(    0f, 0.03f ),
            new Point( 0.04f, 0.03f ),
            Color.BLUE
        );

        retangle = new Rectangle(
                0f, 0.03f,
             0.04f, 0.03f,
            new Color(0.1f, 0.1f, 0.1f, 1)
        );
        
    }
   

    @Override
    public float getWidth() {
       return 0.04f;
    }

    @Override
    public float getHeigth() {
        return 0.06f;
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
                          retangle.getWidth(),
                          retangle.getHeight(),
                          retangle.getColor()
            )
        };
    }
    

    
}
