package starclash.gui;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

/**
 *
 * @author Vinicius Santos
 */
public class GameInterfaceLWJGL implements GameInterfaceAdaptor, Runnable {

    private Thread interfaceThread;
    
    private final int WIDTH = 500;
    private final int HEIGHT = 600;
    
    // OpenGL
    private long window;
    
    private final Rectangle rect;

    public GameInterfaceLWJGL(Rectangle rect) {
        this.rect = rect;        
    }

    /**
     * Cria uma thread da interface grafica e inicia
     */
    @Override
    public void start() {
        interfaceThread = new Thread(this);
        interfaceThread.start();
    }
    
    /**
     * Inicia a interface grafica
     */
    @Override
    public void run() {

        try {
                init();
                loop();

                // Free the window callbacks and destroy the window
                Callbacks.glfwFreeCallbacks(window);
                GLFW.glfwDestroyWindow(window);
        } finally {
                // Terminate GLFW and free the error callback
                GLFW.glfwTerminate();
                GLFW.glfwSetErrorCallback(null).free();
        }
    }
    
    private void init(){
        
        GLFWErrorCallback.createPrint(System.err).set();

        if ( !GLFW.glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
//        GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
//        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
        
        
        // Create the window
        window = GLFW.glfwCreateWindow(
                WIDTH, HEIGHT, 
                "StarClash",
                MemoryUtil.NULL, MemoryUtil.NULL
        );
        if ( window == MemoryUtil.NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        GLFW.glfwSetKeyCallback(window, new KeyCallback());

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        
        // Center the window
        GLFW.glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);
        // Enable v-sync
        GLFW.glfwSwapInterval(1);

        // Make the window visible
        GLFW.glfwShowWindow(window);
    }
    
    private void loop(){
        
        GL.createCapabilities(); 

        GL11.glClearColor(200, 200, 200, 0);

        while ( !GLFW.glfwWindowShouldClose(window) ) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            
            
//            GL11.glShadeModel(GL11.GL_FLAT);

            GL11.glBegin(GL11.GL_QUADS);
//                GL11.glColor3f(1.0f, 1.0f, 1.0f);
//                GL11.glVertex3f(0, 0, 0);
//                GL11.glVertex3f(0, 1, 0);
//                GL11.glVertex3f(1, 0, 0);
//                GL11.glVertex3f(1, 1, 0);
//
//                GL11.glColor3f(1.0f, 1.0f, 0.0f);
//                GL11.glVertex3f(1, 0, 1);
//                GL11.glVertex3f(1, 1,1);
//
//                GL11.glColor3f(0.0f, 1.0f, 1.0f);
//                GL11.glVertex3f(0, 0, 1);
//                GL11.glVertex3f(0, 1,1);
//
//                GL11.glColor3f(1.0f, 0.0f, 0.0f);
//                GL11.glVertex3f(0, 0, 0);
//                GL11.glVertex3f(0, 1,0);
                
            GL11.glColor4f(rect.getColor().getR(), rect.getColor().getG(), rect.getColor().getB(), rect.getColor().getA());
            GL11.glVertex3f(rect.getX(), rect.getY(), 0);
            GL11.glVertex3f(rect.getX()+rect.getWidht(), rect.getY(), 0);
            GL11.glVertex3f(rect.getX()+rect.getWidht(), rect.getY()+rect.getHeight(), 0);
            GL11.glVertex3f(rect.getX(), rect.getY()+rect.getHeight(), 0);
            
            GL11.glEnd();
            
//    GL11.glEnable(GL11.GL_TEXTURE_2D);
//    GL11.glEnable(GL11.GL_NORMALIZE);
//            GL11.glBegin(GL11.GL_QUADS);
//            {
//                GL11.glTexCoord2f(0,0);
//                GL11.glVertex2f(100,100);
//                GL11.glTexCoord2f(1,0);
//                GL11.glVertex2f(100+rect.getX(),100);
//                GL11.glTexCoord2f(1,1);
//                GL11.glVertex2f(100+rect.getWidht(),100+rect.getHeight());
//                GL11.glTexCoord2f(0,1);
//                GL11.glVertex2f(100,100+rect.getHeight());
//            }
//                
//                
//                
//            GL11.glEnd();
            

            GLFW.glfwSwapBuffers(window); // swap the color buffers
            
            // Poll for window events. The key callback will only be invoked during this call.
            GLFW.glfwPollEvents();
        }
    }

