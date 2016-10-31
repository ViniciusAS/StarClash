package starclash.starships.o_rangestarship;

import starclash.gui.components.Rectangle;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class ORangeStarshipCollision implements StarshipCollision {

    private final StarshipComponents components;
    private final boolean enemy;
    
    public ORangeStarshipCollision(StarshipFactory starship, StarshipComponents components) {
        this.components = components;
        this.enemy = starship.isEnemy();
    }

    @Override
    public float wallCollisionHorizontalFilter(float x)
    {
        // left
        if ( x <= 0 )
            return 0;
        // right
        if ( x + components.getWidth() >= 1 )
            return 1 - components.getWidth();
        // nothing
        return x;
    }

    @Override
    public float wallCollisionVerticalFilter(float y)
    {
        float topLimit = ( enemy ) ? 0 : 0.5f;
        float botLimit = ( enemy ) ? 0.5f : 1;
        botLimit -= components.getHeigth();
        // top
        if ( y <= topLimit )
            return topLimit;
        // bottom
        if ( y >= botLimit )
            return botLimit;
        // nothing
        return y;
    }
    
    
    @Override
    public boolean shotCollision(StarshipShot shot, StarshipFactory enemyShip, StarshipComponents components) {
        if ( !(shot instanceof ORangeStarshipShot) )
            return false;
        
        ORangeStarshipShot orshot = ((ORangeStarshipShot) shot);
        
        Rectangle eShot = orshot.getSpecialRect();
        
        return (
            // se NAO e especial
            !orshot.isSpecial() && (
                enemyShip.isEnemy() && (
                    // logica utilizando a nave acima
                    (enemyShip.getY()+enemyShip.getHeight()) >= shot.getY() // se ja chegou: eixo Y
                    && (shot.getY()+shot.getSize()) >= enemyShip.getY() // se nao passou: eixo Y
                    && shot.getX() >= enemyShip.getX()  // se nao esta pra esquerda: eixo X
                    && shot.getX() <= enemyShip.getX()+enemyShip.getWidth()  // se nao esta pra direita: eixo X
                ) || 
                !enemyShip.isEnemy() && (
                    // logica utilizando a nave abaixo
                    enemyShip.getY() <= shot.getY()+shot.getSize() // se ja chegou: eixo Y
                    && shot.getY() <= enemyShip.getY()+enemyShip.getHeight() // se nao passou: eixo Y
                    && shot.getX() >= enemyShip.getX()  // se nao esta pra esquerda: eixo X
                    && shot.getX() <= enemyShip.getX()+enemyShip.getWidth()  // se nao esta pra direita: eixo X
                )
            )
            || 
            // se E especial
            orshot.isSpecial() && (
                enemyShip.isEnemy() && (
                    // logica utilizando a nave acima
                    (enemyShip.getY()+enemyShip.getHeight()) <= eShot.getY() // se ja chegou: eixo Y
                    && (eShot.getY()+eShot.getHeight()) >= enemyShip.getY() // se nao passou: eixo Y
                    && eShot.getX()+eShot.getWidth() >= enemyShip.getX()  // se nao esta pra esquerda: eixo X
                    && eShot.getX() <= enemyShip.getX()+enemyShip.getWidth()  // se nao esta pra direita: eixo X
                ) || 
                !enemyShip.isEnemy() && (
                    // logica utilizando a nave abaixo
                    enemyShip.getY() <= eShot.getY()+eShot.getHeight() // se ja chegou: eixo Y
                    && eShot.getY() <= enemyShip.getY()+enemyShip.getHeight() // se nao passou: eixo Y
                    && eShot.getX()+eShot.getWidth() >= enemyShip.getX()  // se nao esta pra esquerda: eixo X
                    && eShot.getX() <= enemyShip.getX()+enemyShip.getWidth()  // se nao esta pra direita: eixo X
                )
            )
        );
    }

}
