package starclash.starships.ShipVinicius;


import starclash.gui.DrawAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Point;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Triangle;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.gui.components.Line;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipDraw implements StarshipDraw {

    private final TheIncredableStarship starship;
    private final  StarshipComponents components;

    public TheIncredableStarshipDraw(TheIncredableStarship starship, StarshipComponents components) {
        this.components = components;
        this.starship = starship;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        for(int i=0; i< components.getComponents().length; i++){
            if(components.getComponents()[i] instanceof Triangle){
                drawAdaptor.drawTriangle((Triangle)components.getComponents()[i]);
            }
            if(components.getComponents()[i] instanceof Rectangle){
                drawAdaptor.drawRectangle((Rectangle)components.getComponents()[i]);
            }
            if(components.getComponents()[i] instanceof Line){
                drawAdaptor.drawLine((Line) components.getComponents()[i]);
            }
            if(components.getComponents()[i] instanceof Image){
                drawAdaptor.drawImage((Image)components.getComponents()[i]);
            }   
            
        }
        
    }

}
