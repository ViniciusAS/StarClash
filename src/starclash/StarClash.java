package starclash;

import starclash.gamemode.GameModeFactory;
import starclash.gamemode.offline.OfflineGameMode;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Rectangle;
import starclash.gui.swing.SwingDrawAdaptor;
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
        
        myStarship = new TheIncredableStarship(gui);
        
        menu.start(gui);
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
     * Inicia uma batalha online
     * 
     */
    public void startOnlineBatle(){
        
//        GameModeFactory gameMode = new OfflineGameMode( gui.getKeysListener(), myStarship );
        
//        new Batle(gui, myStarship, enemy).start(gameMode);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StarClash().startMenu();
    }
    
}
