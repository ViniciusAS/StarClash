package starclash.gui.swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

import starclash.gui.Drawable;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.KeysListenerAdaptor;


public class SwingGameInterface extends JPanel implements GameInterfaceAdaptor {

    private static final long FRAMES_DELAY_MS = 1;
    private static final long FRAMES_COUNTER_DELAY = 1000;

    private Timer repaintTimer;
    private final JFrame frame;

    private final SwingDrawAdaptor swingDrawAdaptor;
    private final SwingKeysListener keysListener;


    public SwingGameInterface() {
        this.frame = new JFrame("StarClash");
        swingDrawAdaptor = new SwingDrawAdaptor(this);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(this, BorderLayout.CENTER);

        keysListener = new SwingKeysListener();
        frame.addKeyListener(keysListener.new SwingKeyListener());
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
        new Thread(() -> {
            repaintTimer.scheduleAtFixedRate(new RepaintTimerTask(), 1, FRAMES_DELAY_MS);
        }).start();
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
        private long fpsTime = 0;

        @Override
        public void run() {
            if (System.currentTimeMillis() - time >= FRAMES_DELAY_MS) {
                
                if (System.currentTimeMillis() - fpsTime >= FRAMES_COUNTER_DELAY) {
                    long fps = (1000 / (System.currentTimeMillis() - time));
                    frame.setTitle("StarClash (" + fps + " FPS)");

                    fpsTime = System.currentTimeMillis();
                }

                SwingGameInterface.this.repaint();

                time = System.currentTimeMillis();
            }
        }

    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private final List<Drawable> drawables = new LinkedList<>();

    @Override
    public void addDrawable(Drawable drawable) {
        synchronized (drawables) {
            drawables.add(drawable);
        }
    }

    @Override
    public void removeDrawable(Drawable drawable) {
        synchronized (drawables) {
            drawables.remove(drawable);
        }
    }

    @Override
    public synchronized void clearDrawables() {
        synchronized (drawables) {
            drawables.clear();
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.clearRect(0, 0, getWidth(), getHeight());

        swingDrawAdaptor.setGraphics((Graphics2D) graphics);

        synchronized (drawables) {
            for (Drawable drawable : drawables) {
                drawable.draw(swingDrawAdaptor);
            }
        }
//        for (int i = 0; i < drawables.size(); i++) {
//            
//            drawables.get(i).draw(swingDrawAdaptor);
//        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}
