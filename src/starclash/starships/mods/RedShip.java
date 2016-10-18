package starclash.starships.mods;

import starclash.starships.StarshipFactory;

/**
 *
 * @author Vinicius Santos
 */
public class RedShip implements StarshipFactory {

    private final StarshipFactory starship;

    public RedShip(StarshipFactory starship) {
        this.starship = starship;
    }

    @Override
    public void newStarshipDraw() {
        starship.newStarshipDraw();
    }

    @Override
    public void newShot() {
        starship.newShot();
    }

    @Override
    public void newSpecial() {
        starship.newSpecial();
    }

    @Override
    public float getShipSpeed() {
        return starship.getShipSpeed();
    }

    @Override
    public void newStarshipCollision() {
    }

}
