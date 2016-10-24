package starclash.gui.components;

/**
 *
 * @author Vinicius Santos
 */
public class Text {

    private String text;
    private float x, y;
    private Color color;
    private String fontFamily;
    private int fontSize;
    private boolean bold;

    public Text() {
    }
    
    public Text(String text, float x, float y, Color color, String fontFamily, int fontSize, boolean bold) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.bold = bold;
    }
    
    public String getText() {
        return text;
    }
    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public Color getColor() {
        return color;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public boolean isBold() {
        return bold;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
}
