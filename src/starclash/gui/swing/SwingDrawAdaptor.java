package starclash.gui.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import starclash.gui.DrawAdaptor;
import starclash.gui.components.Component;
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

    private Graphics2D graphics;
    private final JPanel panel;

    public SwingDrawAdaptor(JPanel panel) {
       this.panel = panel;
    }

    public SwingDrawAdaptor(JPanel panel, Graphics2D graphics) {
        this.graphics = graphics;
        this.panel = panel;
    }
    
    private float getWidth(){
        return panel.getWidth();
    }
    
    private float getHeight(){
        return panel.getHeight();
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }
    
    private void setColor(starclash.gui.components.Color color){
        graphics.setColor(new Color(
            color.getR(),
            color.getG(),
            color.getB(),
            color.getA()
        ));
    }

    @Override
    public void drawComponent(Component component) {
        if( component instanceof Triangle ){
            this.drawTriangle((Triangle) component);
        }
        if( component instanceof Rectangle ){
            drawRectangle((Rectangle) component);
        }
        if( component instanceof Line ){
            drawLine((Line) component);
        }
        if( component instanceof Image ){
            drawImage((Image) component);
        }
    }
    
    @Override
    public void drawRectangle(Rectangle rectangle) {
        setColor(rectangle.getColor());
        graphics.fillRect(
            (int) ( rectangle.getX()* getWidth() ),
            (int) ( rectangle.getY()* getHeight() ),
            (int) ( rectangle.getWidht()* getWidth() ),
            (int) ( rectangle.getHeight()* getHeight() )
        );
    }

    @Override
    public void drawTriangle(Triangle triangle) {
        setColor(triangle.getColor());
        graphics.fillPolygon(new Polygon(
            new int[]{
                (int) ( triangle.getP0().getX()*getWidth() ),
                (int) ( triangle.getP1().getX()*getWidth() ),
                (int) ( triangle.getP2().getX()*getWidth() )
            }, new int[]{
                (int) ( triangle.getP0().getY()*getHeight() ),
                (int) ( triangle.getP1().getY()*getHeight() ),
                (int) ( triangle.getP2().getY()*getHeight() )
            }, 3)
        );
    }

    @Override
    public void drawImage(Image image) {
        setColor(image.getRectangle().getColor());
        graphics.fillRect(
            (int) ( image.getRectangle().getX()*getWidth() ),
            (int) ( image.getRectangle().getY()*getHeight() ),
            (int) ( image.getRectangle().getWidht()*getWidth() ),
            (int) ( image.getRectangle().getHeight()*getHeight() )
        );
        try {
            graphics.drawImage(
                ImageIO.read(new File(image.getFilename())),
                0, 0,
                (int) getWidth(),
                (int) getHeight(),
                panel
            );
        } catch (Exception ex) {}
    }

    @Override
    public void drawLine(Line line) {
        setColor(line.getColor());
        graphics.drawLine(
            (int) ( line.getP0().getX()*getWidth() ),
            (int) ( line.getP0().getY()*getHeight() ),
            (int) ( line.getP1().getX()*getWidth() ),
            (int) ( line.getP1().getY()*getHeight() )
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
                (int) (text.getX()*getWidth()-textWidth/2),
                (int) (text.getY()*(getHeight())-text.getFontSize()/2)
        );
        
    }

    @Override
    public void setRotate(Component component) {
        
        Graphics2D g = (Graphics2D) component;
        g.rotate(180);
        
        if(component instanceof Triangle){
            ((Triangle) component).getP0().setX(((Triangle) component).getP0().getX()+0.3f);
            ((Triangle) component).getP0().setY(((Triangle) component).getP0().getY()+0.3f);
            drawTriangle((Triangle)component);
        }
        


    }
    
}
