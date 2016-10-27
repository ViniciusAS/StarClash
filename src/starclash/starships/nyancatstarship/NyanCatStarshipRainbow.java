package starclash.starships.nyancatstarship;

import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.gui.swing.SwingDrawAdaptor;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipRainbow extends TimerTask implements StarshipShot{
    
    private static final float RAINBOW_SIZE = 0.1f;
    private static final long RAINBOW_DELAY = 2;
    
    private StarshipFactory starshipEnemy;
    private StarshipComponents components;
    //private StarshipCollision collision;
    
    private DrawAdaptor drawAdaptor;
    
    private final Timer timer = new Timer();
    
    private static long timeMe = 0;
    private static long timeEnemy = 0;
    
    private float posX, posY;
    private final boolean isEnemy;
    
    private GameInterfaceAdaptor gui;
    
    public NyanCatStarshipRainbow(StarshipFactory starship, StarshipComponents components){
        //this.collision = collision;
        this.components = components;
        this.posX = starship.getX()+starship.getWidth()/2;
        this.posY = starship.getY()+starship.getHeight()/2;
        this.isEnemy = starship.isEnemy();
        start(gui, starshipEnemy);
    }
    
    public NyanCatStarshipRainbow(StarshipFactory starship, float x, float y){
        this.posX = x;
        this.posY = y;
        this.isEnemy = starship.isEnemy();
        start(gui, starshipEnemy);
    } 
    
    @Override
    public void run() {
         draw(drawAdaptor);
         rainbowMove();
         System.out.println("RUN");
        
        if (  posY >= 1 || posY <= 0  ){
            timer.cancel();
            gui.removeDrawable(this);
        }
    }
    
    public void rainbowMove(){
        if(!isEnemy) posY++;
        else posY--;
    }

    @Override
    public boolean start(GameInterfaceAdaptor gui, StarshipFactory starshipEnemy) {
        System.out.println("START");
        this.gui = gui;
        this.starshipEnemy = starshipEnemy;
        timer.scheduleAtFixedRate(this, 1,RAINBOW_DELAY); 
        return true;
    }

    @Override
    public int getDamage() {
        return 1;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        if(!isEnemy){
             System.out.println("DESENHOU");
            
                drawAdaptor.drawLine(new Line(new Point(posX+0.01f, posY),
                                     new Point(posX+0.01f, posY+RAINBOW_SIZE),  
                                     Color.RED));

                drawAdaptor.drawLine(new Line(new Point(posX+0.02f, posY), 
                                     new Point(posX+0.02f, posY+RAINBOW_SIZE), 
                                     Color.ORANGE));

                drawAdaptor.drawLine(new Line(new Point(posX+0.03f, posY), 
                                     new Point(posX+0.03f, posY+RAINBOW_SIZE),
                                     Color.YELLOW));

                drawAdaptor.drawLine(new Line(new Point(posX+0.04f, posY), 
                                     new Point(posX+0.04f, posY+RAINBOW_SIZE), 
                                     Color.GREEN));

                drawAdaptor.drawLine(new Line(new Point(posX+0.05f, posY), 
                                     new Point(posX+0.05f, posY+RAINBOW_SIZE), 
                                     Color.BABY_BLUE));
                drawAdaptor.drawLine(new Line(new Point(posX+0.06f, posY), 
                                     new Point(posX+0.06f, posY+RAINBOW_SIZE),
                                     Color.VIOLET));   
        }else{  
                drawAdaptor.drawLine(new Line(new Point(posX+0.01f, posY),
                         new Point(posX+0.01f, posY+RAINBOW_SIZE),  
                         Color.RED));

                drawAdaptor.drawLine(new Line(new Point(posX+0.02f, posY), 
                                     new Point(posX+0.02f, posY+RAINBOW_SIZE), 
                                     Color.ORANGE));

                drawAdaptor.drawLine(new Line(new Point(posX+0.03f, posY), 
                                     new Point(posX+0.03f, posY+RAINBOW_SIZE),
                                     Color.YELLOW));

                drawAdaptor.drawLine(new Line(new Point(posX+0.04f, posY), 
                                     new Point(posX+0.04f, posY+RAINBOW_SIZE), 
                                     Color.GREEN));

                drawAdaptor.drawLine(new Line(new Point(posX+0.05f, posY), 
                                     new Point(posX+0.05f, posY+RAINBOW_SIZE), 
                                     Color.BABY_BLUE));
                drawAdaptor.drawLine(new Line(new Point(posX+0.06f, posY), 
                                     new Point(posX+0.06f, posY+RAINBOW_SIZE),
                                     Color.VIOLET));      
        }
    }
    
    @Override
    public float getX() {
        return posX;
    }

    @Override
    public float getY() {
        return posY;
    }

    @Override
    public float getSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