    @Override
    public void close() {
        GLFW.glfwSetWindowShouldClose(window, true);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private float toPositionX(float x){
        return x-1f;
    }
    
    private float toPositionY(float y){
        return y-1f;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    private final List<KeyListener> observers = new ArrayList<>();
    
    @Override
    public void addKeyListener(KeyListener keyListener) {
        observers.add(keyListener);
    }

    @Override
    public void removeKeyListener(KeyListener keyListener) {
        observers.remove(keyListener);
    }

    @Override
    public void notifyObservers(Key key) {
        for (KeyListener observer : observers) {
            if ( observer.getKey() == key ){
                observer.clicked();
            }
        }
    }

    @Override
    public void addRectangle(Rectangle rectangle) {
    }

    @Override
    public void removeRectangle(Rectangle rectangle) {
    }

    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    private class KeyCallback implements GLFWKeyCallbackI {

        @Override
        public void invoke(long w, int key, int scancode, int action, int mods) {
            switch (key){
                
                case GLFW.GLFW_KEY_SPACE: notifyObservers( Key.KEY_SPACE );
                case GLFW.GLFW_KEY_APOSTROPHE: notifyObservers( Key.KEY_APOSTROPHE );
                case GLFW.GLFW_KEY_COMMA: notifyObservers( Key.KEY_COMMA );
                case GLFW.GLFW_KEY_MINUS: notifyObservers( Key.KEY_MINUS );
                case GLFW.GLFW_KEY_PERIOD: notifyObservers( Key.KEY_PERIOD );
                case GLFW.GLFW_KEY_SLASH: notifyObservers( Key.KEY_SLASH );

                case GLFW.GLFW_KEY_0: notifyObservers( Key.KEY_0 );
                case GLFW.GLFW_KEY_1: notifyObservers( Key.KEY_1 );
                case GLFW.GLFW_KEY_2: notifyObservers( Key.KEY_2 );
                case GLFW.GLFW_KEY_3: notifyObservers( Key.KEY_3 );
                case GLFW.GLFW_KEY_4: notifyObservers( Key.KEY_4 );
                case GLFW.GLFW_KEY_5: notifyObservers( Key.KEY_5 );
                case GLFW.GLFW_KEY_6: notifyObservers( Key.KEY_6 );
                case GLFW.GLFW_KEY_7: notifyObservers( Key.KEY_7 );
                case GLFW.GLFW_KEY_8: notifyObservers( Key.KEY_8 );
                case GLFW.GLFW_KEY_9: notifyObservers( Key.KEY_9 );
                case GLFW.GLFW_KEY_SEMICOLON: notifyObservers( Key.KEY_SEMICOLON );
                case GLFW.GLFW_KEY_EQUAL: notifyObservers( Key.KEY_EQUAL );

                case GLFW.GLFW_KEY_A: notifyObservers( Key.KEY_A );
                case GLFW.GLFW_KEY_B: notifyObservers( Key.KEY_B );
                case GLFW.GLFW_KEY_C: notifyObservers( Key.KEY_C );
                case GLFW.GLFW_KEY_D: notifyObservers( Key.KEY_D );
                case GLFW.GLFW_KEY_E: notifyObservers( Key.KEY_E );
                case GLFW.GLFW_KEY_F: notifyObservers( Key.KEY_F );
                case GLFW.GLFW_KEY_G: notifyObservers( Key.KEY_G );
                case GLFW.GLFW_KEY_H: notifyObservers( Key.KEY_H );
                case GLFW.GLFW_KEY_I: notifyObservers( Key.KEY_I );
                case GLFW.GLFW_KEY_J: notifyObservers( Key.KEY_J );
                case GLFW.GLFW_KEY_K: notifyObservers( Key.KEY_K );
                case GLFW.GLFW_KEY_L: notifyObservers( Key.KEY_L );
                case GLFW.GLFW_KEY_M: notifyObservers( Key.KEY_M );
                case GLFW.GLFW_KEY_N: notifyObservers( Key.KEY_N );
                case GLFW.GLFW_KEY_O: notifyObservers( Key.KEY_O );
                case GLFW.GLFW_KEY_P: notifyObservers( Key.KEY_P );
                case GLFW.GLFW_KEY_Q: notifyObservers( Key.KEY_Q );
                case GLFW.GLFW_KEY_R: notifyObservers( Key.KEY_R );
                case GLFW.GLFW_KEY_S: notifyObservers( Key.KEY_S );
                case GLFW.GLFW_KEY_T: notifyObservers( Key.KEY_T );
                case GLFW.GLFW_KEY_U: notifyObservers( Key.KEY_U );
                case GLFW.GLFW_KEY_V: notifyObservers( Key.KEY_V );
                case GLFW.GLFW_KEY_W: notifyObservers( Key.KEY_W );
                case GLFW.GLFW_KEY_X: notifyObservers( Key.KEY_X );
                case GLFW.GLFW_KEY_Y: notifyObservers( Key.KEY_Y );
                case GLFW.GLFW_KEY_Z: notifyObservers( Key.KEY_Z );

                case GLFW.GLFW_KEY_LEFT_BRACKET: notifyObservers( Key.KEY_LEFT_BRACKET );
                case GLFW.GLFW_KEY_BACKSLASH: notifyObservers( Key.KEY_BACKSLASH );
                case GLFW.GLFW_KEY_RIGHT_BRACKET: notifyObservers( Key.KEY_RIGHT_BRACKET );
                case GLFW.GLFW_KEY_GRAVE_ACCENT: notifyObservers( Key.KEY_GRAVE_ACCENT );
                case GLFW.GLFW_KEY_WORLD_1: notifyObservers( Key.KEY_WORLD_1 );
                case GLFW.GLFW_KEY_WORLD_2: notifyObservers( Key.KEY_WORLD_2 );

                case GLFW.GLFW_KEY_ESCAPE: notifyObservers( Key.KEY_ESCAPE );
                case GLFW.GLFW_KEY_ENTER: notifyObservers( Key.KEY_ENTER );
                case GLFW.GLFW_KEY_TAB: notifyObservers( Key.KEY_TAB );
                case GLFW.GLFW_KEY_BACKSPACE: notifyObservers( Key.KEY_BACKSPACE );
                case GLFW.GLFW_KEY_INSERT: notifyObservers( Key.KEY_INSERT );
                case GLFW.GLFW_KEY_DELETE: notifyObservers( Key.KEY_DELETE );
                case GLFW.GLFW_KEY_RIGHT: notifyObservers( Key.KEY_RIGHT );
                case GLFW.GLFW_KEY_LEFT: notifyObservers( Key.KEY_LEFT );
                case GLFW.GLFW_KEY_DOWN: notifyObservers( Key.KEY_DOWN );
                case GLFW.GLFW_KEY_UP: notifyObservers( Key.KEY_UP );
                case GLFW.GLFW_KEY_PAGE_UP: notifyObservers( Key.KEY_PAGE_UP );
                case GLFW.GLFW_KEY_PAGE_DOWN: notifyObservers( Key.KEY_PAGE_DOWN );
                case GLFW.GLFW_KEY_HOME: notifyObservers( Key.KEY_HOME );
                case GLFW.GLFW_KEY_END: notifyObservers( Key.KEY_END );
                case GLFW.GLFW_KEY_CAPS_LOCK: notifyObservers( Key.KEY_CAPS_LOCK );
                case GLFW.GLFW_KEY_SCROLL_LOCK: notifyObservers( Key.KEY_SCROLL_LOCK );
                case GLFW.GLFW_KEY_NUM_LOCK: notifyObservers( Key.KEY_NUM_LOCK );
                case GLFW.GLFW_KEY_PRINT_SCREEN: notifyObservers( Key.KEY_PRINT_SCREEN );
                case GLFW.GLFW_KEY_PAUSE: notifyObservers( Key.KEY_PAUSE );
                case GLFW.GLFW_KEY_F1: notifyObservers( Key.KEY_F1 );
                case GLFW.GLFW_KEY_F2: notifyObservers( Key.KEY_F2 );
                case GLFW.GLFW_KEY_F3: notifyObservers( Key.KEY_F3 );
                case GLFW.GLFW_KEY_F4: notifyObservers( Key.KEY_F4 );
                case GLFW.GLFW_KEY_F5: notifyObservers( Key.KEY_F5 );
                case GLFW.GLFW_KEY_F6: notifyObservers( Key.KEY_F6 );
                case GLFW.GLFW_KEY_F7: notifyObservers( Key.KEY_F7 );
                case GLFW.GLFW_KEY_F8: notifyObservers( Key.KEY_F8 );
                case GLFW.GLFW_KEY_F9: notifyObservers( Key.KEY_F9 );
                case GLFW.GLFW_KEY_F10: notifyObservers( Key.KEY_F10 );
                case GLFW.GLFW_KEY_F11: notifyObservers( Key.KEY_F11 );
                case GLFW.GLFW_KEY_F12: notifyObservers( Key.KEY_F12 );
                case GLFW.GLFW_KEY_KP_0: notifyObservers( Key.KEY_KP_0 );
                case GLFW.GLFW_KEY_KP_1: notifyObservers( Key.KEY_KP_1 );
                case GLFW.GLFW_KEY_KP_2: notifyObservers( Key.KEY_KP_2 );
                case GLFW.GLFW_KEY_KP_3: notifyObservers( Key.KEY_KP_3 );
                case GLFW.GLFW_KEY_KP_4: notifyObservers( Key.KEY_KP_4 );
                case GLFW.GLFW_KEY_KP_5: notifyObservers( Key.KEY_KP_5 );
                case GLFW.GLFW_KEY_KP_6: notifyObservers( Key.KEY_KP_6 );
                case GLFW.GLFW_KEY_KP_7: notifyObservers( Key.KEY_KP_7 );
                case GLFW.GLFW_KEY_KP_8: notifyObservers( Key.KEY_KP_8 );
                case GLFW.GLFW_KEY_KP_9: notifyObservers( Key.KEY_KP_9 );
                case GLFW.GLFW_KEY_KP_DECIMAL: notifyObservers( Key.KEY_KP_DECIMAL );
                case GLFW.GLFW_KEY_KP_DIVIDE: notifyObservers( Key.KEY_KP_DIVIDE );
                case GLFW.GLFW_KEY_KP_MULTIPLY: notifyObservers( Key.KEY_KP_MULTIPLY );
                case GLFW.GLFW_KEY_KP_SUBTRACT: notifyObservers( Key.KEY_KP_SUBTRACT );
                case GLFW.GLFW_KEY_KP_ADD: notifyObservers( Key.KEY_KP_ADD );
                case GLFW.GLFW_KEY_KP_ENTER: notifyObservers( Key.KEY_KP_ENTER );
                case GLFW.GLFW_KEY_KP_EQUAL: notifyObservers( Key.KEY_KP_EQUAL );
                case GLFW.GLFW_KEY_LEFT_SHIFT: notifyObservers( Key.KEY_LEFT_SHIFT );
                case GLFW.GLFW_KEY_LEFT_CONTROL: notifyObservers( Key.KEY_LEFT_CONTROL );
                case GLFW.GLFW_KEY_LEFT_ALT: notifyObservers( Key.KEY_LEFT_ALT );
                case GLFW.GLFW_KEY_LEFT_SUPER: notifyObservers( Key.KEY_LEFT_SUPER );
                case GLFW.GLFW_KEY_RIGHT_SHIFT: notifyObservers( Key.KEY_RIGHT_SHIFT );
                case GLFW.GLFW_KEY_RIGHT_CONTROL: notifyObservers( Key.KEY_RIGHT_CONTROL );
                case GLFW.GLFW_KEY_RIGHT_ALT: notifyObservers( Key.KEY_RIGHT_ALT );
                case GLFW.GLFW_KEY_RIGHT_SUPER: notifyObservers( Key.KEY_RIGHT_SUPER );
                case GLFW.GLFW_KEY_LAST: notifyObservers( Key.KEY_LAST );
                
                case GLFW.GLFW_MOUSE_BUTTON_LEFT: notifyObservers( Key.MOUSE_BUTTON_LEFT );
                case GLFW.GLFW_MOUSE_BUTTON_RIGHT: notifyObservers( Key.MOUSE_BUTTON_RIGHT );
                case GLFW.GLFW_MOUSE_BUTTON_MIDDLE: notifyObservers( Key.MOUSE_BUTTON_MIDDLE );
                case GLFW.GLFW_MOUSE_BUTTON_4: notifyObservers( Key.MOUSE_BUTTON_4 );
                case GLFW.GLFW_MOUSE_BUTTON_5: notifyObservers( Key.MOUSE_BUTTON_5 );
                case GLFW.GLFW_MOUSE_BUTTON_6: notifyObservers( Key.MOUSE_BUTTON_6 );
                case GLFW.GLFW_MOUSE_BUTTON_7: notifyObservers( Key.MOUSE_BUTTON_7 );
                case GLFW.GLFW_MOUSE_BUTTON_LAST: notifyObservers( Key.MOUSE_BUTTON_LAST );
                
            }
        }
    }

}
