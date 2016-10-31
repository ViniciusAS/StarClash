package starclash.starships.workaroundstarship;

import starclash.starships.*;


public class WorkAroundStarship extends StarshipFactory {

    private float x, y;
    private float speed = 0.01f;
    private final boolean enemy;
    private final StarshipComponents components;

    public WorkAroundStarship(boolean enemy) {
        this.enemy = enemy;
        this.components = new WorkAroundStarshipComponents(this);

        x = 0.5f - components.getWidth() / 2;
        y = (enemy) ? 0.25f : 0.75f;
        y -= components.getHeigth() / 2;
    }

    public WorkAroundStarship() {
        this(false);
    }

    @Override
    public String getName() {
        return "WorkAround Starship";
    }

    @Override
    public StarshipFactory getNext() {
        return null;
    }

    @Override
    public boolean isEnemy() {
        return this.enemy;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public float getManaPercent() {
        return 1;
    }

    @Override
    public float getLifePercent() {
        return 1;
    }
    
    @Override
    public boolean takeDamage(int damage) {
        super.notifyDie();
        return true;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public StarshipDraw newStarshipDraw() {
        return new WorkAroundStarshipDraw(components, this);
    }

    @Override
    public StarshipCollision newStarshipCollision() {
        return new WorkAroundStarshipCollision(this, components);
    }

    @Override
    public StarshipShot newShot() {
        return new WorkAroundStarshipShot(this, components);
    }

    @Override
    public StarshipShot newShot(float x, float y) {
        return new WorkAroundStarshipShot(this, components);
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public boolean doSpecial() {
        return false;
    }

    @Override
    public boolean doSpecial(float x, float y) {
        return false;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public float getShipSpeed() {
        return speed;
    }

    @Override
    public float getWidth() {
        return components.getWidth();
    }

    @Override
    public float getHeight() {
        return components.getHeigth();
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void setShipSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }


}
