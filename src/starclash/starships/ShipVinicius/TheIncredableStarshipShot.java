package starclash.starships.ShipVinicius;

import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipShot extends TimerTask implements StarshipShot  {
    
    private static final long SHOT_DELAY = 10;
    private static final long NEW_SHOT_DELAY = 300;
    private Timer timer;
    private GameInterfaceAdaptor gui;
    private float posX,posY;
    private final boolean isEnemy;
    private static long time = 0;
    
    public TheIncredableStarshipShot(GameInterfaceAdaptor gui, StarshipFactory starship) {
        timer = new Timer();  
        this.gui = gui;
        this.posX = starship.getX();
        this.posY = starship.getY();
        this.isEnemy = starship.isEnemy();
    }
    
    @Override
    public void start() {
        if(!isEnemy){
            if(System.currentTimeMillis()-time <= NEW_SHOT_DELAY){
                gui.removeDrawable(this);
                return;
            }
            time = System.currentTimeMillis();
        }
        timer.scheduleAtFixedRate(this, 1,SHOT_DELAY); 
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        if(!isEnemy)
            drawAdaptor.drawLine(new Line(new Point(posX, posY), new Point(posX,posY-0.05f), Color.RED));
        else
            drawAdaptor.drawLine(new Line(new Point(posX, posY), new Point(posX,posY+0.05f), Color.RED));
            
    }

    @Override
    public void run() {
        shotMove();
        if(posY>=1){
            timer.cancel();
            gui.removeDrawable(this);
        }  
    }
    
    public void shotMove(){
        if(!isEnemy)
            posY -= 0.01f;
        else
            posY += 0.01f;
    }

}
