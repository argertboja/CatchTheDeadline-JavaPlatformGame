package window;

import gameManager.*;
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
    static Texture texture;
    public static int WIDTH, HEIGHT;

    private BufferedImage level1 = null;

    private ImageIcon level = new ImageIcon(getClass().getResource("/images/level.png"));
    
    public void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();

        BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
        level1 = bufferedImageLoader.loadImg("/images/level1.png");

        texture = new Texture();
        handler = new Handler();
        cam = new Camera( 0, 0 );

        //handler.addObject( new Player(100, 100, handler,ObjectType.Player) );
        //handler.level();
        createLevel(level1);

        this.addKeyListener( new InputManager(handler) );
    }

    public void createLevel( BufferedImage img ) {
    	int width = img.getWidth();
    	int height = img.getHeight();
    	for (int i = 0; i < height; i++) {
    	    for (int j = 0; j < width; j++) {
    	        int pixel = img.getRGB(i, j);
    	        int red = (pixel >> 16) & 0xff;
    	        int green = (pixel >> 8) & 0xff;
    	        int blue = (pixel) & 0xff;

    	        if (red == 255 && green == 255 && blue == 255) { // if the pixel is white 
                    handler.addObject( new Block( i * 32 - 400, j * 32 + 72, 1, ObjectType.Block) );
                }
                if (red == 0 && green == 0 && blue == 255) { // if the pixel is blue
                    handler.addObject( new Player(i + 200, j + 100, handler,ObjectType.Player) );
                }
                if (red == 127 && green == 127 && blue == 127) { // if the pixel is grey 
                    handler.addObject( new Block( i * 32 - 400, j * 32 + 72, 2, ObjectType.Block) );
                }
                if (red == 237 && green == 28 && blue == 36) { // if the pixel is red 
                    handler.addObject( new Food( i * 32 - 400, j * 32 + 72, ObjectType.PowerUp, 3) );
                }
            }
        }
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

    public static Texture getInstance() {
    	return texture;
    }
    
    public void startTheGame() {
        new Window(1000, 510, "Catch The Deadline", new GameEngine());
    }
}