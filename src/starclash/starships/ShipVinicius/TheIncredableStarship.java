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
        x = 0.5f;
        y = ( enemy ) ? 0.25f : 0.75f;
        this.enemy = enemy;
    }
    
    public TheIncredableStarship() {
        x = 0.5f;
        y = 0.75f;
        enemy = false;
    }

    @Override
    public String getName() {
        return "The Incredable Starship";
    }
    
    @Override
    public boolean isEnemy() {
        return this.enemy;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public StarshipDraw newStarshipDraw() {
        return new TheIncredableStarshipDraw( components );
    }

    @Override
    public StarshipCollision newStarshipCollision() {
        return new TheIncredableStarshipCollision( components, this.enemy );
    }

    @Override
    public StarshipShot newShot() {
        return new TheIncredableStarshipShot();
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void doSpecial() {
        
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public float getShipSpeed() {
        return speed;
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
