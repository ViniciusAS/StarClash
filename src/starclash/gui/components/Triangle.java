package starclash.gui.components;


public class Triangle implements Component{

    private Point p0, p1 ,p2;
    private Color color;

    public Triangle() {
        p0 = new Point();
        p1 = new Point();
        p2 = new Point();
        color = new Color();
    }

    public Triangle(Point p0, Point p1, Point p2, Color color) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }


    @Override
    public boolean contains(Point point){
        
        float s = p0.getY() * p2.getX()
                - p0.getX() * p2.getY()
                + (p2.getY() - p0.getY()) * point.getX()
                + (p0.getX() - p2.getX()) * point.getY();
        
        float t = p0.getX() * p1.getY()
                - p0.getY() * p1.getX()
                + (p0.getY() - p1.getY()) * point.getX()
                + (p1.getX() - p0.getY()) * point.getY();

        if ( (s < 0f) != (t < 0f) )
        {
            return false;
        }
        
        float A = - p1.getY() * p2.getX()
                  + p1.getX() * p2.getY()
                  + p0.getY() * (p2.getX() - p1.getX())
                  + p0.getX() * (p1.getY() - p2.getY());
        if ( A < 0f )
        {
            s = -s;
            t = -t;
            A = -A;
        }
        return ( s > 0f  &&  t > 0f  &&  (s + t) <= A );
    }

    public Point getP0() {
        return p0;
    }
    
    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Color getColor() {
        return color;
    }
    
    public void setP0(Point p0) {
        this.p0 = p0;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void setColor(Color color) {
        this.color = color;
    }
        
}
