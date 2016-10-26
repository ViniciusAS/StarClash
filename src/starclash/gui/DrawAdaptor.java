package starclash.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import starclash.gui.components.Component;
import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Text;
import starclash.gui.components.Triangle;
import starclash.starships.StarshipComponents;


public interface DrawAdaptor {

    public void drawComponent(Component component);
    
    public void drawRectangle(Rectangle rectangle);
    
    public void drawTriangle(Triangle triangle);
    
    public void drawImage(Image image);
    
    public void drawLine(Line line);
    
    public void drawText(Text text);
    public void setRotate(Component component, StarshipComponents starship);
    
}
