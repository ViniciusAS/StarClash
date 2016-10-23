package starclash.gui.components;


public class Point implements Component{
    
    private float x, y;

    public Point() {
        x = y = 0f;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean contains(Point point) {
       float precision = 1000;
       return ((int)(x*precision)==(int)(point.getX()*precision) && 
               (int)(y*precision)==(int)(point.getY()*precision));
    }
        
}
