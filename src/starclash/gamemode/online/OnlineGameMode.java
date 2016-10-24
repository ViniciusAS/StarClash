package starclash.gamemode.online;

import io.socket.client.Socket;
import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.ObservableEnemy;
import starclash.starships.StarshipFactory;

import java.util.logging.Level;
import java.util.logging.Logger;
import starclash.StarClash;
import starclash.starships.ShipVinicius.TheIncredableStarship;
import starclash.starships.mods.FasterShip;


public class OnlineGameMode implements GameModeFactory {
    
    public static final class StarshipTypes {
        
        public static final int THE_INCREDABLE_STARSHIP = 1;
        
        public static final int MOD_FASTER = 11;
        
        public static String toSocketString(StarshipFactory starship){
            if ( starship == null ) return "";
            String sstr = "";
            if ( starship instanceof TheIncredableStarship ) sstr = Integer.toString(THE_INCREDABLE_STARSHIP);
            
            String next = toSocketString(starship.getNext());
            if ( !next.equals("") ){
                return sstr + "," + next;
            }            
            return sstr;
        }
        
        public static StarshipFactory fromSocketString(String str){
            String[] ships = str.split(",");
            StarshipFactory starship = createStarship( Integer.parseInt( ships[0] ) );
            for (int i = 1; i < ships.length; i++) {
                int code = Integer.parseInt( ships[i] );
                switch (code) {
                    case MOD_FASTER:
                        starship = new FasterShip(starship);
                        break;
                }
            }
            return starship;
        }
        
        public static StarshipFactory createStarship(int starshipType){
            switch ( starshipType ) {
                case ( StarshipTypes.THE_INCREDABLE_STARSHIP ):
                    return new TheIncredableStarship( true );
            }
            return null;
        }
    }
    
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    private final StarClash starClash;
    
    private final StarshipFactory me;
    private StarshipFactory enemy;
    
    private final Socket socket;

    public OnlineGameMode(StarClash starClash, StarshipFactory me) {
        this.starClash = starClash;
        this.me = me;
        this.enemy = null;
        this.socket = SocketConnection.newSocketConnection();
        initSocket();
        socket.connect();
    }

    private void initSocket() {
        socket.on(Socket.EVENT_CONNECT, args -> {
            socket.emit("joinLobby", StarshipTypes.toSocketString(me));
        });
        socket.on(Socket.EVENT_DISCONNECT, args -> {
            starClash.startMenu();
        });
        
        
//        socket.on("player_join", (Object... os) -> {
//            
//        });
        
        socket.on("enemy_join", (Object... os) -> {
            
            this.enemy = StarshipTypes.fromSocketString( (String) os[0] );
            
            synchronized ( OnlineGameMode.this ) {
                OnlineGameMode.this.notify();
            }
        });
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public synchronized StarshipFactory getEnemy() {
        if ( this.enemy == null ){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(OnlineGameMode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.enemy;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public ObservableEnemy newObservableEnemy() {
        return new OnlineObservableEnemy( starClash, socket, me, getEnemy() );
    }

    @Override
    public CommandSender newCommandSender() {
        return new OnlineCommandSender( starClash.gui, socket, me );
    }


}
