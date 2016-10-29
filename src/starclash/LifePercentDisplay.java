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
public class LifePercentDisplay implements Drawable {

    private final StarshipFactory me, enemy;

    private final Rectangle topBD, botBD;
    private final Rectangle top, bot;
    
    public LifePercentDisplay(StarshipFactory me, StarshipFactory enemy) {
        this.me = me;
        this.enemy = enemy;
        topBD = new Rectangle(0, 0,      1, 0.006f, new Color(0, 0, 0, 0.5f));
        botBD = new Rectangle(0, 0.995f, 1, 0.006f, new Color(0, 0, 0, 0.5f));
        top   = new Rectangle(0, 0,      1, 0.006f, new Color(1, 0, 0, 0.8f));
        bot   = new Rectangle(0, 0.995f, 1, 0.006f, new Color(0, 1, 0, 0.8f));
    }
    
    @Override
    public void draw(DrawAdaptor drawAdaptor) {
        top.setWidht( enemy.getLifePercent() );
        bot.setWidht(    me.getLifePercent() );
        drawAdaptor.drawRectangle(topBD);
        drawAdaptor.drawRectangle(botBD);
        drawAdaptor.drawRectangle(top);
        drawAdaptor.drawRectangle(bot);
    }

}
