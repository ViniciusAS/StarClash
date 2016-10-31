package starclash.gamemode.online;

import io.socket.client.IO;
import io.socket.client.Socket;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius Santos
 */
public final class SocketConnection {

    private SocketConnection() {}

//    private static final String HOST = "vps0232.publiccloud.com.br";
    private static final String HOST = "10.120.29.27";
    
    private static final int    PORT = 90;
    
    public static Socket newSocketConnection()
    {
        try {
            return IO.socket( "http://" + HOST + ":" + Integer.toString( PORT ) );
        }
        catch (URISyntaxException e) 
        {
            Logger.getLogger(OnlineGameMode.class.getName()).log(Level.SEVERE, null, e);
            System.exit(1);
        }
        return null;
    }
    
}
