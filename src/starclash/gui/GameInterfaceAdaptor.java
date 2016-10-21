package starclash.gui;


public interface GameInterfaceAdaptor {
   
    public void start();
    
    public void close();
    
    public void addDrawable(Drawable drawable);
    public void removeDrawable(Drawable drawable);
    public void clearDrawables();
    
    public KeysListenerAdaptor getKeysListener();
    
}
