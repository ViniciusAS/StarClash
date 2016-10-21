package starclash.gamemode.offline;

import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.ObservableEnemy;
import starclash.gui.KeysListenerAdaptor;
import starclash.starships.StarshipFactory;


public class OfflineGameMode implements GameModeFactory {

    private final KeysListenerAdaptor keysListener;
    private final StarshipFactory me;
    
    public OfflineGameMode(KeysListenerAdaptor keyListener, StarshipFactory me) {
        this.keysListener = keyListener;
        this.me = me;
    }
    
    @Override
    public ObservableEnemy newObservableEnemy() {
        return new OfflineObservableEnemy(keysListener);
    }

    @Override
    public CommandSender newCommandSender() {
        return new OfflineCommandSender( me );
    }
    

}
