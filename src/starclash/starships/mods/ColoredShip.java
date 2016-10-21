package starclash.starships.mods;

import starclash.gui.components.Color;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;


public class ColoredShip implements StarshipFactory {

    private final Color color;
    private final StarshipFactory starship;

    public ColoredShip(Color color, StarshipFactory starship) {
        this.color = color;
        this.starship = starship;
    }
    
    @Override
    public StarshipDraw newStarshipDraw() {
        return starship.newStarshipDraw();
    }

    @Override
    public StarshipCollision newStarshipCollision() {
        return starship.newStarshipCollision();
    }
    
    @Override
    public StarshipShot newShot() {
        return starship.newShot();
    }

    @Override
    public void doSpecial() {
        starship.doSpecial();
    }

    @Override
    public float getShipSpeed() {
        return starship.getShipSpeed();
    }
    @Override
    public float getX() {
        return starship.getX();
    }

    @Override
    public float getY() {
        return starship.getY();
    }

    @Override
    public void setX(float x) {
        starship.setX(x);
    }

    @Override
    public void setY(float y) {
        starship.setY(y);
    }

    @Override
    public void setShipSpeed(float speed) {
        starship.setShipSpeed(speed);
    }


}
