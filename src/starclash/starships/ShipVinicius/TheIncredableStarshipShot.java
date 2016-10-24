package starclash.starships.ShipVinicius;

import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
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
    private StarshipCollision collision;
    private StarshipFactory starship, enemyShip;
    private StarshipComponents components;
    private boolean isCollision = false;
    
    public TheIncredableStarshipShot(
            GameInterfaceAdaptor gui,
            StarshipFactory starship,
            StarshipCollision collision,
            StarshipComponents components) {
        timer = new Timer();  
        this.gui = gui;
        this.posX = starship.getX();
        this.posY = starship.getY();
        this.isEnemy = starship.isEnemy();
        this.collision = collision;
        this.starship = starship;
        this.components = components;
    }
    
    
    @Override
    public void start(StarshipFactory enemy) {
        this.enemyShip = enemy;
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
        if(collision.shotCollision(this,enemyShip,components)){
            gui.removeDrawable(this);
        }
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

    @Override
    public float getShotPosX() {
        return posX;
    }

    @Override
    public float getShotPosY() {
        return posY;
    }

    

}
