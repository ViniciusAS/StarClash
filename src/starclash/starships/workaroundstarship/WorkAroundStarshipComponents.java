package starclash.starships.workaroundstarship;

import starclash.gui.components.*;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipFactory;

/**
 * @author samuel
 */
public class WorkAroundStarshipComponents implements StarshipComponents {

    private static final float WIDTH = 0.05f;
    private static final float HEIGHT = 0.05f;
    private final StarshipFactory starship;
    private final Image image = new Image("/resources/workaroundstarship/ship.png",
            new Rectangle(0, 0, WIDTH, -HEIGHT, Color.TRANSPARENT));

    public WorkAroundStarshipComponents(StarshipFactory starship) {
        this.starship = starship;
    }

    @Override
    public float getWidth() {
        return WIDTH;
    }

    @Override
    public float getHeigth() {
        return HEIGHT;
    }


    @Override
    public Component[] getComponents() {
        return new Component[]{
                image
        };
    }


}
