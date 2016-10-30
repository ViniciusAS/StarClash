package starclash.starships;

import java.util.LinkedList;
import java.util.List;
import starclash.gamemode.CommandSender;
import starclash.gamemode.listeners.DamageListener;

/**
 *
 * @author viniciusas
 */
public abstract class StarshipFactory {
    
    public abstract String getName();
    
    public abstract StarshipFactory getNext();
    
    public abstract StarshipDraw newStarshipDraw();
    
    public abstract StarshipCollision newStarshipCollision();
    
    public abstract StarshipShot newShot();
    public abstract StarshipShot newShot(float x, float y);
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private final List<DamageListener> damageListeners = new LinkedList<>();
    
    public void addDamageListener(DamageListener damageListener){
        damageListeners.add(damageListener);
    }
    
    protected void notifyDamage(int damage){
        for (DamageListener damageListener : damageListeners) {
            damageListener.onDamageTaken(damage);
        }
    }
    protected void notifyDie(){
        for (DamageListener damageListener : damageListeners) {
            damageListener.onDie();
        }
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     *
     * @return man percentual between 0 and 1
     */
    public abstract float getManaPercent();
    /**
     *
     * @return life percentual between 0 and 1
     */
    public abstract float getLifePercent();
    
    /** Proccess taken damage.
     *
     * call damage taken listener
     * call die listener when dead
     * 
     * @param damage
     * @return if enemy is dead
     */
    public abstract boolean takeDamage(int damage);
    
    
    protected CommandSender commandSender;

    /**
     * Criado para enviar instancias ainda nao criadas
     * ao instanciar a nave, antes de comecar uma batalha
     * 
     * @param commandSender
     */
    public void prepareStarship(CommandSender commandSender){
        this.commandSender = commandSender;
    }
    
    
    public abstract void doSpecial();
    public abstract void doSpecial(float x, float y);
    
    public abstract boolean isEnemy();
    
    public abstract float getShipSpeed();
    public abstract void setShipSpeed(float speed);
    
    public abstract float getWidth();
    public abstract float getHeight();
    
    public abstract float getX();
    public abstract float getY();
    
    public abstract void setX(float x);
    public abstract void setY(float y);
    
}
