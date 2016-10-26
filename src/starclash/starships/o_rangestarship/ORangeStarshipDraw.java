package starclash.starships.o_rangestarship;


import starclash.gui.DrawAdaptor;
import starclash.gui.components.Component;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;

/**
 *
 * @author Vinicius Santos
 */
public class ORangeStarshipDraw implements StarshipDraw {

    private final  StarshipComponents components;

    public ORangeStarshipDraw(StarshipComponents components, StarshipFactory starship) {
        this.components = components;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor)
    {
        Component[] cps = components.getComponents();
        for (Component component : cps) {
            drawAdaptor.drawComponent(component);
        }
    }

}
