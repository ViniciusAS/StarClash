package starclash.menu;

import starclash.StarClash;
import starclash.gui.DrawAdaptor;
import starclash.gui.Drawable;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.KeysListenerAdaptor;
import starclash.gui.components.Color;
import starclash.gui.components.Image;
import starclash.gui.components.Rectangle;
import starclash.gui.components.Text;
import starclash.starships.ShipVinicius.TheIncredableStarship;
import starclash.starships.StarshipFactory;
import starclash.starships.mods.FasterShip;


public class MenuInterface implements Drawable {

    private final Image background = new Image("./background.jpg", new Rectangle(0, 0, 1, 1, Color.BLUE));
    private final Text title = new Text( "StarClash", 0.5f, 0.20f, Color.WHITE, "Trebuchet", 50, true );
    private final Text shipName = new Text( "", 0.5f, 0.25f, Color.WHITE, "Trebuchet", 15, true );
    private final StarClash starClash;

    private static final int MAIN_MENU = 0;
    private static final int SHIP_MENU = 1;
    private static final int MODS_MENU = 2;
    private static final int BATLE_MENU = 3;
    
    private final Menu menus[];
    
    private int currentMenu = MAIN_MENU;
    private boolean startingOfflineBatle = false;
    
    public MenuInterface(StarClash starClash) {
        this.starClash = starClash;
        this.menus = new Menu[]{
            new Menu(new String[]{
                "Escolher Nave",
                "Adicionar MOD",
                "Iniciar Jogo",
                "Sair"
            }),
            new Menu(new String[]{
                new TheIncredableStarship().getName(),
                "Cancelar"
            }),
            new Menu(new String[]{
                "Nave Rápida",
                "Cancelar"
            }),
            new Menu(new String[]{
                "Online",
                "Offline",
                "Cancelar"
            })
        };
    }
    
    public void start(GameInterfaceAdaptor gui){
        currentMenu = MAIN_MENU;
        startingOfflineBatle = false;
        gui.getKeysListener().clearListeners();
        gui.getKeysListener().addKeyListener(KeysListenerAdaptor.Key.KEY_DOWN, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed() {}
            @Override public void clicked() {
                menus[currentMenu].down();
            }
        });
        gui.getKeysListener().addKeyListener(KeysListenerAdaptor.Key.KEY_UP, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed() {}
            @Override public void clicked() {
                menus[currentMenu].up();
            }
        });
        gui.getKeysListener().addKeyListener(KeysListenerAdaptor.Key.KEY_ENTER, new KeysListenerAdaptor.KeyListener() {
            @Override public void pressed() {}
            @Override public void clicked() {
                menuClick();
            }
        });
    }
    
    private void menuClick(){
        switch ( currentMenu ) {
            case MAIN_MENU:
                switch ( menus[MAIN_MENU].getSelected() ) {
                    case 0: // escolher nave
                        currentMenu = SHIP_MENU;
                        break;
                    case 1: // adicionar mod
                        currentMenu = MODS_MENU;
                        break;
                    case 2: // iniciar jogo
                        currentMenu = BATLE_MENU;
                        break;
                    case 3: // sair
                        System.exit(0);
                        break;
                }
                break;
            case SHIP_MENU:
                {
                    StarshipFactory starship = starClash.myStarship;
                    switch ( menus[SHIP_MENU].getSelected() ) {
                        case 0: // the incredable starship
                            starship = new TheIncredableStarship( startingOfflineBatle );
                            break;
                    }
                    if ( startingOfflineBatle ) {
                        // se alguma nave foi escolhida
                        if ( starship != starClash.myStarship ){
                            starClash.startOfflineBatle( starship );
                        }
                    } else {
                        starClash.myStarship = starship;
                    }
                    currentMenu = MAIN_MENU;
                }
                break;
            case MODS_MENU:
                switch ( menus[MODS_MENU].getSelected() ) {
                    case 0: // nave rapida
                        starClash.myStarship = new FasterShip( starClash.myStarship );
                        break;
                }
                currentMenu = MAIN_MENU;
                break;
            case BATLE_MENU:
                switch ( menus[BATLE_MENU].getSelected() ) {
                    case 0: // online
                        starClash.startOnlineBatle();
                        break;
                    case 1: // offline
                        startingOfflineBatle = true;
                        currentMenu = SHIP_MENU;
                        return;
                }
                currentMenu = MAIN_MENU;
                break;
        }
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        
        drawAdaptor.drawImage(background);
        
        drawAdaptor.drawText(title);
                
        menus[currentMenu].draw(drawAdaptor);
        
        shipName.setText( starClash.myStarship.getName() );
        drawAdaptor.drawText(shipName);
        
    }

}
