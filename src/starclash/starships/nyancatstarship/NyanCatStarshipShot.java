package starclash.starships.nyancatstarship;

import java.util.Random;
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
 * @author samuel
 */
public class NyanCatStarshipShot extends TimerTask implements StarshipShot{

    private static final long SHOT_DELAY = 20;
    private static final long NEW_SHOT_DELAY = 250;
    
    private static final float SHOT_SIZE = 0.04f;
    
    private final Timer timer = new Timer();
    
    private static long timeMe = 0;
    private static long timeEnemy = 0;
    
    private StarshipCollision collision;
    
    private StarshipFactory enemyShip;
    
    private StarshipComponents components;
    
    private float posX, posY;
    private final boolean isEnemy;
    
    private Color shotColor = Color.RED;
    
    private GameInterfaceAdaptor gui;
    
    public NyanCatStarshipShot(
            StarshipFactory starship,
            StarshipCollision collision,
            StarshipComponents components
    ) {
        this.posX = starship.getX()+starship.getWidth()/2;
        this.posY = starship.getY()+starship.getHeight()/2;
        this.isEnemy = starship.isEnemy();
        this.collision = collision;
        this.components = components;
    }
    
    public NyanCatStarshipShot(StarshipFactory starship, float x, float y) {
        this.posX = x;
        this.posY = y;
        this.isEnemy = starship.isEnemy();
    }
    
    
    @Override
    public int getDamage() {
        return 1;
    }
    
    public static boolean shotAllowed(boolean isEnemy){
        long time = ( isEnemy ) ? timeEnemy : timeMe;
        if ( System.currentTimeMillis()-time <= NEW_SHOT_DELAY )
            return false;
        if ( isEnemy ) {
            timeEnemy = System.currentTimeMillis();
        } else {
            timeMe = System.currentTimeMillis();
        }
        return true;
    }
    @Override
    public boolean start(GameInterfaceAdaptor gui, StarshipFactory enemyShip) {
        this.gui = gui;
        this.enemyShip = enemyShip;
        
        if( !shotAllowed(isEnemy) ){
            gui.removeDrawable(this);
            return false;
        }
        
        timer.scheduleAtFixedRate(this, 1,SHOT_DELAY); 
        return true;
        
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        if(isEnemy){
            drawAdaptor.drawLine(new Line(new Point(posX, posY), new Point(posX,posY+SHOT_SIZE), shotColor));
            drawAdaptor.drawLine(new Line(new Point(posX+0.005f, posY), new Point(posX+0.005f,posY+SHOT_SIZE), shotColor));
        }else{
            drawAdaptor.drawLine(new Line(new Point(posX, posY), new Point(posX,posY-SHOT_SIZE), shotColor));
            drawAdaptor.drawLine(new Line(new Point(posX+0.002f, posY), new Point(posX+0.002f,posY+SHOT_SIZE), shotColor));
            drawAdaptor.drawLine(new Line(new Point(posX-0.002f, posY), new Point(posX-0.002f,posY+SHOT_SIZE), shotColor));
        }
    }
    
    public Color selectColor(){
        Random randomGenerator = new Random();
        int colorSelect = randomGenerator.nextInt(6);
        switch (colorSelect) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.GREEN;    
            case 4:
                return Color.BABY_BLUE;
            default:
                return Color.WHITE;
        }
    }
    @Override
    public void run() {
        shotMove();
        if( collision.shotCollision(this,enemyShip,components) )
        {
            timer.cancel();
            enemyShip.takeDamage(this);
            gui.removeDrawable(this);
            shotColor = selectColor();
        }
        else if (  posY >= 1 || posY <= 0  ){
            timer.cancel();
            gui.removeDrawable(this);
        }
        shotColor = selectColor();
    }
    
    public void shotMove(){
        if( isEnemy )
            posY += 0.01f;
        else
            posY -= 0.01f;
    }

    @Override
    public float getX() { return this.posX; }

    @Override
    public float getY() { return this.posY; }
    
    @Override
    public float getSize(){
        return ( isEnemy ? SHOT_SIZE : 0 );
    }
    
}
