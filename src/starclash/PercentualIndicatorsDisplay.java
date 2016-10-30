package starclash;

import starclash.gui.DrawAdaptor;
import starclash.gui.Drawable;
import starclash.gui.components.Color;
import starclash.gui.components.Rectangle;
import starclash.starships.StarshipFactory;

/**
 *
 * @author viniciusas
 */
public class PercentualIndicatorsDisplay implements Drawable {

    private final StarshipFactory me, enemy;

    private final Rectangle topBD, botBD;
    private final Rectangle topLife, botLife;
    private final Rectangle topMana, botMana;
    
    public PercentualIndicatorsDisplay(StarshipFactory me, StarshipFactory enemy) {
        this.me = me;
        this.enemy = enemy;
        topBD   = new Rectangle(0, 0,      1, 0.012f, new Color(0, 0, 0, 0.5f));
        botBD   = new Rectangle(0, 0.988f, 1, 0.012f, new Color(0, 0, 0, 0.5f));
        
        topLife = new Rectangle(0, 0,      1, 0.006f, new Color(1, 0, 0, 0.8f));
        botLife = new Rectangle(0, 0.995f, 1, 0.006f, new Color(0, 1, 0, 0.8f));
        
        topMana = new Rectangle(0, 0.006f, 1, 0.006f, new Color(0, 0, 1, 0.8f));
        botMana = new Rectangle(0, 0.988f, 1, 0.006f, new Color(0, 0, 1, 0.8f));
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        topLife.setWidth( enemy.getLifePercent() );
        botLife.setWidth(    me.getLifePercent() );
        
        topMana.setWidth( enemy.getManaPercent() );
        botMana.setWidth(    me.getManaPercent() );
        
        drawAdaptor.drawRectangle(topBD);
        drawAdaptor.drawRectangle(botBD);
        
        drawAdaptor.drawRectangle(topMana);
        drawAdaptor.drawRectangle(botMana);
        
        drawAdaptor.drawRectangle(topLife);
        drawAdaptor.drawRectangle(botLife);
    }

}
