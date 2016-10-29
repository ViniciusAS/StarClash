package starclash.starships.o_rangestarship;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class ORangeStarship extends StarshipFactory {

    private float x, y;
    private float speed = 0.005f;
    private final boolean enemy;
    private final StarshipComponents components;
    
    private int life = 100;
    
    public ORangeStarship(boolean enemy) {
        this.enemy = enemy;
        components = new ORangeStarshipComponents(this);
        
        x = 0.5f - components.getWidth()/2;
        y = ( enemy ) ? 0.25f : 0.75f;
        y -= components.getHeigth()/2;
    }
    
    public ORangeStarship() {
        this(false);
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public String getName() {
        return "O-Range Starship";
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

    @Override
    public float getLifePercent() {
        return life/100f;
    }

    
    @Override
    public boolean takeDamage(StarshipShot shot)
    {
        super.notifyDamage(shot.getDamage());
        life -= shot.getDamage();
        if ( life <= 0 ){
            super.notifyDie();
            return true;
        }
        return false;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public StarshipDraw newStarshipDraw() {
        return new ORangeStarshipDraw( components, this );
    }

    @Override
    public StarshipCollision newStarshipCollision() {
        return new ORangeStarshipCollision( this , components);        
    }

    @Override
    public StarshipShot newShot() {
        return new ORangeStarshipShot(this,newStarshipCollision(),components);
    }

    @Override
    public StarshipShot newShot(float x, float y) {
        return new ORangeStarshipShot(this,newStarshipCollision(),components);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void doSpecial() {
        doSpecial( getX(), getY() );
    }

    @Override
    public void doSpecial(float x, float y) {
        ORangeStarshipShot.nextSpecial();
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
