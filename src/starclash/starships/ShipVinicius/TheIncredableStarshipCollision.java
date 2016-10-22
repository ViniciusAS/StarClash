package starclash.starships.ShipVinicius;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipCollision implements StarshipCollision {

    private final StarshipComponents components;
    public TheIncredableStarshipCollision(StarshipComponents components) {
        this.components = components;    
    }
    
    @Override
    public boolean wallCollision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean shotCollision(StarshipShot starshipShot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
