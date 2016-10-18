package starclash;

import starclash.gui.Color;
import starclash.gui.GameInterfaceLWJGL;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.GameInterfaceAdaptor.Key;
import starclash.gui.GameInterfaceAdaptor.KeyListener;
import starclash.gui.GameInterfaceSwing;
import starclash.gui.Rectangle;


/**
 *
 * @author viniciusas
 */
public class StarClash {

    private final GameInterfaceAdaptor gui;
    
    private final Rectangle rect = new Rectangle(0, 0, 100, 100, Color.BLACK);
    
    public StarClash() {
        gui = new GameInterfaceSwing();
    }
    
    /** 
     * Inicia os servicos
     * 
     */
    public void run(){
        gui.start();
        
        gui.addRectangle(rect);
        
        gui.addKeyListener(new KeyListener(Key.KEY_UP) {
            @Override
            public void clicked() {
                rect.setY( rect.getY()+0.1f );
            }
        });
        gui.addKeyListener(new KeyListener(Key.KEY_DOWN) {
            @Override
            public void clicked() {
                rect.setY( rect.getY()-0.1f );
            }
        });
        gui.addKeyListener(new KeyListener(Key.KEY_LEFT) {
            @Override
            public void clicked() {
                rect.setX( rect.getX()+0.1f );
            }
        });
        gui.addKeyListener(new KeyListener(Key.KEY_RIGHT) {
            @Override
            public void clicked() {
                System.out.println("Act X: "+Float.toString(rect.getX()));
                rect.setX( rect.getX()+0.1f );
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
