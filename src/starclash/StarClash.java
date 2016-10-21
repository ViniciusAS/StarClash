package starclash;

import starclash.gamemode.CommandSender;
import starclash.gamemode.GameModeFactory;
import starclash.gamemode.ObservableEnemy;
import starclash.gamemode.offline.OfflineGameMode;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.swing.SwingGameInterface;
import starclash.starships.ShipVinicius.TheIncredableStarship;
import starclash.starships.StarshipFactory;
import starclash.starships.mods.FasterShip;


public class StarClash {

    private final GameInterfaceAdaptor gui;
    
    private StarshipFactory myStarship;
    
    public StarClash() {
        gui = new SwingGameInterface();
        
        gui.start();
        
        myStarship = new TheIncredableStarship();
        myStarship = new FasterShip(myStarship);
    }
    
    /** 
     * Inicia uma batalha
     * 
     * @param enemy
     */
    public void startBatle(StarshipFactory enemy){
        
        new Batle(gui, myStarship, enemy).start();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StarClash()
            .startBatle( new TheIncredableStarship() );
    }
    
}
