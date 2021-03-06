package starclash.starships.theincredablestarship;


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
        Component[] cps = components.getComponents();
        if(!starship.isEnemy()){
            for (Component component : cps) {
                drawAdaptor.drawComponent(component);
            } 
        }else{
            for (Component component : cps) {
                drawAdaptor.setRotate(component,components);
            } 
        }
        
    }

}
