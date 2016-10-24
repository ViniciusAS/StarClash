package starclash.menu;

import starclash.gui.DrawAdaptor;
import starclash.gui.Drawable;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Text;


public class Menu implements Drawable {

    private final Image background = new Image("./background.jpg", new Rectangle(0, 0, 1, 1, Color.BLUE));
    
    private final Text title = new Text( "StarClash", 0.5f, 0.20f, Color.WHITE, "Trebuchet", 50, true );
    
    private static final Color selected   = new Color(1, 1, 1, 0.6f);
    private static final Color unselected = new Color(1, 1, 1, 0.4f);
    
    private final Rectangle rects[];
    private final Text texts[];
    
    public Menu(String[] options){
        
        rects = new Rectangle[ options.length ];
        texts = new Text[ options.length ];
        
        for (int i = 0; i < options.length; i++){
            rects[i] = new Rectangle(
                0f,  0.315f + ( 0.10f * i ),
                1f,  0.055f,
                (i==0) ? selected:unselected
            );
            texts[i] = new Text(
                options[i],
                0.5f, 0.37f + ( 0.101f * i ),
                Color.BLACK,
                "Arial",
                20,
                true
            );
        }
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        
        drawAdaptor.drawImage(background);
        
        drawAdaptor.drawText(title);
        
        for (Rectangle rect : rects) {
            drawAdaptor.drawRectangle(rect);
        }
        
        for (Text text : texts) {
            drawAdaptor.drawText(text);
        }
    }

    public void up(){
        for ( int i = 0; i < rects.length; i++ ){
            if ( rects[i].getColor() == selected ){
                rects[i].setColor( unselected );
                rects[ i-1 < 0 ? rects.length-1 : i-1 ].setColor( selected );
                return;
            }
        }
        rects[0].setColor(selected);
    }
    
    public void down(){
        for ( int i = 0; i < rects.length; i++ ){
            if ( rects[i].getColor() == selected ){
                rects[i].setColor( unselected );
                rects[ (i+1) % rects.length ].setColor( selected );
                return;
            }
        }
        rects[0].setColor(selected);
    }
    
    public int getSelected() {
        for (int i = 0; i < rects.length; i++) {
            if ( rects[i].getColor() == selected ){
                rects[i].setColor(unselected);
                rects[0].setColor(selected);
                return i;
            }
        }
        rects[0].setColor(selected);        
        return 0;
    }
    
    
}
