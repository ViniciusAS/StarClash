package starclash.starships.nyancatstarship;

import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipFactory;
/**
 *
 * @author samuel
 */
public class NyanCatStarshipRainbow {
    
    private static final float RAINBOW_SIZE = 0.05f;
    private static final long RAINBOW_DELAY = 10;
    private static final long NEW_RAINBOW_DELAY = 1;
    
    private static final String imgName = "/resources/nyancat/rainbow.png";
           
    private Timer timer = new Timer();

    private float posX, posY, heigth, width;
    private final boolean isEnemy;


    public NyanCatStarshipRainbow(StarshipFactory starship, float x, float y, DrawAdaptor drawAdaptor){
        this.posX = x;
        this.posY = y;
        this.heigth = starship.getHeight();
        this.width = starship.getWidth();
        this.isEnemy = starship.isEnemy();
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                rainbowMove();
                if(posY >= 1 || posY <= 0)timer.cancel();
                draw(drawAdaptor);
                
            }
        }, RAINBOW_DELAY, NEW_RAINBOW_DELAY);
        //timer.cancel();
        
    }  
    
    public void rainbowMove(){
        if(!isEnemy) posY+=RAINBOW_SIZE/2;
        else posY-= RAINBOW_SIZE/2;
    }
    
    public void draw(DrawAdaptor drawAdaptor){
        if(!isEnemy){
            drawAdaptor.drawImage(new Image(imgName, new Rectangle(posX+(width/2)/2, posY+heigth/2, width/2, heigth/2, Color.TRANSPARENT)));
        }else{
            drawAdaptor.drawImage(new Image(imgName, new Rectangle(posX+(width/2)/2, posY-heigth/2+0.05f, width/2, heigth/2, Color.TRANSPARENT))); 
        }
    }
    
}
