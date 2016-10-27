package starclash.gui.components;

/**
 *
 * @author Vinicius Santos
 */
public class Color {

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    
    public static final Color BLACK = new Color(0, 0, 0, 1);
    public static final Color WHITE = new Color(1, 1, 1, 1);
    public static final Color GREEN = new Color(0, 1, 0, 1);
    public static final Color BLUE  = new Color(0, 0, 1, 1);
    public static final Color RED   = new Color(1, 0, 0, 1);
    public static final Color ORANGE   = new Color(0.255f, 0.069f, 0, 1);
    public static final Color YELLOW   = new Color(0.255f, 0.215f, 0, 1);
    public static final Color BABY_BLUE   = new Color(0, 0.255f, 0.255f, 1);
    public static final Color VIOLET   = new Color(0.148f, 0, 0.211f, 1);
    
    
    private float r, g, b, a;

    public Color() {
        r = g = b = a = 0;
    }

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

    public float getG() {
        return g;
    }

    public float getR() {
        return r;
    }

    public void setA(float a) {
        this.a = a;
    }

    public void setB(float b) {
        this.b = b;
    }

    public void setG(float g) {
        this.g = g;
    }

    public void setR(float r) {
        this.r = r;
    }
    
}
