package starclash.gamemode.online;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.ObservableEnemy;
import starclash.gamemode.offline.OfflineCommandSender;
import starclash.gamemode.offline.OfflineObservableEnemy;
import starclash.gui.KeysListenerAdaptor;
import starclash.starships.StarshipFactory;

import java.net.URISyntaxException;


public class OnlineGameMode implements GameModeFactory {

    private final KeysListenerAdaptor keysListener;
    private final StarshipFactory me;
    private Socket socket;

    public OnlineGameMode(KeysListenerAdaptor keyListener, StarshipFactory me) {
        this.keysListener = keyListener;
        this.me = me;
        try {
            this.socket = IO.socket("http://localhost:90");
            iniciarMetodosSocket();
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void iniciarMetodosSocket() {
        socket.on(Socket.EVENT_CONNECT, args -> {
            socket.emit("joinLobby", "1,2,3");
        }).on("event", args -> {
        }).on(Socket.EVENT_DISCONNECT, args -> {
        });
    }

    @Override
    public ObservableEnemy newObservableEnemy() {
        return new OfflineObservableEnemy(keysListener);
    }

    @Override
    public CommandSender newCommandSender() {
        return new OfflineCommandSender(me);
    }


}
