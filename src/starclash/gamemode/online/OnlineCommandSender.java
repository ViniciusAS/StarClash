package starclash.gamemode.online;

import starclash.gamemode.CommandSender;
import starclash.gamemode.StarshipMovementListener;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.Movement;
import starclash.starships.StarshipFactory;


public class OnlineCommandSender implements CommandSender {

    private final MoveListener moveListener;

    public OnlineCommandSender(StarshipFactory starship) {
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
