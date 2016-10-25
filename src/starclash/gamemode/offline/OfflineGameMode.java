package starclash.gamemode.offline;

import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.ObservableEnemy;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.KeysListenerAdaptor;
import starclash.starships.StarshipFactory;


public class OfflineGameMode implements GameModeFactory {

    private final KeysListenerAdaptor keysListener;
    
    private final GameInterfaceAdaptor gui;
    
    private final StarshipFactory me, enemy;
    
    
    public OfflineGameMode(
            KeysListenerAdaptor keyListener,
            GameInterfaceAdaptor gui,
            StarshipFactory me,
            StarshipFactory enemy
    ) {
        this.keysListener = keyListener;
        this.gui = gui;
        this.me = me;
        this.enemy = enemy;
    }
    
    @Override
    public ObservableEnemy newObservableEnemy() {
        return new OfflineObservableEnemy(keysListener);
    }

    @Override
    public CommandSender newCommandSender() {
        return new OfflineCommandSender( me, enemy, gui );
    }

    @Override
    public StarshipFactory getEnemy() {
        return enemy;
    }
    
}
