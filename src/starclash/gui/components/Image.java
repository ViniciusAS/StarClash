package starclash.gui.components;


public class Image implements Component {

    private String filename;
    private Rectangle rectangle;
    private boolean inverted = false;

    public Image() {
    }

    public Image(String filename, Rectangle rectangle) {
        this.filename = filename;
        this.rectangle = rectangle;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public boolean isInverted() {
        return inverted;
    }

    @Override
    public boolean contains(Point point) {
        return this.rectangle.contains(point);
    }

    public String getFilename() {
        return filename;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

}
