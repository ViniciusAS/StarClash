package starclash.gamemode.online;

import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.ObservableEnemy;
import starclash.gamemode.offline.OfflineCommandSender;
import starclash.gamemode.offline.OfflineObservableEnemy;
import starclash.gui.KeysListenerAdaptor;
import starclash.starships.StarshipFactory;


public class OnlineGameMode implements GameModeFactory {

    private final KeysListenerAdaptor keysListener;
    private final StarshipFactory me;

    public OnlineGameMode(KeysListenerAdaptor keyListener, StarshipFactory me) {
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
