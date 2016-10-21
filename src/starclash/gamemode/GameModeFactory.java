package starclash.gamemode;

import starclash.starships.StarshipFactory;


public interface GameModeFactory {
    
    public abstract ObservableEnemy newObservableEnemy();
    
    public abstract CommandSender newCommandSender();
    
}
