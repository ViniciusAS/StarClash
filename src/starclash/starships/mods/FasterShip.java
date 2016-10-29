package starclash.starships.mods;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;


public class FasterShip extends StarshipFactory{

    private final StarshipFactory starship;

    public FasterShip(StarshipFactory starship) {
        this.starship = starship;
    }

    @Override
    public String getName() {
        return starship.getName() + " faster";
    }

    @Override
    public StarshipFactory getNext() {
        return starship;
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
    public StarshipShot newShot(float x, float y) {
        return starship.newShot(x, y);
    }

    @Override
    public float getLifePercent() {
        return starship.getLifePercent();
    }
    
    @Override
    public boolean takeDamage(StarshipShot shot) {
        return starship.takeDamage(shot);
    }

    @Override
    public void doSpecial() {
        starship.doSpecial();
    }

    @Override
    public void doSpecial(float x, float y) {
        starship.doSpecial(x, y);
    }

    @Override
    public float getShipSpeed() {
        return starship.getShipSpeed()*1.2f;
    }

    @Override
    public float getWidth() {
        return starship.getWidth();
    }

    @Override
    public float getHeight() {
        return starship.getHeight();
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
