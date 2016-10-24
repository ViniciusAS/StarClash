package starclash.gui.components;

/**
 *
 * @author Vinicius Santos
 */
public class Line implements Component{

    private Point p0, p1;
    private Color color;

    public Line() {
        p0 = new Point();
        p1 = new Point();
        color = new Color();
    }

    public Line(Point p0, Point p1, Color color) {
        this.p0 = p0;
        this.p1 = p1;
        this.color = color;
    }

    public Point getP0() {
        return p0;
    }

    public Point getP1() {
        return p1;
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

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean contains(Point point) {
        float precision = 100;
        for(float x=p0.getX(); x<p1.getX(); x+=1/precision){
            float y= p0.getY()+ ((p1.getY()-p0.getY())/(p1.getX()-p0.getY()))*(1/precision) *x;
            
            int px = (int) Math.round(point.getX()*precision);
            int py = (int) Math.round(point.getY()*precision);
            int lx = (int) Math.round(x*precision);
            int ly = (int) Math.round(y*precision);
            
            if(px == lx && py == ly){
                return true;    
            }
                
        }
        
        return false;
    }
    
}
