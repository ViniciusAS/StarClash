package starclash.starships.ShipVinicius;


import java.awt.Graphics;
import java.awt.Graphics2D;
import starclash.gui.DrawAdaptor;
import starclash.gui.components.Component;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipDraw implements StarshipDraw {

    private final  StarshipComponents components;
    private final  StarshipFactory starship;

    public TheIncredableStarshipDraw(StarshipComponents components, StarshipFactory starship) {
        this.components = components;
        this.starship = starship;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor)
    {
        if(!starship.isEnemy()){
            Component[] cps = components.getComponents();
            for (Component component : cps) {
                drawAdaptor.drawComponent(component);
            } 
        }else{
            Component[] cps = components.getComponents();
            for (Component component : cps) {
                //drawAdaptor.drawComponent(component);
                drawAdaptor.setRotate(component);
            } 
        }
        
    }

}
