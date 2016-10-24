package starclash;

import starclash.gui.DrawAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import sun.java2d.pipe.DrawImage;

/**
 *
 * @author samuel
 */
public class Scenario {
    
    public static Image img;
    public static Color color;

    public Scenario(Image img, Color color) {
        this.img = img;
        this.color = color;
    }
    
    public void DrawImage(DrawAdaptor draw){
        draw.drawImage(img);
    }
}
