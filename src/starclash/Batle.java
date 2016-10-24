package starclash;

import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.listeners.Movement;
import starclash.gamemode.ObservableEnemy;
import starclash.gamemode.StarshipMovementListener;
import starclash.gamemode.listeners.ShotFiredListener;
import starclash.gamemode.listeners.SpecialLaunchListener;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.KeysListenerAdaptor;
import starclash.starships.StarshipFactory;
import starclash.starships.StarshipShot;

/**
 *
 * @author Vinicius Santos
 */
public class Batle implements
                        ShotFiredListener,
                        SpecialLaunchListener
{

    private final GameInterfaceAdaptor gui;
    private StarshipFactory me, enemy;
    private final Scenario scenario;
    
    public Batle(GameInterfaceAdaptor gui, StarshipFactory me,Scenario scenario) {
        this.scenario = scenario;
        this.gui = gui;
        this.me = me;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private CommandSender commandSender;
    
    public void start(GameModeFactory gameMode){
        
        System.out.println("Searching for enemies");
        
        this.enemy = gameMode.getEnemy();
        
        if ( enemy == null ) {
            throw new NullPointerException("Enemy not found");
        }
        
        System.out.println("Got an enemy: "+enemy.getName());
        
        //// drawables //// 
        
        gui.clearDrawables();
        
        
        
        commandSender = gameMode.newCommandSender();
        
        gui.addDrawable( scenario);
        gui.addDrawable( me.newStarshipDraw() );
        gui.addDrawable( enemy.newStarshipDraw() );
        
        
        //// listeners ////
        
        gui.getKeysListener().clearListeners();
        
        // enemy
        ObservableEnemy observableEnemy = gameMode.newObservableEnemy();
        observableEnemy.setMoveListener(new StarshipMovementListener( enemy ));
        observableEnemy.setShotListener(this);
        observableEnemy.setSpecialListener(this);
        
        // me
        initKeyListeners();
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    
    @Override
    public void shotFired() {
        StarshipShot shot = enemy.newShot();
        gui.addDrawable( shot );
        shot.start(gui,enemy);
    }
    @Override
    public void shotFired(float x, float y) {
        StarshipShot shot = enemy.newShot( x, y );
        gui.addDrawable( shot );
        shot.start(gui,enemy);
    }
    
    @Override
    public void specialLaunched() {
        enemy.doSpecial();
    }
    @Override
    public void specialLaunched(float x, float y) {
        enemy.doSpecial(x, y);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    
    private void initKeyListeners()
    {
        
        // ARROW - UP ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_UP, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed(){
                
                commandSender.moved(Movement.UP);
                
            }
        });
        
        // ARROW - LEFT ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_LEFT, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed(){
                
                commandSender.moved(Movement.LEFT);
                
            }
        });
        
        // ARROW - DOWN ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_DOWN, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed(){
                
                commandSender.moved(Movement.DOWN);
                
            }
        });
        
        // ARROW - RIGHT +++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_RIGHT, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed(){
                
                commandSender.moved(Movement.RIGHT);
                
            }
        });
        
        // SPACE - SHOT ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_SPACE, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed(){
                
                commandSender.shotFired();
                
            }
        });
        
        // ENTER - SPECIAL +++++++++++++++++++++++++++++++++++++++++++++++++++++
        gui.getKeysListener()
                .addKeyListener(KeysListenerAdaptor.Key.KEY_ENTER, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed(){
                
                commandSender.specialLaunched();
                
            }
        });
    }
    
}
