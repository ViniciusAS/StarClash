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
        int width = 1080;
        int height = 720;
        float x = starship.getX();
        float y = starship.getY();
        switch (movement){
            case UP:
                if(y>0)y -= starship.getShipSpeed();
                else y=1;
                break;
            case DOWN:
                if(y<=height-50)y += starship.getShipSpeed();
                else y--;
                break;
            case LEFT:
                if(x>0) x -= starship.getShipSpeed();
                else x=1;
                break;
            case RIGHT:
                if(x<width-10) x += starship.getShipSpeed();
                else x--;
                break;
        }
        this.starship.setX( x );
        this.starship.setY( y );
    }

}
