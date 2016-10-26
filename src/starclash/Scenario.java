package starclash;

import starclash.gui.DrawAdaptor;
import starclash.gui.Drawable;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Point;
import starclash.gui.components.Rectangle;

/**
 *
 * @author samuel
 */
public class Scenario implements Drawable{
    
    public static final Scenario scenarioDefault = new Scenario(new Image("/resources/backgrounds/space.jpg", new Rectangle(0, 0, 1, 1, Color.BLUE)),Color.WHITE);
    public final Image img;
    public final Color color;
    public final Line line = new Line(new Point(0, 0.5f), new Point(1, 0.5f),Color.WHITE);

   
    public Scenario(Image img, Color color) {
        this.img = img;
        this.color = color;
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        drawAdaptor.drawImage(img);
        drawAdaptor.drawLine(line);
    }
}
