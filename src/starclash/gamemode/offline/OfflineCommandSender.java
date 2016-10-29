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
    private final StarshipFactory me, enemy;

    public OfflineCommandSender(StarshipFactory me, StarshipFactory enemy, GameInterfaceAdaptor gui) {
        this.moveListener = new StarshipMovementListener( me );
        this.gui = gui;
        this.me = me;
        this.enemy = enemy;
    }
    
    @Override
    public void moved(Movement movement) {
        moveListener.moved(movement);
    }

    @Override
    public void moved(float x, float y) {
        moveListener.moved(x, y);
    }

    @Override
    public void shotFired() {
        StarshipShot shot = me.newShot();
        gui.addDrawable(shot);
        shot.start(gui, enemy);
    }

    @Override
    public void shotFired(float x, float y) {
        StarshipShot shot = me.newShot(x, y);
        gui.addDrawable(shot);
        shot.start(gui,enemy);
    }

    @Override
    public void specialLaunched() {
        me.doSpecial();
    }

    @Override
    public void specialLaunched(float x, float y) {
        me.doSpecial(x, y);
    }

    @Override
    public void onDamageTaken(int damage) {
    }
    
    @Override
    public void onDie() {
    }

}
