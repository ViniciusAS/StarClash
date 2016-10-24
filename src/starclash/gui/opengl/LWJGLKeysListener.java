package starclash.gui.opengl;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import starclash.gui.KeysListenerAdaptor;


public class LWJGLKeysListener implements KeysListenerAdaptor {

    public LWJGLKeysListener(long window) {        
        GLFW.glfwSetKeyCallback(window, new KeyCallback());
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    
    private final List<KeyListener> observers = new ArrayList<>();
    
    @Override
    public void addKeyListener(KeyListener keyListener) {
        observers.add(keyListener);
    }

    @Override
    public void addKeyListener(Key key, KeyListener keyListener) {
        keyListener.setKey(key);
        addKeyListener(keyListener);
    }
    

    @Override
    public void removeKeyListener(KeyListener keyListener) {
        observers.remove(keyListener);
    }

    @Override
    public void clearListeners() {
        observers.clear();
    }
    
    @Override
    public void notifyObservers(Key key) {
        for (KeyListener observer : observers) {
            if ( observer.getKey() == key ){
                observer.pressed();
            }
        }
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    private class KeyCallback implements GLFWKeyCallbackI {

        @Override
        public void invoke(long w, int key, int scancode, int action, int mods) {
            switch (key){
                
                case GLFW.GLFW_KEY_SPACE: notifyObservers( Key.KEY_SPACE );             break;
                case GLFW.GLFW_KEY_APOSTROPHE: notifyObservers( Key.KEY_APOSTROPHE );   break;
                case GLFW.GLFW_KEY_COMMA: notifyObservers( Key.KEY_COMMA );             break;                          
                case GLFW.GLFW_KEY_MINUS: notifyObservers( Key.KEY_MINUS );             break;
                case GLFW.GLFW_KEY_PERIOD: notifyObservers( Key.KEY_PERIOD );           break;
                case GLFW.GLFW_KEY_SLASH: notifyObservers( Key.KEY_SLASH );             break;

                case GLFW.GLFW_KEY_0: notifyObservers( Key.KEY_0 ); break;
                case GLFW.GLFW_KEY_1: notifyObservers( Key.KEY_1 ); break;
                case GLFW.GLFW_KEY_2: notifyObservers( Key.KEY_2 ); break;
                case GLFW.GLFW_KEY_3: notifyObservers( Key.KEY_3 ); break;
                case GLFW.GLFW_KEY_4: notifyObservers( Key.KEY_4 ); break;
                case GLFW.GLFW_KEY_5: notifyObservers( Key.KEY_5 ); break;
                case GLFW.GLFW_KEY_6: notifyObservers( Key.KEY_6 ); break;
                case GLFW.GLFW_KEY_7: notifyObservers( Key.KEY_7 ); break;
                case GLFW.GLFW_KEY_8: notifyObservers( Key.KEY_8 ); break;
                case GLFW.GLFW_KEY_9: notifyObservers( Key.KEY_9 ); break;
                case GLFW.GLFW_KEY_SEMICOLON: notifyObservers( Key.KEY_SEMICOLON ); break; 
                case GLFW.GLFW_KEY_EQUAL: notifyObservers( Key.KEY_EQUAL );         break;

                case GLFW.GLFW_KEY_A: notifyObservers( Key.KEY_A ); break;
                case GLFW.GLFW_KEY_B: notifyObservers( Key.KEY_B ); break;
                case GLFW.GLFW_KEY_C: notifyObservers( Key.KEY_C ); break;
                case GLFW.GLFW_KEY_D: notifyObservers( Key.KEY_D ); break;
                case GLFW.GLFW_KEY_E: notifyObservers( Key.KEY_E ); break;
                case GLFW.GLFW_KEY_F: notifyObservers( Key.KEY_F ); break;
                case GLFW.GLFW_KEY_G: notifyObservers( Key.KEY_G ); break;
                case GLFW.GLFW_KEY_H: notifyObservers( Key.KEY_H ); break;
                case GLFW.GLFW_KEY_I: notifyObservers( Key.KEY_I ); break;
                case GLFW.GLFW_KEY_J: notifyObservers( Key.KEY_J ); break;
                case GLFW.GLFW_KEY_K: notifyObservers( Key.KEY_K ); break;
                case GLFW.GLFW_KEY_L: notifyObservers( Key.KEY_L ); break;
                case GLFW.GLFW_KEY_M: notifyObservers( Key.KEY_M ); break;
                case GLFW.GLFW_KEY_N: notifyObservers( Key.KEY_N ); break;
                case GLFW.GLFW_KEY_O: notifyObservers( Key.KEY_O ); break;
                case GLFW.GLFW_KEY_P: notifyObservers( Key.KEY_P ); break;
                case GLFW.GLFW_KEY_Q: notifyObservers( Key.KEY_Q ); break;
                case GLFW.GLFW_KEY_R: notifyObservers( Key.KEY_R ); break;
                case GLFW.GLFW_KEY_S: notifyObservers( Key.KEY_S ); break;
                case GLFW.GLFW_KEY_T: notifyObservers( Key.KEY_T ); break;
                case GLFW.GLFW_KEY_U: notifyObservers( Key.KEY_U ); break;
                case GLFW.GLFW_KEY_V: notifyObservers( Key.KEY_V ); break;
                case GLFW.GLFW_KEY_W: notifyObservers( Key.KEY_W ); break;
                case GLFW.GLFW_KEY_X: notifyObservers( Key.KEY_X ); break;
                case GLFW.GLFW_KEY_Y: notifyObservers( Key.KEY_Y ); break;
                case GLFW.GLFW_KEY_Z: notifyObservers( Key.KEY_Z ); break;

                case GLFW.GLFW_KEY_LEFT_BRACKET: notifyObservers( Key.KEY_LEFT_BRACKET );   break;
                case GLFW.GLFW_KEY_BACKSLASH: notifyObservers( Key.KEY_BACKSLASH );         break;
                case GLFW.GLFW_KEY_RIGHT_BRACKET: notifyObservers( Key.KEY_RIGHT_BRACKET ); break;
                case GLFW.GLFW_KEY_GRAVE_ACCENT: notifyObservers( Key.KEY_GRAVE_ACCENT );   break;
                case GLFW.GLFW_KEY_WORLD_1: notifyObservers( Key.KEY_WORLD_1 );             break;
                case GLFW.GLFW_KEY_WORLD_2: notifyObservers( Key.KEY_WORLD_2 );             break;

                case GLFW.GLFW_KEY_ESCAPE: notifyObservers( Key.KEY_ESCAPE );                   break;
                case GLFW.GLFW_KEY_ENTER: notifyObservers( Key.KEY_ENTER );                     break;
                case GLFW.GLFW_KEY_TAB: notifyObservers( Key.KEY_TAB );                         break;
                case GLFW.GLFW_KEY_BACKSPACE: notifyObservers( Key.KEY_BACKSPACE );             break;
                case GLFW.GLFW_KEY_INSERT: notifyObservers( Key.KEY_INSERT );                   break;
                case GLFW.GLFW_KEY_DELETE: notifyObservers( Key.KEY_DELETE );                   break;
                case GLFW.GLFW_KEY_RIGHT: notifyObservers( Key.KEY_RIGHT );                     break;
                case GLFW.GLFW_KEY_LEFT: notifyObservers( Key.KEY_LEFT );                       break;
                case GLFW.GLFW_KEY_DOWN: notifyObservers( Key.KEY_DOWN );                       break;
                case GLFW.GLFW_KEY_UP: notifyObservers( Key.KEY_UP );                           break;
                case GLFW.GLFW_KEY_PAGE_UP: notifyObservers( Key.KEY_PAGE_UP );                 break;
                case GLFW.GLFW_KEY_PAGE_DOWN: notifyObservers( Key.KEY_PAGE_DOWN );             break;
                case GLFW.GLFW_KEY_HOME: notifyObservers( Key.KEY_HOME );                       break;
                case GLFW.GLFW_KEY_END: notifyObservers( Key.KEY_END );                         break;
                case GLFW.GLFW_KEY_CAPS_LOCK: notifyObservers( Key.KEY_CAPS_LOCK );             break;
                case GLFW.GLFW_KEY_SCROLL_LOCK: notifyObservers( Key.KEY_SCROLL_LOCK );         break;
                case GLFW.GLFW_KEY_NUM_LOCK: notifyObservers( Key.KEY_NUM_LOCK );               break;
                case GLFW.GLFW_KEY_PRINT_SCREEN: notifyObservers( Key.KEY_PRINT_SCREEN );       break;
                case GLFW.GLFW_KEY_PAUSE: notifyObservers( Key.KEY_PAUSE );                     break;
                case GLFW.GLFW_KEY_F1: notifyObservers( Key.KEY_F1 );                           break;
                case GLFW.GLFW_KEY_F2: notifyObservers( Key.KEY_F2 );                           break;
                case GLFW.GLFW_KEY_F3: notifyObservers( Key.KEY_F3 );                           break;
                case GLFW.GLFW_KEY_F4: notifyObservers( Key.KEY_F4 );                           break;
                case GLFW.GLFW_KEY_F5: notifyObservers( Key.KEY_F5 );                           break;
                case GLFW.GLFW_KEY_F6: notifyObservers( Key.KEY_F6 );                           break;
                case GLFW.GLFW_KEY_F7: notifyObservers( Key.KEY_F7 );                           break;
                case GLFW.GLFW_KEY_F8: notifyObservers( Key.KEY_F8 );                           break;
                case GLFW.GLFW_KEY_F9: notifyObservers( Key.KEY_F9 );                           break;
                case GLFW.GLFW_KEY_F10: notifyObservers( Key.KEY_F10 );                         break;
                case GLFW.GLFW_KEY_F11: notifyObservers( Key.KEY_F11 );                         break;
                case GLFW.GLFW_KEY_F12: notifyObservers( Key.KEY_F12 );                         break;
                case GLFW.GLFW_KEY_KP_0: notifyObservers( Key.KEY_KP_0 );                       break;
                case GLFW.GLFW_KEY_KP_1: notifyObservers( Key.KEY_KP_1 );                       break;
                case GLFW.GLFW_KEY_KP_2: notifyObservers( Key.KEY_KP_2 );                       break;
                case GLFW.GLFW_KEY_KP_3: notifyObservers( Key.KEY_KP_3 );                       break;
                case GLFW.GLFW_KEY_KP_4: notifyObservers( Key.KEY_KP_4 );                       break;
                case GLFW.GLFW_KEY_KP_5: notifyObservers( Key.KEY_KP_5 );                       break;
                case GLFW.GLFW_KEY_KP_6: notifyObservers( Key.KEY_KP_6 );                       break;
                case GLFW.GLFW_KEY_KP_7: notifyObservers( Key.KEY_KP_7 );                       break;
                case GLFW.GLFW_KEY_KP_8: notifyObservers( Key.KEY_KP_8 );                       break;
                case GLFW.GLFW_KEY_KP_9: notifyObservers( Key.KEY_KP_9 );                       break;                       
                case GLFW.GLFW_KEY_KP_DECIMAL: notifyObservers( Key.KEY_KP_DECIMAL );           break;
                case GLFW.GLFW_KEY_KP_DIVIDE: notifyObservers( Key.KEY_KP_DIVIDE );             break;
                case GLFW.GLFW_KEY_KP_MULTIPLY: notifyObservers( Key.KEY_KP_MULTIPLY );         break;
                case GLFW.GLFW_KEY_KP_SUBTRACT: notifyObservers( Key.KEY_KP_SUBTRACT );         break;
                case GLFW.GLFW_KEY_KP_ADD: notifyObservers( Key.KEY_KP_ADD );                   break;
                case GLFW.GLFW_KEY_KP_ENTER: notifyObservers( Key.KEY_KP_ENTER );               break;
                case GLFW.GLFW_KEY_KP_EQUAL: notifyObservers( Key.KEY_KP_EQUAL );               break;
                case GLFW.GLFW_KEY_LEFT_SHIFT: notifyObservers( Key.KEY_LEFT_SHIFT );           break;
                case GLFW.GLFW_KEY_LEFT_CONTROL: notifyObservers( Key.KEY_LEFT_CONTROL );       break;
                case GLFW.GLFW_KEY_LEFT_ALT: notifyObservers( Key.KEY_LEFT_ALT );               break;
                case GLFW.GLFW_KEY_LEFT_SUPER: notifyObservers( Key.KEY_LEFT_SUPER );           break;
                case GLFW.GLFW_KEY_RIGHT_SHIFT: notifyObservers( Key.KEY_RIGHT_SHIFT );         break;
                case GLFW.GLFW_KEY_RIGHT_CONTROL: notifyObservers( Key.KEY_RIGHT_CONTROL );     break;
                case GLFW.GLFW_KEY_RIGHT_ALT: notifyObservers( Key.KEY_RIGHT_ALT );             break;
                case GLFW.GLFW_KEY_RIGHT_SUPER: notifyObservers( Key.KEY_RIGHT_SUPER );         break;
                case GLFW.GLFW_KEY_LAST: notifyObservers( Key.KEY_LAST );                       break;
                
                case GLFW.GLFW_MOUSE_BUTTON_LEFT: notifyObservers( Key.MOUSE_BUTTON_LEFT );     break;
                case GLFW.GLFW_MOUSE_BUTTON_RIGHT: notifyObservers( Key.MOUSE_BUTTON_RIGHT );   break;
                case GLFW.GLFW_MOUSE_BUTTON_MIDDLE: notifyObservers( Key.MOUSE_BUTTON_MIDDLE ); break;
                case GLFW.GLFW_MOUSE_BUTTON_4: notifyObservers( Key.MOUSE_BUTTON_4 );           break;
                case GLFW.GLFW_MOUSE_BUTTON_5: notifyObservers( Key.MOUSE_BUTTON_5 );           break;
                case GLFW.GLFW_MOUSE_BUTTON_6: notifyObservers( Key.MOUSE_BUTTON_6 );           break;
                case GLFW.GLFW_MOUSE_BUTTON_7: notifyObservers( Key.MOUSE_BUTTON_7 );           break;
                case GLFW.GLFW_MOUSE_BUTTON_LAST: notifyObservers( Key.MOUSE_BUTTON_LAST );     break;
                
            }
        }
    }
}
