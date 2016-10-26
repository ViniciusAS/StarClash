package starclash.starships.nyancatstarship;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipCollision implements StarshipCollision{

    
    private final StarshipComponents components;
    private final boolean enemy;
    
    public NyanCatStarshipCollision(StarshipFactory starship, StarshipComponents components) {
        this.components = components;
        this.enemy = starship.isEnemy();
    }

    @Override
    public float wallCollisionHorizontalFilter(float x)
    {
        if ( x <= 0 ) return 0;    
        if ( x + components.getWidth() >= 1 ) return 1 - components.getWidth();
        return x;
    }

    @Override
    public float wallCollisionVerticalFilter(float y)
    {
        float topLimit = ( enemy ) ? 0 : 0.5f;
        float botLimit = ( enemy ) ? 0.5f : 1;
        botLimit -= components.getHeigth();
        if ( y <= topLimit ) return topLimit;
        if ( y >= botLimit )return botLimit;
        return y;
    }

    @Override
    public boolean shotCollision(StarshipShot starshipShot, StarshipFactory enemyShip, StarshipComponents components) {
        
        if ( enemyShip.isEnemy() ) {
            if( starshipShot.getY()+starshipShot.getSize() <= enemyShip.getY() + components.getHeigth() ){
                if(    starshipShot.getX() >= enemyShip.getX()
                    && starshipShot.getX() <= enemyShip.getX() + components.getWidth()){
                    return true;
                }
            }
        } else {
            if( starshipShot.getY()+starshipShot.getSize() >= enemyShip.getY() + components.getHeigth() ){
                if(    starshipShot.getX() >= enemyShip.getX()
                    && starshipShot.getX() <= enemyShip.getX() + components.getWidth()){
                    return true;
                }
            }
        }
        return false;
    }
    
}
