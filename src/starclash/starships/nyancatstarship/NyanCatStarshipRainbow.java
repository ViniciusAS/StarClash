package starclash.starships.nyancatstarship;

import java.util.TimerTask;
import starclash.gui.components.Color;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.starships.StarshipFactory;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipRainbow extends TimerTask{

    private static final float RAINBOW_SIZE = 0.3f;
    private final StarshipFactory starship;
    private Line[] rainbow;
    private float posX, posY;
    
    public NyanCatStarshipRainbow(StarshipFactory starship) {
        this.starship = starship;    
        this.posX = starship.getX();
        this.posY = starship.getY();
        createRainbow();
    }
     public void createRainbow(){
        if(!starship.isEnemy()){
            this.rainbow = new Line[]{
                new Line(new Point(posX+0.01f, posY+starship.getHeight()),
                         new Point(posX+0.01f, posY+starship.getHeight()+RAINBOW_SIZE),  
                         Color.RED),

                new Line(new Point(posX+0.02f, posY+starship.getHeight()), 
                         new Point(posX+0.02f, posY+starship.getHeight()+RAINBOW_SIZE), 
                         Color.ORANGE),

                new Line(new Point(posX+0.03f, posY+starship.getHeight()), 
                         new Point(posX+0.03f, posY+starship.getHeight()+RAINBOW_SIZE),
                         Color.YELLOW),

                new Line(new Point(posX+0.04f, posY+starship.getHeight()), 
                         new Point(posX+0.04f, posY+starship.getHeight()+RAINBOW_SIZE), 
                         Color.GREEN),

                new Line(new Point(posX+0.05f, posY+starship.getHeight()), 
                         new Point(posX+0.05f, posY+starship.getHeight()+RAINBOW_SIZE), 
                         Color.BABY_BLUE),
                new Line(new Point(posX+0.06f, posY+starship.getHeight()), 
                         new Point(posX+0.06f, posY+starship.getHeight()+RAINBOW_SIZE),
                         Color.VIOLET)      
            };
        }else{
            this.rainbow = new Line[]{
                new Line(new Point(posX+0.01f, posY+starship.getHeight()),
                         new Point(posX+0.01f, posY+starship.getHeight()-RAINBOW_SIZE),  
                         Color.RED),

                new Line(new Point(posX+0.02f, posY+starship.getHeight()), 
                         new Point(posX+0.02f, posY+starship.getHeight()-RAINBOW_SIZE), 
                         Color.ORANGE),

                new Line(new Point(posX+0.03f, posY+starship.getHeight()), 
                         new Point(posX+0.03f, posY+starship.getHeight()-RAINBOW_SIZE),
                         Color.YELLOW),

                new Line(new Point(posX+0.04f, posY+starship.getHeight()), 
                         new Point(posX+0.04f, posY+starship.getHeight()-RAINBOW_SIZE), 
                         Color.GREEN),

                new Line(new Point(posX+0.05f, posY+starship.getHeight()), 
                         new Point(posX+0.05f, posY+starship.getHeight()-RAINBOW_SIZE), 
                         Color.BABY_BLUE),
                new Line(new Point(posX+0.06f, posY+starship.getHeight()), 
                         new Point(posX+0.06f, posY+starship.getHeight()-RAINBOW_SIZE),
                         Color.VIOLET)      
            };
            
        }
    }

    public Line[] getRainbow() {
        return rainbow;
    }

    @Override
    public void run() {
        if(!starship.isEnemy()){
            while(posY >=1) posY++;
            createRainbow();
        }
    }
     
    
    
}
