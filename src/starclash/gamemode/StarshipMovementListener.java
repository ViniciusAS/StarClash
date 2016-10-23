package starclash.gamemode;

import javax.swing.JFrame;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.Movement;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipFactory;

/**
 *
 * @author Vinicius Santos
 */
public class StarshipMovementListener implements MoveListener {

    private final StarshipFactory starship;

    public StarshipMovementListener(StarshipFactory starship) {
        this.starship = starship;
    }
    
    @Override
    public void moved(Movement movement) {
        float x = starship.getX();
        float y = starship.getY();
        boolean wallColision = starship.newStarshipCollision().wallCollision();
        switch (movement){
            case UP:
                if(wallColision) y++;
                y -= starship.getShipSpeed();  
                break;
            case DOWN:
                if(wallColision) y--;
                else y += starship.getShipSpeed();
                break;
            case LEFT:
                if(wallColision) x++;
                x -= starship.getShipSpeed();
                break;
            case RIGHT:
                if(wallColision) x--;
                x += starship.getShipSpeed();
                break;
      
        }
        this.starship.setX( x );
        this.starship.setY( y );
    }

}
