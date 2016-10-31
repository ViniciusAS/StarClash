package starclash.starships.theincredablestarship;

import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

public class TheIncredableStarshipCollision implements StarshipCollision {

    private final StarshipComponents components;
    private final boolean enemy;
    
    public TheIncredableStarshipCollision(StarshipFactory starship ,StarshipComponents components) {
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
        return ( enemyShip.isEnemy() && (
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
        );
    }

}
