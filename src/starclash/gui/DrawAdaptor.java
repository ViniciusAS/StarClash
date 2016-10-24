package starclash.gui;


import java.awt.Graphics;
import java.awt.Graphics2D;
import starclash.gui.components.Component;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Triangle;


public interface DrawAdaptor {

    public void drawComponent(Component component);
    
    public void drawRectangle(Rectangle rectangle);
    
    public void drawTriangle(Triangle triangle);
    
    public void drawImage(Image image);
    
    public void drawLine(Line line);
    
    public void setRotate(Component component);
    
}
