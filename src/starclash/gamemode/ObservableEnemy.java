package starclash.gamemode;

import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.ShotFiredListener;
import starclash.gamemode.listeners.SpecialLaunchListener;


public interface ObservableEnemy {
    
    public void setMoveListener(MoveListener moveListener);
    
    public void setShotListener(ShotFiredListener shotListener);
    
    public void setSpecialListener(SpecialLaunchListener specialListener);
    
}
