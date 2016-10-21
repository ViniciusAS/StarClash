package starclash.gui.swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import starclash.gui.Drawable;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.KeysListenerAdaptor;


public class SwingGameInterface extends JPanel implements GameInterfaceAdaptor {
    
    private static final long FRAMES_DELAY = 1;
    
    private Timer repaintTimer;
    private final JFrame frame;    
    private final SwingDrawAdaptor swingDrawAdaptor;
    private final SwingKeysListener keysListener;
    
    public SwingGameInterface()
    {
        swingDrawAdaptor = new SwingDrawAdaptor();
        frame = new JFrame("StarClash");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(this, BorderLayout.CENTER);
        
        keysListener = new SwingKeysListener();
        frame.addKeyListener( keysListener.new SwingKeyListener() );
    }

    @Override
    public KeysListenerAdaptor getKeysListener() {
        return keysListener;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void start() {
        frame.setVisible(true);
        repaintTimer = new Timer();
        repaintTimer.scheduleAtFixedRate(new RepaintTimerTask(), 1, FRAMES_DELAY);
    }


    @Override
    public void close() {
        repaintTimer.cancel();
        frame.setVisible(false);
        System.exit(1);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private class RepaintTimerTask extends TimerTask {

        @Override
        public void run()
        {
            SwingGameInterface.this.repaint();
        }
        
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private final List<Drawable> drawables = new ArrayList<>();
    
    @Override
    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    @Override
    public void removeDrawable(Drawable drawable) {
        drawables.remove(drawable);
    }

    @Override
    public void clearDrawables() {
        drawables.clear();
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        swingDrawAdaptor.setGraphics(graphics);
        
        graphics.clearRect(0, 0, getWidth(), getHeight());
        
        for (Drawable drawable : drawables)
        {
            drawable.draw(swingDrawAdaptor);
        }
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
}
