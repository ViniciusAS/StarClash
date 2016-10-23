package starclash.starships.ShipVinicius;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipCollision implements StarshipCollision {

    private final StarshipComponents components;
    private final StarshipFactory starship;
    public TheIncredableStarshipCollision(StarshipComponents components, StarshipFactory starship) {
        this.components = components;    
        this.starship = starship;
    }
    
    @Override
    public boolean wallCollision() {
        while(true){
            if((starship.getX()+components.getWidth()*2)/1080>=1){
                return true;
            }else if((starship.getX()-components.getWidth()*2)/1080<0){
                return true;
            }else if((starship.getY()+(components.getHeigth()*2))/720>=1){
                return true;
            }else if((starship.getY()+(components.getHeigth()*2))/720<0){
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean shotCollision(StarshipShot starshipShot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
