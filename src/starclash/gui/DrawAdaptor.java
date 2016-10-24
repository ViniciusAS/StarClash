package starclash.gui;

import starclash.gui.components.Image;
import starclash.gui.components.Line;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Text;
import starclash.gui.components.Triangle;


public interface DrawAdaptor {

    public void drawRectangle(Rectangle rectangle);
    
    public void drawTriangle(Triangle triangle);
    
    public void drawImage(Image image);
    
    public void drawLine(Line line);
    
    public void drawText(Text text);
    
}
