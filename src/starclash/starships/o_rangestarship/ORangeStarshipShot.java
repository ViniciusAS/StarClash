package starclash.starships.o_rangestarship;

import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class ORangeStarshipShot extends TimerTask implements StarshipShot  {
    
    private static final long SHOT_DELAY = 10;
    private static final long NEW_SHOT_DELAY = 225;
    
    private static final float SHOT_SIZE = 0.025f;
    
    private final Timer timer = new Timer();
    
    private static long timeMe = 0;
    private static long timeEnemy = 0;
    
    private StarshipCollision collision;
    
    private final StarshipFactory myShip;
    private StarshipFactory enemyShip;
    
    private StarshipComponents components;
    
    private float posX, posY;
    private final boolean isEnemy;
    
    private GameInterfaceAdaptor gui;
    
    private static boolean nextSpecial = false;
    private static boolean nextSpecialEnemy = false;
    private final boolean isSpecial;
    
    public ORangeStarshipShot(
            StarshipFactory starship,
            StarshipCollision collision,
            StarshipComponents components
    ) {
        this.myShip = starship;
        this.posX = starship.getX() + starship.getWidth()/2;
        this.posY = starship.getY();
        this.isEnemy = starship.isEnemy();
        this.collision = collision;
        this.components = components;
        // correcao do Y
        if ( isEnemy ){
            posY += starship.getHeight();
        }
        // se tem a flag de especial
        if ( nextSpecial ){
            this.isSpecial = true;
            nextSpecial = false;
            initSpecial();
        // se tem a flag de especial inimigo
        } else if ( nextSpecialEnemy ) {
            this.isSpecial = true;
            nextSpecialEnemy = false;
            initSpecial();
        } else {
            this.isSpecial = false;
        }
    }
    
    
    public ORangeStarshipShot(StarshipFactory starship, float x, float y) {
        this.myShip = null;
        this.posX = x;
        this.posY = y;
        this.isEnemy = starship.isEnemy();
        this.isSpecial = false;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    @Override
    public int getDamage() {
        return ( isSpecial ) ? 40:2;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
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
        
        startSpeedUpTimer();
        
        if ( isSpecial ) {
            initSpecial();
        } else {
            init();
        }
        
        return true;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final Line line = new Line();
    
    private void init(){
        synchronized (line) {
            line.setColor( new Color(1, 0.27059f, 0, 1) );
        }
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        if ( isSpecial ){
            drawSpecial(drawAdaptor);
            return;
        }
        synchronized (line) {
            line.getP0().setX(posX);
            line.getP0().setY(posY);
            line.getP1().setX(posX);
            line.getP1().setY(posY);
            if(isEnemy){
                line.getP1().setY( posY+SHOT_SIZE );
            } else {
                line.getP1().setY( posY-SHOT_SIZE );
            }
        }
        drawAdaptor.drawLine(line);
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void run() {
        shotMove();
        if( collision.shotCollision(this,enemyShip,components) )
        {
            timer.cancel();
            enemyShip.takeDamage(this);
            gui.removeDrawable(this);
        }
        else if (  posY >= 1 || posY <= 0  ){
            timer.cancel();
            gui.removeDrawable(this);
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private float shotSpeed = 0.003f;
    
    private void startSpeedUpTimer(){
        timer.schedule(new TimerTask() {
            @Override public void run() {
                shotSpeed = 0.03f;
            }
        }, ( isSpecial ) ? 800 : 500
        );
    }
    
    private void shotMove(){
        if( isEnemy ){
            posY += shotSpeed;
        } else {
            posY -= shotSpeed;
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public float getX() { return this.posX; }

    @Override
    public float getY() { return this.posY; }
    
    @Override
    public float getSize(){
        return ( isEnemy ? (SHOT_SIZE ) : 0 );
    }
    
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // SPECIAL FUNCTIONS
    
    private Image specialImage = null;
    
    private void initSpecial(){
        specialImage = new Image("/resources/o_range/shot.png", new Rectangle(
            0,0,
            0.08f, 0.08f,
            Color.TRANSPARENT
        ));
        specialImage.setInverted(isEnemy);
        posX -= 0.02f;
        if ( !isEnemy ){
            posY -= specialImage.getRectangle().getHeight()/2 - 0.005f;
        } else {
            posY -= 0.005f;
        }
    }
    
    private void drawSpecial(DrawAdaptor drawAdaptor){
        specialImage.getRectangle().setX(posX);
        specialImage.getRectangle().setY(posY);
        drawAdaptor.drawImage(specialImage);
    }
    
    public boolean isSpecial(){
        return isSpecial;
    }
    
    public Rectangle getSpecialRect(){
        return specialImage.getRectangle();
    }
    
    public static void nextSpecial(){
        nextSpecial = true;
    }
    
    public static void nextSpecialEnemy(){
        nextSpecialEnemy = true;
    }
    
}
