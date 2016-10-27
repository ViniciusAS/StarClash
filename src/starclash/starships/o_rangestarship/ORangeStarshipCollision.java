package starclash.starships.o_rangestarship;

import starclash.gui.components.Rectangle;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class ORangeStarshipCollision implements StarshipCollision {

    private final StarshipComponents components;
    private final boolean enemy;
    
    public ORangeStarshipCollision(StarshipFactory starship, StarshipComponents components) {
        this.components = components;
        this.enemy = starship.isEnemy();
    }

    @Override
    public float wallCollisionHorizontalFilter(float x)
    {
        // left
        if ( x <= 0 )
            return 0;
        // right
        if ( x + components.getWidth() >= 1 )
            return 1 - components.getWidth();
        // nothing
        return x;
    }

    @Override
    public float wallCollisionVerticalFilter(float y)
    {
        float topLimit = ( enemy ) ? 0 : 0.5f;
        float botLimit = ( enemy ) ? 0.5f : 1;
        botLimit -= components.getHeigth();
        // top
        if ( y <= topLimit )
            return topLimit;
        // bottom
        if ( y >= botLimit )
            return botLimit;
        // nothing
        return y;
    }

    
    /**
     * X
     *   y1
     *   |
     *   |
     *   y2
     */
    private boolean testPoints(
            StarshipFactory enemyShip,
            StarshipComponents components,
            float x,
            float y1, float y2
    ){
        if ( enemyShip.isEnemy() ) {
            if( 
                enemyShip.getY() + components.getHeigth() >= y2
                &&
                enemyShip.getY() <= y1
            ){
                if(    x >= enemyShip.getX()
                    && x <= enemyShip.getX() + components.getWidth()){
                    return true;
                }
            }
        } else {
            if(
                enemyShip.getY() + components.getHeigth() >= y2
                &&
                enemyShip.getY() <= y1
            ){
                if(    x >= enemyShip.getX()
                    && x <= enemyShip.getX() + components.getWidth()){
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean shotCollision(StarshipShot starshipShot, StarshipFactory enemyShip, StarshipComponents components) {
        if ( !(starshipShot instanceof ORangeStarshipShot) )
            return false;
        
        ORangeStarshipShot orshot = ((ORangeStarshipShot) starshipShot);
        
        if ( !orshot.isSpecial() )
            return (
                testPoints(
                        enemyShip,
                        components,
                        starshipShot.getX(),
                        starshipShot.getY(),
                        starshipShot.getY() + starshipShot.getSize()
                )
            );
        
        Rectangle shot = orshot.getSpecialRect();
        
        return (
            testPoints(
                    enemyShip,
                    components,
                    shot.getX(),
                    shot.getY(),
                    shot.getY()+shot.getHeight()
            ) | 
            testPoints(
                    enemyShip,
                    components,
                    shot.getX()+shot.getWidht(),
                    shot.getY(),
                    shot.getY()+shot.getHeight()
            )
        );
    }

}
