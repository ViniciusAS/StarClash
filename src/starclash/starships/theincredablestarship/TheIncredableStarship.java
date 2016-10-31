package starclash.starships.theincredablestarship;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;


public class TheIncredableStarship extends StarshipFactory {

    private Timer manaTimer;
    
    private static final long MANA_DELAY = 10;
    
    private int manaAmount = 0;
    private int life = 100;
    private float x, y;
    private float speed = 0.003f;
    private final boolean enemy;
    private final StarshipComponents components = new TheIncreadableStarshipComponents(this);
    
    
    public TheIncredableStarship(boolean enemy) {
        x = 0.5f - components.getWidth()/2;
        y = ( enemy ) ? 0.25f : 0.75f;
        y -= components.getHeigth()/2;
        this.enemy = enemy;
    }
    
    public TheIncredableStarship() {
        x = 0.5f - components.getWidth()/2;
        y = 0.75f - components.getHeigth()/2;
        enemy = false;
    }

    @Override
    public String getName() {
        return "The Incredable Starship";
    }

    @Override
    public StarshipFactory getNext() {
        return null;
    }

    @Override
    public boolean isEnemy() {
        return this.enemy;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void startManaTimer(){
        manaAmount = 0;
        manaTimer = new Timer();
        manaTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if ( manaAmount < 1000 ){
                    manaAmount += 1;
                } else {
                    manaTimer.cancel();
                }
            }
        }, 100, MANA_DELAY);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public float getManaPercent() {
        return manaAmount/1000f;
    }

    @Override
    public float getLifePercent() {
        return life/100f;
    }
    
    @Override
    public boolean takeDamage(int damage)
    {
        life -= damage;
        if ( life <= 0 ){
            super.notifyDie();
            return true;
        }
        return false;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public StarshipDraw newStarshipDraw() {
        startManaTimer();
        return new TheIncredableStarshipDraw( components, this );
    }

    @Override
    public StarshipCollision newStarshipCollision() {
        return new TheIncredableStarshipCollision( this , components);        
    }

    @Override
    public StarshipShot newShot() {
        return new TheIncredableStarshipShot(this,newStarshipCollision(),components);
    }

    @Override
    public StarshipShot newShot(float x, float y) {
        return new TheIncredableStarshipShot(this,newStarshipCollision(),components);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void doSpecial() {
        if ( manaAmount == 1000 ){
            startManaTimer();
            TheIncreadableStarshipComponents.startSpecialColors(isEnemy());
            new Thread(() -> {
                for (int i = 0; i < 30; i++) {
                    life += 1;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {}
                }
                TheIncreadableStarshipComponents.stopSpecialColors(isEnemy());
            }).start();
        }
    }

    @Override
    public void doSpecial(float x, float y) {
        this.doSpecial();
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public float getShipSpeed() {
        return speed;
    }

    @Override
    public float getWidth() {
        return components.getWidth();
    }

    @Override
    public float getHeight() {
        return components.getHeigth();
    }
    
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void setShipSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

}
