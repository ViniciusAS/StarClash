package starclash.gui;

/**
 *
 * @author Vinicius Santos
 */
public class Rectangle {

    private float x, y, widht, height;
    
    private Color color;
    
    public Rectangle() {
    }

    public Rectangle(float x, float y, float widht, float height, Color color) {
        this.x = x;
        this.y = y;
        this.widht = widht;
        this.height = height;
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidht() {
        return widht;
    }
    
    public float getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
    
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidht(float widht) {
        this.widht = widht;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
