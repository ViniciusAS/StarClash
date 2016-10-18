package starclash;

import starclash.gui.Color;
import starclash.gui.GameInterfaceLWJGL;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.GameInterfaceAdaptor.Key;
import starclash.gui.GameInterfaceAdaptor.KeyListener;
import starclash.gui.Rectangle;


/**
 *
 * @author viniciusas
 */
public class StarClash {

    private final GameInterfaceAdaptor gui;
    
    private final Rectangle rect = new Rectangle(0, 0, 0, 0, Color.BLACK);
    
    public StarClash() {
        gui = new GameInterfaceLWJGL(rect);
    }
    
    /** 
     * Inicia os servicos
     * 
     */
    public void run(){
        gui.start();
        gui.addKeyListener(new KeyListener(Key.KEY_UP) {
            @Override
            public void clicked() {
                rect.setY( rect.getY()-1 );
            }
        });
        gui.addKeyListener(new KeyListener(Key.KEY_DOWN) {
            @Override
            public void clicked() {
                rect.setY( rect.getY()+1 );
            }
        });
        gui.addKeyListener(new KeyListener(Key.KEY_LEFT) {
            @Override
            public void clicked() {
                rect.setX( rect.getX()-1 );
            }
        });
        gui.addKeyListener(new KeyListener(Key.KEY_RIGHT) {
            @Override
            public void clicked() {
                rect.setX( rect.getX()+1 );
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StarClash().run();
    }
    
}
