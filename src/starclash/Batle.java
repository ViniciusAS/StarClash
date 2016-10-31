package starclash;

import java.util.logging.Level;
import java.util.logging.Logger;
import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.listeners.Movement;
import starclash.gamemode.ObservableEnemy;
import starclash.gamemode.StarshipMovementListener;
import starclash.gamemode.listeners.DamageListener;
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

    private final StarClash starClash;
    
    private final GameInterfaceAdaptor gui;
    private final Scenario scenario;
    
    private final StarshipFactory me;
    private StarshipFactory enemy;
    
    public Batle(StarClash starClash, GameInterfaceAdaptor gui, StarshipFactory me,Scenario scenario) {
        this.starClash = starClash;
        this.scenario = scenario;
        this.gui = gui;
        this.me = me;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private CommandSender commandSender;
    private ObservableEnemy observableEnemy;
    
    public void start(GameModeFactory gameMode){
        
        //// loading window // clear everything ////
        Loading loading = new Loading();
        gui.clearDrawables();
        gui.addDrawable(loading);
        gui.getKeysListener().clearListeners();
        
        //// load enemy ////
        System.out.println("Searching for enemies");
        
        this.enemy = gameMode.getEnemy();
        
        if ( enemy == null ) {
            throw new NullPointerException("Enemy not found");
        }
        
        System.out.println("Got an enemy: "+enemy.getName());
        
        
        //// starships commands start ////
        
        commandSender = gameMode.newCommandSender();
        observableEnemy = gameMode.newObservableEnemy();
        
        //// listeners ////
        
        initListeners();
        
        
        //// die listeners ////
        DamageListener damageListener = new DamageListener() {
            @Override public void onDamageTaken(int damage) {}

            @Override
            public void onDie() {
                starClash.endOfBatle();
            }
        };
        me.addDamageListener(damageListener);
        enemy.addDamageListener(damageListener);
        me.addDamageListener(commandSender);
        
        //// change drawables to start batle //// 
        
        loading.stop();
        gui.clearDrawables();
        
        gui.addDrawable( scenario );
        gui.addDrawable( me.newStarshipDraw() );
        gui.addDrawable( enemy.newStarshipDraw() );
        gui.addDrawable( new PercentualIndicatorsDisplay(me, enemy) );
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void shotFired(StarshipShot shot){
        boolean allowed = shot.start(me, new StarshipShot.EndShotLifeListener() {
            @Override
            public void onExit() {
                gui.removeDrawable(shot);
            }

            @Override
            public void onHit() {
                me.takeDamage(shot.getDamage());
                gui.removeDrawable(shot);
            }
        });
        if ( allowed ){
            gui.addDrawable( shot );
        }
    }
    
    /**
     * enemy shot fired
     */
    @Override
    public void shotFired() {
        shotFired( enemy.newShot() );
    }
    @Override
    public void shotFired(float x, float y) {
        shotFired( enemy.newShot( x, y ) );
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
    
    private void initListeners()
    {
        // enemy listeners
        observableEnemy.setMoveListener(new StarshipMovementListener( enemy ));
        observableEnemy.setShotListener(this);
        observableEnemy.setSpecialListener(this);
 
        
        // player listeners
        
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
