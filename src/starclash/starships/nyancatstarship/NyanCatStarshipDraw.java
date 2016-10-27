package starclash.starships.nyancatstarship;

import starclash.gui.DrawAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Component;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipDraw implements StarshipDraw{

    private final StarshipComponents components;
    private final NyanCatStarship rainbow;
    
    public NyanCatStarshipDraw(NyanCatStarship starship, StarshipComponents components) {
        this.components = components;
        this.rainbow = starship;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        
        rainbow.newRainbow().draw(drawAdaptor);
        Component[] cps = components.getComponents();
        for (Component component : cps) {
            drawAdaptor.drawComponent(component);
        }  
    }  
}
