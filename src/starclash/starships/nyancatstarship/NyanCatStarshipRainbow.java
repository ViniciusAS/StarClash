package starclash.starships.nyancatstarship;

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
/**
 *
 * @author samuel
 */
public class NyanCatStarshipRainbow {
    
    private static final float RAINBOW_SIZE = 0.08f;
    private static final long RAINBOW_DELAY = 2;
    private static final long NEW_RAINBOW_DELAY = 20;
    
    private StarshipFactory starshipEnemy;
    private StarshipComponents components;
    private StarshipCollision collision;
       
    private Timer timer = new Timer();
    
    private static long timeMe = 0;
    private static long timeEnemy = 0;
    
    private float posX, posY, heigth, width;
    private final boolean isEnemy;
    
    private GameInterfaceAdaptor gui;
    
    /*    public NyanCatStarshipRainbow(StarshipFactory starship,StarshipCollision collision ,StarshipComponents components,DrawAdaptor drawAdaptor){
    this.collision = collision;
    this.components = components;
    this.posX = starship.getX()+starship.getWidth()/2;
    this.posY = starship.getY()+starship.getHeight()/2;
    this.heigth = starship.getHeight();
    this.width = starship.getWidth();
    this.isEnemy = starship.isEnemy();
    
    timer.scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run() {
    draw(drawAdaptor);
    rainbowMove();
    }
    }, RAINBOW_DELAY, NEW_RAINBOW_DELAY);
    }
    */
    public NyanCatStarshipRainbow(StarshipFactory starship, float x, float y, DrawAdaptor drawAdaptor){
        this.posX = x;
        this.posY = y;
        this.heigth = starship.getHeight();
        this.width = starship.getWidth();
        this.isEnemy = starship.isEnemy();
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                draw(drawAdaptor);
                rainbowMove();
            }
        }, RAINBOW_DELAY, NEW_RAINBOW_DELAY);
    } 
    
    /*
    public static boolean rainAllowed(boolean isEnemy){
    long time = ( isEnemy ) ? timeEnemy : timeMe;
    if ( System.currentTimeMillis()-time <= NEW_RAINBOW_DELAY)
    return false;
    if ( isEnemy ) {
    timeEnemy = System.currentTimeMillis();
    } else {
    timeMe = System.currentTimeMillis();
    }
    return true;
    }*/
    
    
    public void rainbowMove(){
        if(!isEnemy) posY=posY + RAINBOW_SIZE;
        else posY-= RAINBOW_SIZE;
    }
    /*
    @Override
    public boolean start(GameInterfaceAdaptor gui, StarshipFactory starshipEnemy) {
    this.gui = gui;
    this.starshipEnemy = starshipEnemy;
    
    if( !rainAllowed(isEnemy) ){
    gui.removeDrawable(this);
    return false;
    }
    
    //timer.scheduleAtFixedRate(this, 1,RAINBOW_DELAY);
    return true;
    }*/
    /*
    @Override
    public int getDamage() {
    return 1;
    }*/

    public void draw(DrawAdaptor drawAdaptor) {
        if(!isEnemy){
            
                drawAdaptor.drawLine(new Line(new Point(posX+0.018f-(width/2), posY+0.03f),
                                     new Point(posX+0.018f-(width/2), posY+RAINBOW_SIZE),  
                                     Color.RED));

                drawAdaptor.drawLine(new Line(new Point(posX+0.027f-(width/2), posY+0.03f), 
                                     new Point(posX+0.027f-(width/2), posY+RAINBOW_SIZE), 
                                     Color.ORANGE));

                drawAdaptor.drawLine(new Line(new Point(posX+0.036f-(width/2), posY+0.03f), 
                                     new Point(posX+0.036f-(width/2), posY+RAINBOW_SIZE),
                                     Color.YELLOW));

                drawAdaptor.drawLine(new Line(new Point(posX+0.045f-(width/2), posY+0.03f), 
                                     new Point(posX+0.045f-(width/2), posY+RAINBOW_SIZE), 
                                     Color.GREEN));

                drawAdaptor.drawLine(new Line(new Point(posX+0.054f-(width/2), posY+0.03f), 
                                     new Point(posX+0.054f-(width/2), posY+RAINBOW_SIZE), 
                                     Color.BABY_BLUE));
                drawAdaptor.drawLine(new Line(new Point(posX+0.063f-(width/2), posY+0.03f), 
                                     new Point(posX+0.063f-(width/2), posY+RAINBOW_SIZE),
                                     Color.VIOLET));   
        }else{  
               drawAdaptor.drawLine(new Line(new Point(posX+0.018f-(width/2), posY-0.03f),
                                     new Point(posX+0.018f-(width/2), posY-RAINBOW_SIZE*2),  
                                     Color.RED));

                drawAdaptor.drawLine(new Line(new Point(posX+0.027f-(width/2), posY-0.03f), 
                                     new Point(posX+0.027f-(width/2), posY-RAINBOW_SIZE*2), 
                                     Color.ORANGE));

                drawAdaptor.drawLine(new Line(new Point(posX+0.036f-(width/2), posY-0.03f), 
                                     new Point(posX+0.036f-(width/2), posY-RAINBOW_SIZE*2),
                                     Color.YELLOW));

                drawAdaptor.drawLine(new Line(new Point(posX+0.045f-(width/2), posY-0.03f), 
                                     new Point(posX+0.045f-(width/2), posY-RAINBOW_SIZE*2), 
                                     Color.GREEN));

                drawAdaptor.drawLine(new Line(new Point(posX+0.054f-(width/2), posY-0.03f), 
                                     new Point(posX+0.054f-(width/2), posY-RAINBOW_SIZE*2), 
                                     Color.BABY_BLUE));
                drawAdaptor.drawLine(new Line(new Point(posX+0.063f-(width/2), posY-0.03f), 
                                     new Point(posX+0.063f-(width/2), posY-RAINBOW_SIZE*2),
                                     Color.VIOLET));  
        }
      
    }
    
     
    /*    @Override
    public float getX() {
    return posX;
    }
    
    @Override
    public float getY() {
    return posY;
    }
    
    @Override
    public float getSize() {
    return ( isEnemy ? RAINBOW_SIZE : 0 );
    }*/
 
}
