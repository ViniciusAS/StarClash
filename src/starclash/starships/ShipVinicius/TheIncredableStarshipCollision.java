package starclash.starships.ShipVinicius;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

public class TheIncredableStarshipCollision implements StarshipCollision {

    private final StarshipComponents components;
    private final boolean enemy;
    private final StarshipFactory starship;
    
    public TheIncredableStarshipCollision(StarshipFactory starship ,StarshipComponents components) {
        this.components = components;
        this.enemy = starship.isEnemy();
        this.starship = starship;
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
        topLimit += components.getHeigth();
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

    @Override
    public boolean shotCollision(StarshipShot starshipShot, StarshipFactory enemyShip, StarshipComponents components) {
        if( starshipShot.getY()+starshipShot.getSize() <= enemyShip.getY() + components.getHeigth() ){
            if(    starshipShot.getX() >= enemyShip.getX()
                && starshipShot.getX() <= enemyShip.getX() + components.getWidth()){
                return true;
            }
        }
        return false;
    }

}
