package starclash.gui.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import starclash.gui.DrawAdaptor;
import starclash.gui.components.Component;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Text;
import starclash.gui.components.Triangle;
import starclash.starships.StarshipComponents;

/**
 * @author Vinicius Santos
 */
public class SwingDrawAdaptor implements DrawAdaptor {

    private Graphics2D graphics;
    private final JPanel panel;

    public SwingDrawAdaptor(JPanel panel) {
        this.panel = panel;
    }

    public SwingDrawAdaptor(JPanel panel, Graphics2D graphics) {
        this.graphics = graphics;
        this.panel = panel;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private float getWidth() {
        return panel.getWidth();
    }

    private float getHeight() {
        return panel.getHeight();
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    private void setColor(starclash.gui.components.Color color) {
        graphics.setColor(new Color(
                color.getR(),
                color.getG(),
                color.getB(),
                color.getA()
        ));
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void drawComponent(Component component) {
        if (component instanceof Triangle) {
            this.drawTriangle((Triangle) component);
        }
        if (component instanceof Rectangle) {
            drawRectangle((Rectangle) component);
        }
        if (component instanceof Line) {
            drawLine((Line) component);
        }
        if (component instanceof Image) {
            drawImage((Image) component);
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void drawRectangle(Rectangle rectangle) {
        setColor(rectangle.getColor());
        graphics.fillRect(
                (int) (rectangle.getX() * getWidth()),
                (int) (rectangle.getY() * getHeight()),
                (int) (rectangle.getWidht() * getWidth()),
                (int) (rectangle.getHeight() * getHeight())
        );
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void drawTriangle(Triangle triangle) {
        setColor(triangle.getColor());
        graphics.fillPolygon(new Polygon(
                        new int[]{
                                (int) (triangle.getP0().getX() * getWidth()),
                                (int) (triangle.getP1().getX() * getWidth()),
                                (int) (triangle.getP2().getX() * getWidth())
                        }, new int[]{
                        (int) (triangle.getP0().getY() * getHeight()),
                        (int) (triangle.getP1().getY() * getHeight()),
                        (int) (triangle.getP2().getY() * getHeight())
                }, 3)
        );
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private final Map<String, java.awt.Image> images = new HashMap<>();

    private java.awt.Image loadImage(Image image) {
        java.awt.Image loadedImage = images.get(image.getFilename());

        if (loadedImage == null) {
            try {
                loadedImage = ImageIO.read(getClass().getResourceAsStream(image.getFilename()));
                images.put(image.getFilename(), loadedImage);
            } catch (IOException ex) {
                Logger.getLogger(SwingDrawAdaptor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return loadedImage;
    }

    @Override
    public void drawImage(Image image) {
        setColor(image.getRectangle().getColor());
        graphics.fillRect(
                (int) (image.getRectangle().getX() * getWidth()),
                (int) (image.getRectangle().getY() * getHeight()),
                (int) (image.getRectangle().getWidht() * getWidth()),
                (int) (image.getRectangle().getHeight() * getHeight())
        );

        if (image.isInverted()) {
            graphics.drawImage(
                    loadImage(image),
                    (int) (image.getRectangle().getX() * getWidth()),
                    (int) (((image.getRectangle().getY() + image.getRectangle().getHeight()) * getHeight())),
                    (int) (image.getRectangle().getWidht() * getWidth()),
                    (int) -(image.getRectangle().getHeight() * getHeight()),
                    panel
            );
        } else {
            graphics.drawImage(
                    loadImage(image),
                    (int) (image.getRectangle().getX() * getWidth()),
                    (int) (image.getRectangle().getY() * getHeight()),
                    (int) (image.getRectangle().getWidht() * getWidth()),
                    (int) (image.getRectangle().getHeight() * getHeight()),
                    panel
            );
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void drawLine(Line line) {
        setColor(line.getColor());
        graphics.drawLine(
                (int) (line.getP0().getX() * getWidth()),
                (int) (line.getP0().getY() * getHeight()),
                (int) (line.getP1().getX() * getWidth()),
                (int) (line.getP1().getY() * getHeight())
        );
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void drawText(Text text) {
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        setColor(text.getColor());

        graphics.setFont(
                new Font(
                        text.getFontFamily(),
                        text.isBold() ? Font.BOLD : Font.PLAIN,
                        text.getFontSize()
                )
        );

        int textWidth = graphics.getFontMetrics().stringWidth(text.getText());

        graphics.drawString(
                text.getText(),
                (int) (text.getX() * getWidth() - textWidth / 2),
                (int) (text.getY() * (getHeight()) - text.getFontSize() / 2)
        );

    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void setRotate(Component component, StarshipComponents starship) {

        if (component instanceof Triangle) {
            ((Triangle) component).getP0().setX(((Triangle) component).getP0().getX());
            ((Triangle) component).getP0().setY(((Triangle) component).getP0().getY() + starship.getHeigth());
            drawTriangle((Triangle) component);
        }
        if (component instanceof Rectangle) {
            ((Rectangle) component).setY((((Rectangle) component).getY() - ((Rectangle) component).getHeight()));
            drawRectangle((Rectangle) component);
        }
        if (component instanceof Image) {
            AffineTransform identity = new AffineTransform();
            AffineTransform trans = new AffineTransform();
            trans.setTransform(identity);
            trans.rotate(Math.toRadians(180));
            graphics.drawImage(loadImage((Image) component), trans, panel);
        }
        if (component instanceof Line) {
            drawLine((Line) component);
        }

    }

}
