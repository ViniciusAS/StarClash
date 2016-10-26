package starclash.starships.nyancatstarship;

import starclash.gui.components.Component;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author samuel
 */
public class NyanCatStarship implements StarshipFactory{
    
    private float x, y;
    private float speed = 0.05f;
    private final boolean enemy;
    private final StarshipComponents components = new NyanCatStarshipComponents();


    public NyanCatStarship() {
        x = 0.5f - components.getWidth()/2;
        y = 0.75f - components.getHeigth()/2;
        enemy = false;
    }

    public NyanCatStarship( boolean enemy) {
        x = 0.5f - components.getWidth()/2;
        y = ( enemy ) ? 0.25f : 0.75f;
        y -= components.getHeigth()/2;
        this.enemy = enemy;
        
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
    
    private DieListener dieListener;
    @Override
    public void setDieListener(DieListener dieListener) {
        this.dieListener = dieListener;
    }

    @Override
    public boolean takeDamage(StarshipShot shot)
    {
        if ( dieListener != null ){
            dieListener.dead();
        }
        return true;
    }
    
    /*========================================================================================================*/

    @Override
    public StarshipDraw newStarshipDraw() {
        return new NyanCatStarshipDraw(this,components);
    }

    @Override
    public StarshipCollision newStarshipCollision() {
        return new NyanCatStarshipCollision();
    }

    @Override
    public StarshipShot newShot() {
        return new NyanCatStarshipShot();
    }

    @Override
    public StarshipShot newShot(float x, float y) {
        return new NyanCatStarshipShot();
    }
    
    /*========================================================================================================*/

    @Override
    public void doSpecial() {
      
    }

    @Override
    public void doSpecial(float x, float y) {
        
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