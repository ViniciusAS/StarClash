package starclash;

import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.listeners.Movement;
import starclash.gamemode.ObservableEnemy;
import starclash.gamemode.StarshipMovementListener;
import starclash.gamemode.listeners.ShotFiredListener;
import starclash.gamemode.listeners.SpecialLaunchListener;
import starclash.gamemode.offline.OfflineGameMode;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.KeysListenerAdaptor;
import starclash.starships.StarshipFactory;

/**
 *
 * @author Vinicius Santos
 */
public class Batle implements
                        ShotFiredListener,
                        SpecialLaunchListener
{

    private GameInterfaceAdaptor gui;
    private StarshipFactory me, enemy;

    public Batle() {
    }

    public void setGui(GameInterfaceAdaptor gui) {
        this.gui = gui;
    }

    public void setMe(StarshipFactory me) {
        this.me = me;
    }
    
    public void setEnemy(StarshipFactory enemy) {
        this.enemy = enemy;
    }
    
    public Batle(GameInterfaceAdaptor gui, StarshipFactory me, StarshipFactory enemy) {
        this.gui = gui;
        this.me = me;
        this.enemy = enemy;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private CommandSender commandSender;
    
    public void start(){
        
        gui.clearDrawables();
        
        GameModeFactory gameMode = new OfflineGameMode( gui.getKeysListener(), me );
        
        commandSender = gameMode.newCommandSender();
        
        gui.addDrawable( me.newStarshipDraw() );
        gui.addDrawable( enemy.newStarshipDraw() );
        
        
        gui.getKeysListener().clearListeners();
        ObservableEnemy observableEnemy = gameMode.newObservableEnemy();
        observableEnemy.setMoveListener(new StarshipMovementListener( enemy ));
        observableEnemy.setShotListener(this);
        observableEnemy.setSpecialListener(this);
        initKeyListeners();
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    
    @Override
    public void shotFired() {
        gui.addDrawable( enemy.newShot() );
    }

    @Override
    public void specialLaunched() {
        enemy.doSpecial();
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    
    private void initKeyListeners()
    {
        
        // ARROW - UP ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_UP, new KeysListenerAdaptor.KeyListener() {
            @Override public void clicked(){
                
                commandSender.moved(Movement.UP);
                
            }
        });
        
        // ARROW - LEFT ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_LEFT, new KeysListenerAdaptor.KeyListener() {
            @Override public void clicked(){
                
                commandSender.moved(Movement.LEFT);
                
            }
        });
        
        // ARROW - DOWN ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_DOWN, new KeysListenerAdaptor.KeyListener() {
            @Override public void clicked(){
                
                commandSender.moved(Movement.DOWN);
                
            }
        });
        
        // ARROW - RIGHT +++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_RIGHT, new KeysListenerAdaptor.KeyListener() {
            @Override public void clicked(){
                
                commandSender.moved(Movement.RIGHT);
                
            }
        });
        
        // SPACE - SHOT ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_SPACE, new KeysListenerAdaptor.KeyListener() {
            @Override public void clicked(){
                
                commandSender.shotFired();
                
            }
        });
        
        // M - SPECIAL +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_M, new KeysListenerAdaptor.KeyListener() {
            @Override public void clicked(){
                
                commandSender.specialLaunched();
                
            }
        });
    }
    
}
