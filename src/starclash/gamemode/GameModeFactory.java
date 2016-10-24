package starclash.gamemode;

import starclash.starships.StarshipFactory;

public interface GameModeFactory {
    
    public ObservableEnemy newObservableEnemy();
    
    public CommandSender newCommandSender();
    
    public StarshipFactory getEnemy();
    
}
