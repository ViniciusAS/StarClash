package starclash;

import java.util.logging.Level;
import java.util.logging.Logger;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.offline.OfflineGameMode;
import starclash.gamemode.online.OnlineGameMode;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.swing.SwingGameInterface;
import starclash.menu.MenuInterface;
import starclash.starships.theincredablestarship.TheIncredableStarship;
import starclash.starships.StarshipFactory;


public class StarClash {

    private Thread battleThread;
    private final MenuInterface menu;
    
    public final GameInterfaceAdaptor gui;
    
    public StarshipFactory myStarship;
    
    public StarClash() {
        gui = new SwingGameInterface();
        menu = new MenuInterface( this );
        gui.start();
    }
    
    public void startMenu(){
        gui.clearDrawables();
        gui.addDrawable(menu);
        
        myStarship = new TheIncredableStarship();
                
        menu.start(gui);
    }
    
    public void endOfBatle(){
        startMenu();
    }
    
    /** 
     * Inicia uma batalha offline
     * 
     * @param enemy
     */
    public void startOfflineBatle(StarshipFactory enemy){
        
        battleThread = new Thread(() -> {

            GameModeFactory gameMode = new OfflineGameMode( gui.getKeysListener(), gui, myStarship, enemy );

            new Batle( this, gui, myStarship, Scenario.scenarioDefault ).start(gameMode);

        });
        battleThread.start();
    }
    
    /** 
     * Inicia uma batalha online.
     * 
     */
    public void startOnlineBatle(){
        
        battleThread = new Thread(() -> {
            try {

                GameModeFactory gameMode = new OnlineGameMode(this, myStarship);

                new Batle( this, gui, myStarship, Scenario.scenarioDefault ).start(gameMode);

            } catch (NullPointerException e){
                Logger.getLogger(StarClash.class.getName()).log(Level.WARNING, e.getMessage());
                startMenu();
            }
        });
        battleThread.start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StarClash().startMenu();
    }
    
}
