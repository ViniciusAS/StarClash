package starclash;

import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.Drawable;
import starclash.gui.components.Color;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Text;
import starclash.menu.MenuInterface;

/**
 *
 * @author viniciusas
 */
public class Loading implements Drawable {

    private static final String DEFAULT_TEXT = "Carregando jogo";
    
    private final Text text = new Text(DEFAULT_TEXT, 0.5f, 0.5f, Color.BLACK, "Trebuchet", 20, true);
    
    private int numDots = 0;
    private final Timer timer = new Timer();

    private final Rectangle rect = new Rectangle(0, 0.4f, 1, 0.15f, new Color(1, 1, 1, 0.5f));
    
    public Loading() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                numDots = (numDots+1)%4;
                String t = DEFAULT_TEXT;
                for (int i = 0; i < numDots; i++) {
                    t += ".";
                }
                text.setText(t);
            }
        }, 500, 500);
    }
    
    public void stop(){
        timer.cancel();
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        drawAdaptor.drawImage(MenuInterface.BACKGROUND);
        drawAdaptor.drawRectangle(rect);
        drawAdaptor.drawText(text);
    }

}
