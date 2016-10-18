package starclash.starships.mods;

import starclash.gui.Color;
import starclash.starships.StarshipFactory;

/**
 *
 * @author Vinicius Santos
 */
public class ColoredShip implements StarshipFactory {

    private final Color color;
    private final StarshipFactory starship;

    public ColoredShip(Color color, StarshipFactory starship) {
        this.color = color;
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

}
