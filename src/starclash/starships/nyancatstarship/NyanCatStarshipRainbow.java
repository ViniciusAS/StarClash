package starclash.starships.nyancatstarship;

import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipFactory;
/**
 *
 * @author samuel
 */
public class NyanCatStarshipRainbow {
    
    private static final float RAINBOW_SPEED = 0.005f;
    private static final float RAINBOW_SIZE = 0.05f;
    private static final long RAINBOW_DELAY = 10;
    
    private static final String IMG_FILENAME = "/resources/nyancat/rainbow.png";
           
    private Timer timer = new Timer();

    private float posY;
    private final StarshipFactory starship;
    private final Image image;

    private boolean stopped = false;
    
    public NyanCatStarshipRainbow(StarshipFactory starship, float x, float y){
        this.posY = y + ( starship.getHeight() )/2;
        this.starship = starship;
        
        image = new Image(
            IMG_FILENAME,
            new Rectangle(
                x + starship.getWidth()/2f/2f,
                posY,
                starship.getWidth()/2,
                starship.getHeight()/2,
                Color.TRANSPARENT
            )
        );
        
        if( starship.isEnemy() ){
            image.getRectangle().setY( image.getRectangle().getY()-RAINBOW_SIZE );
        }
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                rainbowMove();
                if( posY >= 1 || posY+RAINBOW_SIZE <= 0 ){
                    stopped = true;
                    timer.cancel();
                }                
            }
        }, 1, RAINBOW_DELAY);
        
    }  
    
    public boolean isStopped(){
        return stopped;
    }
    
    public void rainbowMove(){
        
        posY += ( starship.isEnemy() ) ? - RAINBOW_SPEED : + RAINBOW_SPEED ;
        image.getRectangle().setY( posY );
    }
    
    public void draw(DrawAdaptor drawAdaptor){
        drawAdaptor.drawImage(image);
    }
    
}
