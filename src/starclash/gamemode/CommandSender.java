package starclash.gamemode;

import starclash.gamemode.listeners.DamageListener;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.ShotFiredListener;
import starclash.gamemode.listeners.SpecialLaunchListener;


public interface CommandSender extends MoveListener, ShotFiredListener, SpecialLaunchListener, DamageListener {
    
    public void proccessHitPerformed(int damage);
    
}
