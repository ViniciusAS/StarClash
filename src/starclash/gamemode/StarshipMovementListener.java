package starclash.gamemode;

import javax.swing.JFrame;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.Movement;
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
        switch (movement){
            case UP:
                y -= starship.getShipSpeed();
                break;
            case DOWN:
                y += starship.getShipSpeed();
                break;
            case LEFT:
                x -= starship.getShipSpeed();
                break;
            case RIGHT:
                x += starship.getShipSpeed();
                break;
        }
        this.starship.setX( x );
        this.starship.setY( y );
    }

}
