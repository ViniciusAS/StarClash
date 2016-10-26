package starclash.gui.components;


public class Rectangle implements Component{

    private float x, y, width, height;
    
    private Color color;
    
    public Rectangle() {
    }

    public Rectangle(float x, float y, float width, float height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    @Override
    public boolean contains(Point point){
        return (    point.getX() >= this.x 
                 && point.getY() >= this.y
                 && point.getX() <= this.x + this.width
                 && point.getY() <= this.y + this.height 
               );
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidht() {
        return width;
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
        this.width = widht;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
