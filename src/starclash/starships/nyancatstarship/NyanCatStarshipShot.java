package starclash.starships.nyancatstarship;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import starclash.gamemode.CommandSender;
import starclash.gui.DrawAdaptor;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipShot extends TimerTask implements StarshipShot{

    private static final long SHOT_DELAY = 10;
    private static final long NEW_SHOT_DELAY = 400;
    
    private static final float SHOT_SIZE = 0.04f;
    
    private final Timer timer = new Timer();
    
    private static long timeMe = 0;
    private static long timeEnemy = 0;
    
    private final StarshipCollision collision;
    
    private EndShotLifeListener endShotLifeListener = null;
    private StarshipFactory enemyShip;
    
    private StarshipComponents components;
    
    private float posX, posY;
    private final boolean isEnemy;
    
    private Color shotColor = Color.RED;
    
    private static boolean special = false;
    private static boolean specialEnemy = false;
    private final boolean isSpecial;
    
    public NyanCatStarshipShot(
            StarshipFactory starship,
            StarshipComponents components
    ) {
        this.posX = starship.getX()+starship.getWidth()/2;
        this.posY = starship.getY()+starship.getHeight()/2;
        this.isEnemy = starship.isEnemy();
        this.collision = starship.newStarshipCollision();
        this.components = components;
        
        if ( isEnemy ){
            posY += starship.getHeight();
        }


        if ( !isEnemy & special ){
            this.isSpecial = true;
            special = false;
            timeMe += 1000;
            initSpecial();

        } else if ( isEnemy & specialEnemy ) {
            this.isSpecial = true;
            specialEnemy = false;
            timeEnemy += 1000;
            initSpecial();
        } else {
            this.isSpecial = false;
        }
        
    }
    
    public NyanCatStarshipShot(StarshipFactory starship, float x, float y) {
        this.posX = x;
        this.posY = y;
        this.collision = starship.newStarshipCollision();
        this.isEnemy = starship.isEnemy();
        this.isSpecial = false;
    }
    
    
    @Override
    public int getDamage() {
        if(isSpecial)
            return 25;
        else
            return 5;
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
    public boolean start(StarshipFactory enemy, EndShotLifeListener endShotLifeListener){
        this.enemyShip = enemy;
        this.endShotLifeListener = endShotLifeListener;
        
        if( !shotAllowed(isEnemy) ){
            if ( endShotLifeListener != null ){
                endShotLifeListener.onExit();
            }
            return false;
        }
        if (isSpecial){
            initSpecial();
            System.out.println("initSpecial");
        }
        timer.scheduleAtFixedRate(this, 1, SHOT_DELAY);
        return true;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        if(isSpecial){
            drawSpecial(drawAdaptor);
            System.out.println("ESPECIAL DESENHADO");
        }else{
            if(isEnemy){
                drawAdaptor.drawLine(new Line(new Point(posX, posY), new Point(posX,posY+SHOT_SIZE), shotColor));
                drawAdaptor.drawLine(new Line(new Point(posX+0.005f, posY), new Point(posX+0.005f,posY+SHOT_SIZE), shotColor));
            }else{
                drawAdaptor.drawLine(new Line(new Point(posX, posY), new Point(posX,posY-SHOT_SIZE), shotColor));
                drawAdaptor.drawLine(new Line(new Point(posX+0.002f, posY), new Point(posX+0.002f,posY+SHOT_SIZE), shotColor));
                drawAdaptor.drawLine(new Line(new Point(posX-0.002f, posY), new Point(posX-0.002f,posY+SHOT_SIZE), shotColor));
            }
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
            if ( endShotLifeListener != null )
                endShotLifeListener.onHit();
            shotColor = selectColor();
        }
        else if (  posY >= 1 || posY <= 0  ){
            timer.cancel();
            if ( endShotLifeListener != null )
                endShotLifeListener.onExit();
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
        return SHOT_SIZE;
    }
    
    
    private Image specialImage = new Image();
    
    private void initSpecial(){
        specialImage = new Image("/resources/nyancat/8bitfire.png", new Rectangle(
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
        specialImage.getRectangle().setX(posX+0.3f);
        specialImage.getRectangle().setY(posY);
        drawAdaptor.drawImage(specialImage);
        specialImage.getRectangle().setX(posX-0.3f);
        specialImage.getRectangle().setY(posY);
        drawAdaptor.drawImage(specialImage);
    }
    
    public static void special(){
        special = true;
    }
    
    public static void specialEnemy(){
        specialEnemy = true;
    }
    
    public static float getNextShotTime(boolean enemy){
        return System.currentTimeMillis() - ( enemy ? timeEnemy : timeMe );
    }
    
    public boolean isSpecial(){
        return isSpecial;
    }
    
    public Rectangle getSpecialRect(){
        return specialImage.getRectangle();
    }
    
    public boolean getIsSpecial(){
        return isSpecial;
    }
}
