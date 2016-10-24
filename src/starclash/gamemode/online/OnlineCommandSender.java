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
    private final StarshipFactory starship;
    private final MoveListener moveListener;
    
    private final Socket socket;

    public OnlineCommandSender(GameInterfaceAdaptor gui, Socket socket, StarshipFactory starship) {
        this.socket = socket;
        this.gui = gui;
        this.starship = starship;
        this.moveListener = new StarshipMovementListener( starship );
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
        emit("move", starship.getX(), starship.getY());
    }

    @Override
    public void moved(float x, float y) {
        moveListener.moved(x, y);
        emit("move", x, y);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    @Override
    public void shotFired() {
        StarshipShot shot = starship.newShot();
        gui.addDrawable(shot);
        shot.start(gui);
        emit("fire", shot.getX(), shot.getY());
    }

    @Override
    public void shotFired(float x, float y) {
        StarshipShot shot = starship.newShot(x, y);
        gui.addDrawable(shot);
        shot.start(gui);
        emit("fire", x, y);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void specialLaunched() {
        starship.doSpecial();
        emit("special", starship.getX(), starship.getY());
    }

    @Override
    public void specialLaunched(float x, float y) {
        starship.doSpecial(x, y);
        emit("special", x, y);
    }

}
