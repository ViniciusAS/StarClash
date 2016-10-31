package starclash.gamemode.online;

import io.socket.client.Socket;
import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.ObservableEnemy;
import starclash.starships.StarshipFactory;

import java.util.logging.Level;
import java.util.logging.Logger;
import starclash.StarClash;
import starclash.starships.theincredablestarship.TheIncredableStarship;
import starclash.starships.mods.FasterShip;
import starclash.starships.mods.StrongerShip;
import starclash.starships.nyancatstarship.NyanCatStarship;
import starclash.starships.o_rangestarship.ORangeStarship;
import starclash.starships.workaroundstarship.WorkAroundStarship;


public class OnlineGameMode implements GameModeFactory {
    
    public static final class StarshipTypes {
        
        public static final int THE_INCREDABLE_STARSHIP = 1;
        public static final int NYAN_CAT_STARSHIP       = 2;
        public static final int WORKAROUND_STARSHIP     = 3;
        public static final int O_RANGE_STARSHIP        = 4;
        
        public static final int MOD_FASTER   = 11;
        public static final int MOD_STRONGER = 12;
        
        public static String toSocketString(StarshipFactory starship){
            if ( starship == null ) return "";
            String sstr = "";
            if ( starship instanceof TheIncredableStarship ) sstr = Integer.toString(THE_INCREDABLE_STARSHIP);
            if ( starship instanceof NyanCatStarship )       sstr = Integer.toString(NYAN_CAT_STARSHIP);
            if ( starship instanceof WorkAroundStarship )    sstr = Integer.toString(WORKAROUND_STARSHIP);
            if ( starship instanceof ORangeStarship )        sstr = Integer.toString(O_RANGE_STARSHIP);
            
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
                    case MOD_STRONGER:
                        starship = new StrongerShip(starship);
                        break;
                }
            }
            return starship;
        }
        
        public static StarshipFactory createStarship(int starshipType){
            switch ( starshipType ) {
                case ( StarshipTypes.THE_INCREDABLE_STARSHIP ):
                    return new TheIncredableStarship( true );
                case ( StarshipTypes.NYAN_CAT_STARSHIP ):
                    return new NyanCatStarship( true );
                case ( StarshipTypes.WORKAROUND_STARSHIP ):
                    return new WorkAroundStarship( true );
                case ( StarshipTypes.O_RANGE_STARSHIP ):
                    return new ORangeStarship( true );
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
    public StarshipFactory getEnemy() {
        if ( this.enemy == null )
            synchronized (OnlineGameMode.this) {
                try {
                    OnlineGameMode.this.wait();
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
        return new OnlineCommandSender( starClash.gui, socket, me, enemy );
    }


}
