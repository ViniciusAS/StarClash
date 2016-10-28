package starclash.starships.nyancatstarship;

import starclash.gui.DrawAdaptor;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Component;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipDraw implements StarshipDraw{

    private final StarshipComponents components;
    private final StarshipFactory starship;
    private final StarshipCollision collision;
    private NyanCatStarshipRainbow rainbow;
        
    public NyanCatStarshipDraw(NyanCatStarship starship, 
                                StarshipComponents components, 
                                StarshipCollision collision) {
        this.components = components;
        this.starship = starship;
        this.collision = collision;
        System.out.println(starship.getX()+" - "+starship.getY());
        
    }
    
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        rainbow = new NyanCatStarshipRainbow(starship, starship.getX(), starship.getY(), drawAdaptor);
        rainbow.draw(drawAdaptor);
        Component[] cps = components.getComponents();
        for (Component component : cps) {
            drawAdaptor.drawComponent(component);
        }  
       // rainbow.run();
    }  
}
