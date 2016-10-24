package starclash.gamemode.online;

import io.socket.client.Socket;
import starclash.StarClash;
import starclash.gamemode.ObservableEnemy;
import starclash.gamemode.listeners.MoveListener;
import starclash.gamemode.listeners.ShotFiredListener;
import starclash.gamemode.listeners.SpecialLaunchListener;
import starclash.starships.StarshipFactory;


public class OnlineObservableEnemy implements ObservableEnemy {
    
    private final StarClash starClash;
    
    private final StarshipFactory me, enemy;
    
    private ShotFiredListener shotListener;
    private MoveListener moveListener;
    private SpecialLaunchListener specialListener;

    public OnlineObservableEnemy(StarClash starClash, Socket socket, StarshipFactory me, StarshipFactory enemy) {
        this.starClash = starClash;
        this.me = me;
        this.enemy = enemy;
        initEnemyObserving(socket);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private float filterX(float x){
        return 1 - x;
    }
    private float filterY(float y){
        return 1 - y;
    }
    
    private float[] filterResult(Object[] args){
        String[] data = ((String) args[0]).split(",");
        float[] res = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            res[i] = Float.parseFloat(data[i]);
        }
        return res;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public void setShotListener(ShotFiredListener shotListener) {
        this.shotListener = shotListener;
    }

    @Override
    public void setMoveListener(MoveListener moveListener) {
        this.moveListener = moveListener;
    }

    @Override
    public void setSpecialListener(SpecialLaunchListener specialListener) {
        this.specialListener = specialListener;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private void initEnemyObserving(Socket socket){
        socket.on("enemy_move", (Object... os) -> {
            if ( moveListener == null ) return;
            float data[] = filterResult(os);
            
            moveListener.moved(
                filterX(data[0]),
                filterY(data[1])
            );
        });
        socket.on("enemy_fire", (Object... os) -> {
            if ( shotListener == null ) return;
            float data[] = filterResult(os);
            
            shotListener.shotFired(
                filterX(data[0]),
                filterY(data[1])
            );
        });
        socket.on("enemy_special", (Object... os) -> {
            if ( shotListener == null ) return;
            float data[] = filterResult(os);
            
            specialListener.specialLaunched(
                filterX(data[0]),
                filterY(data[1])
            );
        });
        socket.on("enemy_getShot", (Object... os) -> {
            
            if ( enemy.takeDamage( me.newShot() ) ){
                // if the enemy is dead
                socket.disconnect();
                starClash.endOfBatle();
            }
        });
    }

}
