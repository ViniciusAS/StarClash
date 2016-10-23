package starclash.starships.ShipVinicius;


import starclash.gui.DrawAdaptor;
import starclash.gui.components.Component;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipDraw implements StarshipDraw {

    private final  StarshipComponents components;

    public TheIncredableStarshipDraw(StarshipComponents components) {
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
