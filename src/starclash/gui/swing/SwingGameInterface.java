package starclash.gui.swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    
    private static final long FRAMES_DELAY_MS = 10;
    
    private Timer repaintTimer;
    private final JFrame frame;    
    private final SwingDrawAdaptor swingDrawAdaptor;
    private final SwingKeysListener keysListener;
    
    
    public SwingGameInterface()
    {
        this.frame = new JFrame();
        swingDrawAdaptor = new SwingDrawAdaptor(this);
        frame.setSize(500, 600);
        frame.setResizable(false);
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
        repaintTimer.scheduleAtFixedRate(new RepaintTimerTask(), 1, FRAMES_DELAY_MS);
    }


    @Override
    public void close() {
        repaintTimer.cancel();
        frame.setVisible(false);
        System.exit(1);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private class RepaintTimerTask extends TimerTask {

        private long time = 0;
        @Override
        public void run()
        {
            if ( System.currentTimeMillis()-time >= FRAMES_DELAY_MS ){
                System.out.println("FPS: "+(1000/(System.currentTimeMillis()-time)));
                
                new Thread(() -> {
                    SwingGameInterface.this.repaint();
                }).start();
                
                time = System.currentTimeMillis();
            }
        }
        
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private final List<Drawable> drawables = new ArrayList<>();
    
    @Override
    public synchronized void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    @Override
    public synchronized void removeDrawable(Drawable drawable) {
        drawables.remove(drawable);
    }

    @Override
    public synchronized void clearDrawables() {
        drawables.clear();
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        swingDrawAdaptor.setGraphics( (Graphics2D) graphics );
        
        graphics.clearRect(0, 0, getWidth(), getHeight());
        
        for (int i = 0; i < drawables.size(); i++) {
            
            drawables.get(i).draw(swingDrawAdaptor);
        }
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
}
