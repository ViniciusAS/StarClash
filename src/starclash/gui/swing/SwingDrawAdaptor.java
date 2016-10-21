package starclash.gui.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import starclash.gui.DrawAdaptor;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Triangle;

/**
 *
 * @author Vinicius Santos
 */
public class SwingDrawAdaptor implements DrawAdaptor {

    private Graphics graphics;

    public SwingDrawAdaptor() {
    }

    public SwingDrawAdaptor(Graphics graphics) {
        this.graphics = graphics;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }    
    
    @Override
    public void drawRectangle(Rectangle rectangle) {
        graphics.setColor(new Color(
                rectangle.getColor().getR(),
                rectangle.getColor().getG(),
                rectangle.getColor().getB(),
                rectangle.getColor().getA()
        ));
        graphics.fillRect(
            (int) ( rectangle.getX() ),
            (int) ( rectangle.getY() ),
            (int) ( rectangle.getWidht() ),
            (int) ( rectangle.getHeight() )
        );
    }

    @Override
    public void drawTriangle(Triangle triangle) {
        graphics.setColor(new Color(
                triangle.getColor().getR(),
                triangle.getColor().getG(),
                triangle.getColor().getB(),
                triangle.getColor().getA()
        ));
        graphics.fillPolygon(new Polygon(
            new int[]{
                (int) triangle.getP0().getX(),
                (int) triangle.getP1().getX(),
                (int) triangle.getP2().getX()
            }, new int[]{
                (int) triangle.getP0().getY(),
                (int) triangle.getP1().getY(),
                (int) triangle.getP2().getY()
            }, 3)
        );
    }

    @Override
    public void drawImage(Image image) {
        graphics.setColor(new Color(
                image.getRectangle().getColor().getR(),
                image.getRectangle().getColor().getG(),
                image.getRectangle().getColor().getB(),
                image.getRectangle().getColor().getA()
        ));
        graphics.fillRect(
            (int) ( image.getRectangle().getX() ),
            (int) ( image.getRectangle().getY() ),
            (int) ( image.getRectangle().getWidht() ),
            (int) ( image.getRectangle().getHeight() )
        );
    }

    @Override
    public void drawLine(Line line) {
        graphics.setColor(new Color(
                line.getColor().getR(),
                line.getColor().getG(),
                line.getColor().getB(),
                line.getColor().getA()
        ));
        graphics.drawLine(
            (int) ( line.getP0().getX() ),
            (int) ( line.getP0().getY() ),
            (int) ( line.getP1().getX() ),
            (int) ( line.getP1().getY() )
        );
    }
    
}
