package starclash.gamemode.offline;

import starclash.gamemode.CommandSender;
import starclash.gamemode.StarshipMovementListener;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.Movement;
import starclash.gamemode.listeners.ShotFiredListener;
import starclash.gui.GameInterfaceAdaptor;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;


public class OfflineCommandSender implements CommandSender {

    private final MoveListener moveListener;
    private final GameInterfaceAdaptor gui;
    private final StarshipFactory starship;

    public OfflineCommandSender(StarshipFactory starship, GameInterfaceAdaptor gui) {
        this.moveListener = new StarshipMovementListener( starship );
        this.gui = gui;
        this.starship = starship;
    }
    
    @Override
    public void moved(Movement movement) {
        moveListener.moved(movement);
    }

    @Override
    public void shotFired() {
        StarshipShot shot = starship.newShot();
        gui.addDrawable(shot);
        shot.start();
    }

    @Override
    public void specialLaunched() {
    }

}
