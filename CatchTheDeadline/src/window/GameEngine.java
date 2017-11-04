package window;

import gameManager.Animation;
import gameManager.Camera;
import gameManager.Handler;
import gameManager.InputManager;
import gameobjects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class GameEngine extends Canvas implements Runnable {

    private Thread thread;
    private boolean isRunning = false;
    private Handler handler;
    private Camera cam;
    public static int WIDTH, HEIGHT;

    private ImageIcon level = new ImageIcon(getClass().getResource("/images/level.png"));
    
    public void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        
        //Animation loader = new Animation();
        //level = loader.loadImage("/images/level.png");
        handler = new Handler();
        cam = new Camera( 0, 0 );
        handler.addObject( new Player(100, 100, handler,ObjectType.Player) );
        handler.level();
        this.addKeyListener( new InputManager(handler) );
    }

    public void loadImageLevel( BufferedImage img ) {
    	
    }
    
    public synchronized void start() {
        if(isRunning) {
            return;
        }

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double fps = 60.0;
        double ns = 1000000000 / fps;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                updateFrame();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    // Update frames
    private void updateFrame() {
        handler.updateFrame();
        for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
        	if( handler.objectLinkedList.get(i).getType() == ObjectType.Player )
        		cam.updateFrame( handler.objectLinkedList.get(i));
        }
    }

    // Render Images as fast as a computer can
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            // 3 is the most efficient number of buffers. A value larger than this
            // would reduce the performance of the game
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) graphics;
        
        /////////////////////////////////////////////////////////////////////////////
        // Graphics of the game
        //graphics.setColor(Color.BLACK);
        //graphics.fillRect(0,0, getWidth(), getHeight());
    	graphics.drawImage(level.getImage(), 0, 0, null);
    	
    	
        g2d.translate(cam.getXPos(), cam.getYPos() ); // begin of cam
        handler.render(graphics);
        g2d.translate(cam.getXPos(), -cam.getYPos() ); //end of cam
        /////////////////////////////////////////////////////////////////////////////

        graphics.dispose();
        bufferStrategy.show();
    }

    public void startTheGame() {
        new Window(1000, 500, "Catch The Deadline", new GameEngine());
    }


}