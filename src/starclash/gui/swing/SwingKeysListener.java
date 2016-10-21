package starclash.gui.swing;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.KeysListenerAdaptor;


public class SwingKeysListener implements KeysListenerAdaptor{

    private static final long KEY_DELAY = 30;
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private final List<KeyListener> keyObservers = new ArrayList<>();
    
    @Override
    public void addKeyListener(KeyListener keyListener) {
        keyObservers.add(keyListener);
    }
    
    @Override
    public void addKeyListener(Key key, KeyListener keyListener) {
        keyListener.setKey(key);
        addKeyListener(keyListener);
    }

    @Override
    public void removeKeyListener(KeyListener keyListener) {
        keyObservers.remove(keyListener);
    }

    @Override
    public void clearListeners() {
        keyObservers.clear();
    }

    @Override
    public void notifyObservers(Key key) {
        for (KeyListener keyListener : keyObservers) {
            if ( keyListener.getKey() == key ){
                keyListener.clicked();
            }
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    public class SwingKeyListener implements java.awt.event.KeyListener {

        private final Map<Key,Timer> timers = new HashMap<>();
        
        private synchronized void startTimer(Key key){
            if ( timers.get(key) != null )
                return;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    notifyObservers( key );
                }
            };
            timer.scheduleAtFixedRate(task, 1, KEY_DELAY);
            timers.put(key, timer);            
        }
        
        private synchronized void endTimer(Key key){
            timers.get(key).cancel();
            timers.remove(key);
        }
        
        private Key swingEventToKey(KeyEvent ke){
            switch ( ke.getKeyCode() ){
                case KeyEvent.VK_LEFT:  return Key.KEY_LEFT;
                case KeyEvent.VK_RIGHT: return Key.KEY_RIGHT;
                case KeyEvent.VK_UP:    return Key.KEY_UP;
                case KeyEvent.VK_DOWN:  return Key.KEY_DOWN;
                   
                case KeyEvent.VK_W:     return Key.KEY_W;
                case KeyEvent.VK_A:     return Key.KEY_A;
                case KeyEvent.VK_S:     return Key.KEY_S;
                case KeyEvent.VK_D:     return Key.KEY_D;
                   
                case KeyEvent.VK_SPACE: return Key.KEY_SPACE;
                case KeyEvent.VK_ENTER: return Key.KEY_ENTER;
            }
            return null;
        }
        
        @Override public void keyTyped(KeyEvent ke) {}
        
        @Override public void keyReleased(KeyEvent ke) {
            endTimer( swingEventToKey(ke) );
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            startTimer( swingEventToKey(ke) );
        }
        
    }
    
    
}
