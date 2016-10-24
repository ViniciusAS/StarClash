package starclash.starships.mods;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;


public class FasterShip implements StarshipFactory{

    private final StarshipFactory starship;

    public FasterShip(StarshipFactory starship) {
        this.starship = starship;
    }

    @Override
    public String getName() {
        return starship.getName() + " faster";
    }
    
    @Override
    public boolean isEnemy() {
        return starship.isEnemy();
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
        return starship.getShipSpeed()*1.2f;
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
