package starclash.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Vinicius Santos
 */
public class GameInterfaceSwing extends JPanel implements GameInterfaceAdaptor{
    
    private final JFrame frame;
    
    public GameInterfaceSwing() {
        frame = new JFrame("StarClash");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(this, BorderLayout.CENTER);
    }

    @Override
    public void start() {
        frame.setVisible(true);
    }

    @Override
    public void close() {
        frame.setVisible(false);
        System.exit(1);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private final List<KeyListener> keyListeners = new ArrayList<>();
    
    @Override
    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    @Override
    public void removeKeyListener(KeyListener keyListener) {
        keyListeners.remove(keyListener);
    }

    @Override
    public void notifyObservers(Key key) {
        for (KeyListener keyListener : keyListeners) {
            if ( keyListener.getKey() == key ){
                keyListener.clicked();
            }
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private final List<Rectangle> els = new ArrayList<>();
    
    @Override
    public void addRectangle(Rectangle rectangle) {
        els.add(rectangle);
    }

    @Override
    public void removeRectangle(Rectangle rectangle) {
        els.remove(rectangle);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public void paint(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics;
        
        for (Rectangle el : els) {
            g.setColor(new java.awt.Color(
                    el.getColor().getR(),
                    el.getColor().getG(),
                    el.getColor().getB(),
                    el.getColor().getA()
            ));
            g.fillRect(
                (int) ( el.getX() + getWidth()/2f ),
                (int) ( el.getY() + getHeight()/2f ),
                (int) ( el.getWidht() ),
                (int) ( el.getHeight() )
            );
        }
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
}
