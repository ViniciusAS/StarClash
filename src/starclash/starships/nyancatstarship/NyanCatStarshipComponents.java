package starclash.starships.nyancatstarship;

import starclash.gui.components.Color;
import starclash.gui.components.Component;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipComponents implements StarshipComponents{
    
    private final Image image = new Image("/nyanCatShip.png", new Rectangle(0, 0, WIDTH, HEIGTH, Color.TRANSPARENT));
    private static final float WIDTH = 0.08f;
    private static final float HEIGTH = 0.08f;
    private final StarshipFactory starship;
    private Line[] rainbow;

    public NyanCatStarshipComponents(StarshipFactory starship) {
        this.starship = starship;
        if (starship.isEnemy())image.getRectangle().setHeight( -image.getRectangle().getHeight() ); 
    }

    @Override
    public float getWidth() {
        return WIDTH; 
    }

    @Override
    public float getHeigth() {
        return HEIGTH;
    }

    @Override
    public Component[] getComponents() {
        System.out.println("here");
        image.getRectangle().setX(starship.getX());
        image.getRectangle().setY(starship.getY());
        createRainbow();
        Component[] component = new Component[rainbow.length+1];
        component[0] = image;
        for(int i=1; i<rainbow.length; i++){
            component[i]= rainbow[i];
        }
        return component;   
    }   
    
    public void createRainbow(){
        if(!starship.isEnemy()){
            this.rainbow = new Line[]{
                new Line(new Point(this.starship.getX()+0.01f, this.starship.getY()+starship.getHeight()),
                         new Point(this.starship.getX()+0.01f, this.starship.getY()+starship.getHeight()+0.5f),  
                         Color.RED),

                new Line(new Point(this.starship.getX()+0.02f, this.starship.getY()+starship.getHeight()), 
                         new Point(this.starship.getX()+0.02f, this.starship.getY()+starship.getHeight()+0.5f), 
                         Color.ORANGE),

                new Line(new Point(this.starship.getX()+0.03f, this.starship.getY()+starship.getHeight()), 
                         new Point(this.starship.getX()+0.03f, this.starship.getY()+starship.getHeight()+0.5f),
                         Color.YELLOW),

                new Line(new Point(this.starship.getX()+0.04f, this.starship.getY()+starship.getHeight()), 
                         new Point(this.starship.getX()+0.04f, this.starship.getY()+starship.getHeight()+0.5f), 
                         Color.GREEN),

                new Line(new Point(this.starship.getX()+0.05f, this.starship.getY()+starship.getHeight()), 
                         new Point(this.starship.getX()+0.05f, this.starship.getY()+starship.getHeight()+0.5f), 
                         Color.BABY_BLUE),
                new Line(new Point(this.starship.getX()+0.06f, this.starship.getY()+starship.getHeight()), 
                         new Point(this.starship.getX()+0.06f, this.starship.getY()+starship.getHeight()+0.5f),
                         Color.VIOLET)      
            };
        }else{
            this.rainbow = new Line[]{
                new Line(new Point(this.starship.getX()+0.01f, this.starship.getY()-starship.getHeight()),
                         new Point(this.starship.getX()+0.01f, this.starship.getY()-starship.getHeight()-0.5f),  
                         Color.RED),

                new Line(new Point(this.starship.getX()+0.02f, this.starship.getY()-starship.getHeight()), 
                         new Point(this.starship.getX()+0.02f, this.starship.getY()-starship.getHeight()-0.5f), 
                         Color.ORANGE),

                new Line(new Point(this.starship.getX()+0.03f, this.starship.getY()-starship.getHeight()), 
                         new Point(this.starship.getX()+0.03f, this.starship.getY()-starship.getHeight()-0.5f),
                         Color.YELLOW),

                new Line(new Point(this.starship.getX()+0.04f, this.starship.getY()-starship.getHeight()), 
                         new Point(this.starship.getX()+0.04f, this.starship.getY()-starship.getHeight()-0.5f), 
                         Color.GREEN),

                new Line(new Point(this.starship.getX()+0.05f, this.starship.getY()-starship.getHeight()), 
                         new Point(this.starship.getX()+0.05f, this.starship.getY()-starship.getHeight()-0.5f), 
                         Color.BABY_BLUE),
                new Line(new Point(this.starship.getX()+0.06f, this.starship.getY()-starship.getHeight()), 
                         new Point(this.starship.getX()+0.06f, this.starship.getY()-starship.getHeight()-0.5f),
                         Color.VIOLET)      
            };
            
        }
    }
    
}
