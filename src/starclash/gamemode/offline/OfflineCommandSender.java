package starclash.gamemode.offline;

import starclash.gamemode.CommandSender;
import starclash.gamemode.StarshipMovementListener;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.Movement;
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

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void shotFired(StarshipShot shot){
        boolean allowed = shot.start(enemy, new StarshipShot.EndShotLifeListener() {
            
            @Override
            public void onExit()
            {
                gui.removeDrawable(shot);
            }

            @Override
            public void onHit()
            {
                enemy.takeDamage( shot.getDamage() );
                gui.removeDrawable(shot);
            }
            
        });
        if ( allowed ) {
            gui.addDrawable(shot);
        }
    }

    @Override
    public void shotFired() {
        shotFired( me.newShot() );
    }

    @Override
    public void shotFired(float x, float y) {
        shotFired( me.newShot(x, y) );        
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
