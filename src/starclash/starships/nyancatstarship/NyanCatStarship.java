package starclash.starships.nyancatstarship;

import starclash.gui.DrawAdaptor;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author samuel
 */
public class NyanCatStarship extends StarshipFactory{
    
    private float x, y;
    private float speed = 0.01f;
    private final boolean enemy;
    private final StarshipComponents components;
    
    private int life = 100;

    public NyanCatStarship() {
        this(false);
    }

    public NyanCatStarship( boolean enemy ) {
        this.enemy = enemy;
        components = new NyanCatStarshipComponents(this);
        x = 0.5f - components.getWidth()/2;
        y = ( enemy ) ? 0.25f : 0.75f;
        y -= components.getHeigth()/2;
    }
    
    /*========================================================================================================*/    
    @Override
    public String getName() {
        return "NyanCatStarShip";
    }

    @Override
    public StarshipFactory getNext() {
        return null;
    }
    
    @Override
    public boolean isEnemy() {
        return this.enemy;
    }
    
    /*========================================================================================================*/

    @Override
    public float getManaPercent() {
        return NyanCatStarshipShot.getNextShotTime(isEnemy()) / 1000f;
    }
    
    @Override
    public float getLifePercent() {
        return life/100f;
    }
    
    @Override
    public boolean takeDamage(int damage)
    {
        super.notifyDamage(damage);
        life -= damage;
        if ( life <= 0 ){
            super.notifyDie();
            return true;
        }
        return false;
    }
    
    /*========================================================================================================*/
    
    
    @Override
    public StarshipDraw newStarshipDraw() {
        return new NyanCatStarshipDraw(this,components,newStarshipCollision());
    }

    @Override
    public StarshipCollision newStarshipCollision() {
        return new NyanCatStarshipCollision(this,components);
    }

    @Override
    public StarshipShot newShot() {
        return new NyanCatStarshipShot(this, components);
    }

    @Override
    public StarshipShot newShot(float x, float y) {
        return new NyanCatStarshipShot(this, components);
    }
    
    
    /*========================================================================================================*/

    @Override
    public void doSpecial() {
        doSpecial( getX(), getY() );
    }

    @Override
    public void doSpecial(float x, float y) {
        if ( getManaPercent() < 1 )return;
        
        
        if ( enemy ){
            NyanCatStarshipShot.specialEnemy();
        }
        else{
            NyanCatStarshipShot.special();
            
        }
    }
        
    /*========================================================================================================*/


    @Override
    public float getShipSpeed() {
        return this.speed;
    }
    
    @Override
    public float getWidth() {
        return this.components.getWidth();
    }

    @Override
    public float getHeight() {
        return this.components.getHeigth();
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }
    
    /*========================================================================================================*/
    @Override
    public void setShipSpeed(float speed) {
        this.speed = speed;   
    }


    @Override
    public void setX(float x) {
        this.x=x;
    }

    @Override
    public void setY(float y) {
        this.y=y;
    }
    
}
