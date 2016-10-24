package starclash.gamemode;


import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.Movement;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipFactory;


public class StarshipMovementListener implements MoveListener {

    private final StarshipFactory starship;
    private final StarshipCollision collision;

    public StarshipMovementListener(StarshipFactory starship) {
        this.starship = starship;
        this.collision = starship.newStarshipCollision();
    }
    
    @Override
    public void moved(Movement movement)
    {
        float x = starship.getX();
        float y = starship.getY();
        
        switch (movement){
            case UP:    y -= starship.getShipSpeed(); break;
            case DOWN:  y += starship.getShipSpeed(); break;
            case LEFT:  x -= starship.getShipSpeed(); break;
            case RIGHT: x += starship.getShipSpeed(); break;
        }
        
        x = collision.wallCollisionHorizontalFilter(x);
        y = collision.wallCollisionVerticalFilter(y);
        
        this.starship.setX( x );
        this.starship.setY( y );
    }

    @Override
    public void moved(float x, float y) {
        this.starship.setX(x);
        this.starship.setY(y);
    }

}
