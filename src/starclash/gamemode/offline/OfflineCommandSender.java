package starclash.gamemode.offline;

import starclash.gamemode.CommandSender;
import starclash.gamemode.StarshipMovementListener;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.Movement;
import starclash.starships.StarshipFactory;


public class OfflineCommandSender implements CommandSender {

    private final MoveListener moveListener;

    public OfflineCommandSender(StarshipFactory starship) {
        this.moveListener = new StarshipMovementListener( starship );
    }
    
    @Override
    public void moved(Movement movement) {
        moveListener.moved(movement);
    }

    @Override
    public void shotFired() {
    }

    @Override
    public void specialLaunched() {
    }

}
