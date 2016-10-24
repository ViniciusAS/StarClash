package starclash.gui.swing;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import starclash.gui.DrawAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Text;
import starclash.gui.components.Triangle;

/**
 *
 * @author Vinicius Santos
 */
public class SwingDrawAdaptor implements DrawAdaptor {

    private Graphics graphics;
    private final JFrame frame;

    public SwingDrawAdaptor(JFrame frame) {
       this.frame = frame;
    }

    public SwingDrawAdaptor(JFrame frame, Graphics graphics) {
        this.graphics = graphics;
        this.frame = frame;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }    
    
    private void setColor(Color color){
        graphics.setColor(new java.awt.Color(
            color.getR(),
            color.getG(),
            color.getB(),
            color.getA()
        ));
    }
    
    @Override
    public void drawRectangle(Rectangle rectangle) {
        setColor(rectangle.getColor());
        graphics.fillRect(
            (int) ( rectangle.getX()*frame.getWidth() ),
            (int) ( rectangle.getY()*frame.getHeight() ),
            (int) ( rectangle.getWidht()*frame.getWidth() ),
            (int) ( rectangle.getHeight()*frame.getHeight() )
        );
    }

    @Override
    public void drawTriangle(Triangle triangle) {
        setColor(triangle.getColor());
        graphics.fillPolygon(new Polygon(
            new int[]{
                (int) triangle.getP0().getX()*frame.getWidth(),
                (int) triangle.getP1().getX()*frame.getWidth(),
                (int) triangle.getP2().getX()*frame.getWidth()
            }, new int[]{
                (int) triangle.getP0().getY()*frame.getHeight(),
                (int) triangle.getP1().getY()*frame.getHeight(),
                (int) triangle.getP2().getY()*frame.getHeight()
            }, 3)
        );
    }

    @Override
    public void drawImage(Image image) {
        setColor(image.getRectangle().getColor());
        graphics.fillRect(
            (int) ( image.getRectangle().getX()*frame.getWidth() ),
            (int) ( image.getRectangle().getY()*frame.getHeight() ),
            (int) ( image.getRectangle().getWidht()*frame.getWidth() ),
            (int) ( image.getRectangle().getHeight()*frame.getHeight() )
        );
        try {
            graphics.drawImage(ImageIO.read(new File(image.getFilename())), 0, 0, frame.getWidth(), frame.getHeight(), frame);
        } catch (IOException ex) {}
    }

    @Override
    public void drawLine(Line line) {
        setColor(line.getColor());
        graphics.drawLine(
            (int) ( line.getP0().getX() ),
            (int) ( line.getP0().getY() ),
            (int) ( line.getP1().getX() ),
            (int) ( line.getP1().getY() )
        );
    }

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
                (int) (text.getX()*frame.getWidth()-textWidth/2),
                (int) (text.getY()*(frame.getHeight()-28)-text.getFontSize()/2)+28
        );
        
    }
    
}
