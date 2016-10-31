package starclash;

import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.Drawable;
import starclash.gui.components.Color;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Text;

/**
 *
 * @author viniciusas
 */
public class BattleEnd implements Drawable {

    private final Rectangle rect = new Rectangle(0, 0.4f, 1, 0.15f, Color.WHITE);
    private final Text text = new Text("", 0.5f, 0.5f, Color.BLACK, "Trebuchet", 20, true);

    private final Timer timer = new Timer();
    
    private int cont = 0;
    
    public BattleEnd(String text, final Color textColor, final Color rectColor) {
        this.text.setText(text);
        
        final float transpText = textColor.getA();
        final float transpRect = rectColor.getA();
        textColor.setA(0);
        rectColor.setA(0);
        
        this.text.setColor(textColor);
        this.rect.setColor(rectColor);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if ( cont == 100 ){
                    timer.cancel();
                }
                textColor.setA( transpText * ( cont/100f ) );
                rectColor.setA( transpRect * ( cont/100f ) );
                cont++;
            }
        }, 100, 100);
    }
    
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        drawAdaptor.drawRectangle(rect);
        drawAdaptor.drawText(text);
    }

}
