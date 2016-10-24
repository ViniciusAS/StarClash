package starclash;

import java.util.logging.Level;
import java.util.logging.Logger;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.offline.OfflineGameMode;
import starclash.gamemode.online.OnlineGameMode;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.swing.SwingGameInterface;
import starclash.menu.MenuInterface;
import starclash.starships.ShipVinicius.TheIncredableStarship;
import starclash.starships.StarshipFactory;


public class StarClash {

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
        
        GameModeFactory gameMode = new OfflineGameMode( gui.getKeysListener(), gui, myStarship, enemy );
        
        new Batle( gui, myStarship, Scenario.scenarioDefault ).start(gameMode);
        
    }
    
    /** 
     * Inicia uma batalha online.
     * 
     */
    public void startOnlineBatle(){
        try {
            
            GameModeFactory gameMode = new OnlineGameMode(this, myStarship);

            new Batle(gui, myStarship, Scenario.scenarioDefault).start(gameMode);
            
        } catch (NullPointerException e){
            Logger.getLogger(StarClash.class.getName()).log(Level.WARNING, e.getMessage());
            startMenu();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StarClash().startMenu();
    }
    
}
