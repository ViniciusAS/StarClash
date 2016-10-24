package starclash.gamemode.online;

import io.socket.client.Socket;
import starclash.gamemode.CommandSender;
import starclash.gamemode.StarshipMovementListener;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.Movement;
import starclash.gui.GameInterfaceAdaptor;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;


public class OnlineCommandSender implements CommandSender {

    private final GameInterfaceAdaptor gui;
    private final StarshipFactory me, enemy;
    private final MoveListener moveListener;
    
    private final Socket socket;

    public OnlineCommandSender(
            GameInterfaceAdaptor gui,
            Socket socket,
            StarshipFactory me,
            StarshipFactory enemy
    ) {
        this.socket = socket;
        this.gui = gui;
        this.me = me;
        this.enemy = enemy;
        this.moveListener = new StarshipMovementListener( me );
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private void emit(String event, float... values){
        String args = "";
        for (int i = 0; i < values.length; i++) {
            if ( i != 0 ){
                args += ",";
            }
            args += Float.toString( values[i] );
        }
        socket.emit(event, args);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public void moved(Movement movement) {
        moveListener.moved(movement);
        emit("move", me.getX(), me.getY());
    }

    @Override
    public void moved(float x, float y) {
        moveListener.moved(x, y);
        emit("move", x, y);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    @Override
    public void shotFired() {
        StarshipShot shot = me.newShot();
        boolean allowed = shot.start(gui,enemy);
        if ( allowed ) {
            gui.addDrawable(shot);
            emit("fire", shot.getX(), shot.getY());
        }
    }

    @Override
    public void shotFired(float x, float y) {
        StarshipShot shot = me.newShot(x, y);
        boolean allowed = shot.start(gui,enemy);
        if ( allowed ) {
            gui.addDrawable(shot);
            emit("fire", x, y);
        }
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void specialLaunched() {
        me.doSpecial();
        emit("special", me.getX(), me.getY());
    }

    @Override
    public void specialLaunched(float x, float y) {
        me.doSpecial(x, y);
        emit("special", x, y);
    }

}
