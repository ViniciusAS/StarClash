package starclash.gamemode.offline;

import starclash.gamemode.listeners.Movement;
import starclash.gamemode.ObservableEnemy;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.ShotFiredListener;
import starclash.gamemode.listeners.SpecialLaunchListener;
import starclash.gui.KeysListenerAdaptor;
import starclash.gui.KeysListenerAdaptor.KeyListener;


public class OfflineObservableEnemy implements ObservableEnemy {

    private ShotFiredListener shotListener;
    private MoveListener moveListener;
    private SpecialLaunchListener specialListener;

    public OfflineObservableEnemy(KeysListenerAdaptor keysListener) {
        initEnemyObserving(keysListener);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public void setShotListener(ShotFiredListener shotListener) {
        this.shotListener = shotListener;
    }

    @Override
    public void setMoveListener(MoveListener moveListener) {
        this.moveListener = moveListener;
    }

    @Override
    public void setSpecialListener(SpecialLaunchListener specialListener) {
        this.specialListener = specialListener;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private void initEnemyObserving(KeysListenerAdaptor keysListener){
        
        // W - UP ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        keysListener.addKeyListener(KeysListenerAdaptor.Key.KEY_W, new KeyListener() {
            @Override public void clicked() {
                
                if ( moveListener != null )
                    moveListener.moved(Movement.UP);
                
            }
        });
        
        // A - LEFT ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++        
        keysListener.addKeyListener(KeysListenerAdaptor.Key.KEY_A, new KeyListener() {
            @Override public void clicked() {
                
                if ( moveListener != null )
                    moveListener.moved(Movement.LEFT);
                
            }
        });
        
        // S - DOWN ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        keysListener.addKeyListener(KeysListenerAdaptor.Key.KEY_S, new KeyListener() {
            @Override public void clicked() {
                
                if ( moveListener != null )
                    moveListener.moved(Movement.DOWN);
                
            }
        });
        
        // D - RIGHT +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        keysListener.addKeyListener(KeysListenerAdaptor.Key.KEY_D, new KeyListener() {
            @Override public void clicked() {
                
                if ( moveListener != null )
                    moveListener.moved(Movement.RIGHT);
                
            }
        });
        
        // G - SHOT ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        keysListener.addKeyListener(KeysListenerAdaptor.Key.KEY_G, new KeyListener() {
            @Override public void clicked() {
                
                if ( shotListener != null )
                    shotListener.shotFired();
                
            }
        });
        
        // T - SPECIAL +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        keysListener.addKeyListener(KeysListenerAdaptor.Key.KEY_G, new KeyListener() {
            @Override public void clicked() {
                
                if ( specialListener != null )
                    specialListener.specialLaunched();
                
            }
        });
    }

}
