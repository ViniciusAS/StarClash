package starclash.starships.ShipVinicius;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;


public class TheIncredableStarship implements StarshipFactory {

    private float x, y;
    private float speed = 0.01f;
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

    @Override
    public boolean takeDamage(StarshipShot shot)
    {
        ///// INVENCIBLE /////
        return false;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public StarshipDraw newStarshipDraw() {
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
    }

    @Override
    public void doSpecial(float x, float y) {
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
