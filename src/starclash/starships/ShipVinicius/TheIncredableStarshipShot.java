package starclash.starships.ShipVinicius;

import starclash.gui.DrawAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class TheIncredableStarshipShot implements StarshipShot {

    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        drawAdaptor.drawLine(new Line(new Point(0, 0), new Point(10,10), Color.BLACK));
    }

}
