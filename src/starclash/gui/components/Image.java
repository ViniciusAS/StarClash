package starclash.gui.components;


public class Image {

    private String filename;
    private Rectangle rectangle;

    public Image() {
    }

    public Image(String filename, Rectangle rectangle) {
        this.filename = filename;
        this.rectangle = rectangle;
    }
    
    public boolean contains(Point point){
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
