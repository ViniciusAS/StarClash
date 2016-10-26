package starclash.starships.workaroundstarship;

import starclash.starships.*;


public class WorkAroundStarship implements StarshipFactory {

    private float x, y;
    private float speed = 0.01f;
    private final boolean enemy;
    private final StarshipComponents components = new WorkAroundStarshipComponents(this);

    public WorkAroundStarship(boolean enemy) {
        x = 0.5f - components.getWidth() / 2;
        y = (enemy) ? 0.25f : 0.75f;
        y -= components.getHeigth() / 2;
        this.enemy = enemy;
    }

    public WorkAroundStarship() {
        x = 0.5f - components.getWidth() / 2;
        y = 0.75f - components.getHeigth() / 2;
        enemy = false;
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

    private DieListener dieListener;

    @Override
    public void setDieListener(DieListener dieListener) {
        this.dieListener = dieListener;
    }

    @Override
    public boolean takeDamage(StarshipShot shot) {
        ///// NOT INVENCIBLE /////
        if (dieListener != null) {
            dieListener.dead();
        }
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
        return new WorkAroundStarshipShot(this, newStarshipCollision(), components);
    }

    @Override
    public StarshipShot newShot(float x, float y) {
        return new WorkAroundStarshipShot(this, newStarshipCollision(), components);
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void doSpecial() {
    }

    @Override
    public void doSpecial(float x, float y) {
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
