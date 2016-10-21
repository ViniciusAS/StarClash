package starclash.gui.opengl;

import starclash.gui.components.Rectangle;
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
import starclash.gui.Drawable;
import starclash.gui.GameInterfaceAdaptor;
import starclash.gui.KeysListenerAdaptor;

/**
 *
 * @author Vinicius Santos
 */
public class LWJGLGameInterface implements GameInterfaceAdaptor, Runnable {

    private Thread interfaceThread;
    
    private final int WIDTH = 500;
    private final int HEIGHT = 600;
    
    // OpenGL
    private long window;
    
    private final Rectangle rect;

    private LWJGLKeysListener keysListener;
    
    
    public LWJGLGameInterface(Rectangle rect) {
        this.rect = rect;        
    }

    @Override
    public KeysListenerAdaptor getKeysListener() {
        return keysListener;
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
        this.keysListener = new LWJGLKeysListener(window);

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
    
    @Override
    public void addDrawable(Drawable drawable) {
    }

    @Override
    public void removeDrawable(Drawable drawable) {
    }

    @Override
    public void clearDrawables() {
    }

}
