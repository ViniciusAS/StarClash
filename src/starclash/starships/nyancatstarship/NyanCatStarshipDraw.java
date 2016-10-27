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
    private final StarshipFactory starship;
    private final Line[] rainbow;
    
    public NyanCatStarshipDraw(StarshipFactory starship, StarshipComponents components) {
        this.starship = starship;
        this.components = components;
        this.rainbow = new Line[]{
            new Line(new Point(this.starship.getX()+0.01f, this.starship.getY()),
                     new Point(this.starship.getX()+0.01f, this.starship.getY()+0.06f),  
                     Color.RED),
            
            new Line(new Point(this.starship.getX()+0.02f, this.starship.getY()), 
                     new Point(this.starship.getX()+0.02f, this.starship.getY()+0.06f),
                     Color.ORANGE),
            
            new Line(new Point(this.starship.getX()+0.03f, this.starship.getY()), 
                     new Point(this.starship.getX()+0.03f, this.starship.getY()+0.06f), 
                     Color.YELLOW),
            
            new Line(new Point(this.starship.getX()+0.04f, this.starship.getY()), 
                     new Point(this.starship.getX()+0.04f, this.starship.getY()+0.06f), 
                     Color.GREEN),
            
            new Line(new Point(this.starship.getX()+0.05f, this.starship.getY()), 
                     new Point(this.starship.getX()+0.05f, this.starship.getY()+0.06f), 
                     Color.BABY_BLUE),
            new Line(new Point(this.starship.getX()+0.06f, this.starship.getY()), 
                     new Point(this.starship.getX()+0.06f, this.starship.getY()+0.06f), 
                     Color.VIOLET)      
        };
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        Component[] cps = components.getComponents();
        Line[] line = new Line[rainbow.length];
        for (Component component : cps) drawAdaptor.drawComponent(component); 
        
    }

    
}
