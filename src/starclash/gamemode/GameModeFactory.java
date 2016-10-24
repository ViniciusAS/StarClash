package starclash.gamemode;

public interface GameModeFactory {
    
    public abstract ObservableEnemy newObservableEnemy();
    
    public abstract CommandSender newCommandSender();
    
}
