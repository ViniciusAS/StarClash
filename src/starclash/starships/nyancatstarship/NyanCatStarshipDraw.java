package starclash.starships.nyancatstarship;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import starclash.gui.DrawAdaptor;
import starclash.gui.components.Component;
import starclash.starships.StarshipCollision;
import starclash.starships.StarshipComponents;
import starclash.starships.StarshipDraw;
import starclash.starships.StarshipFactory;

/**
 *
 * @author samuel
 */
public class NyanCatStarshipDraw implements StarshipDraw{

    private final StarshipComponents components;
    private final StarshipFactory starship;
    private final StarshipCollision collision;
    private NyanCatFlagSpecial flag;
    
    private long lastDrawTime = 0;
    
    private final List<NyanCatStarshipRainbow> rainbows = new LinkedList<>();
    private final Timer newRainbowTimer = new Timer();
        
    public NyanCatStarshipDraw(NyanCatStarship starship, 
                                StarshipComponents components, 
                                StarshipCollision collision) {
        this.components = components;
        this.starship = starship;
        this.collision = collision;
        
        newRainbowTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // se o tempo o ultimo desenho foi a mais de 1 segundo, para
                if ( lastDrawTime != 0 & System.currentTimeMillis() - lastDrawTime > 1000  ){
                    rainbows.clear();
                    newRainbowTimer.cancel();
                }
                synchronized (rainbows) {
                    rainbows.add(
                        new NyanCatStarshipRainbow(
                            starship,
                            starship.getX(),
                            starship.getY()
                        )
                    );
                    if ( rainbows.get(0).isStopped() ){
                        rainbows.remove(0);
                    }
                }
            }
        }, 1, 50);
    }
    
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        lastDrawTime = System.currentTimeMillis();
        synchronized ( rainbows ) {
            for (int i = 0; i < rainbows.size(); i++) {
                rainbows.get(i).draw(drawAdaptor);
            }
        }
        Component[] cps = components.getComponents();
        for (Component component : cps) {
            drawAdaptor.drawComponent(component);
        }  
    }  
}
