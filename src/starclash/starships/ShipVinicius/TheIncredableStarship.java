package starclash.starships.ShipVinicius;

import starclash.gui.GameInterfaceAdaptor;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;


public class TheIncredableStarship implements StarshipFactory {

    private float x, y;
    private float speed = 0.01f;
    private boolean enemy;
    private final StarshipComponents components = new TheIncreadableStarshipComponents(this);
    private final GameInterfaceAdaptor gui;
    
    
    public TheIncredableStarship(GameInterfaceAdaptor gui, boolean enemy) {
        x = 0.5f;
        y = ( enemy ) ? 0.25f : 0.75f;
        this.enemy = enemy;
        this.gui = gui;
    }
    
    public TheIncredableStarship(GameInterfaceAdaptor gui) {
        x = 0.5f;
        y = 0.75f;
        enemy = false;
        this.gui = gui;
    }

    @Override
    public String getName() {
        return "The Incredable Starship";
    }

    @Override
    public boolean isEnemy() {
        return this.enemy;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public StarshipDraw newStarshipDraw() {
        return new TheIncredableStarshipDraw( components, this );
    }

    @Override
    public StarshipCollision newStarshipCollision() {
        return new TheIncredableStarshipCollision( this , components);        
    }

    @Override
    public StarshipShot newShot() {
        return new TheIncredableStarshipShot(gui,this,newStarshipCollision());
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void doSpecial() {
        
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public float getShipSpeed() {
        return speed;
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
