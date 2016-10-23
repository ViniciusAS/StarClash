package starclash.starships.ShipVinicius;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipShot;

public class TheIncredableStarshipCollision implements StarshipCollision {

    private final StarshipComponents components;
    private final boolean enemy;
    
    public TheIncredableStarshipCollision(StarshipComponents components, boolean enemy) {
        this.components = components;
        this.enemy = enemy;
    }

    @Override
    public float wallCollisionHorizontalFilter(float x)
    {
        // left
        if ( x - components.getWidth()/2 <= 0 )
            return 0 + components.getWidth()/2;
        // right
        if ( x + components.getWidth()/2 >= 1 )
            return 1 - components.getWidth()/2;
        // nothing
        return x;
    }

    @Override
    public float wallCollisionVerticalFilter(float y)
    {
        float topLimit = ( enemy ) ? 0 : 0.5f;
        float botLimit = ( enemy ) ? 0.5f : 1;
        topLimit += components.getHeigth()/2;
        botLimit -= components.getHeigth()/2;
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
    public boolean shotCollision(StarshipShot starshipShot) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
