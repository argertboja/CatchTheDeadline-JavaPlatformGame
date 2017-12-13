package window;

import gameManager.*;
import gameobjects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameEngine extends Canvas implements Runnable {

    private Thread thread, timer;
    private Player player;
    private boolean isRunning = false;
    private Handler handler;
    private Camera cam;
    static Texture texture;
    public static int WIDTH, HEIGHT;
    private String countDown = "Time: 1:30";
    Font font = new Font("Cooper Black", Font.BOLD, 28);
    
    private BufferedImage level1 = null, level2 = null, level3 = null;

    private ImageIcon level = new ImageIcon(getClass().getResource("/images/level.png"));
    private ImageIcon clouds = new ImageIcon(getClass().getResource("/images/clouds.png"));
    private ImageIcon food = new ImageIcon(getClass().getResource("/images/food.png"));
    
    private int levelNo = 1;
    
    public GameEngine(int levelNo) {
        this.levelNo = levelNo;
    }
    
    public void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();

        BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
        level1 = bufferedImageLoader.loadImg("/images/level1.png");
        level2 = bufferedImageLoader.loadImg("/images/level2.png");
        level3 = bufferedImageLoader.loadImg("/images/level3.png");
        
        texture = new Texture();
        handler = new Handler();
        cam = new Camera( 0, 0 );

        if (levelNo == 1)
        	createLevel(level1);
        else if (levelNo == 2)
        	createLevel(level2);
        else if (levelNo == 3)
        	createLevel(level3);
        
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
                    handler.addObject( new Block( i * 32 - 400, j * 32 + 72, 1, ObjectType.Block, handler) );
                }
                if (red == 0 && green == 0 && blue == 255) { // if the pixel is blue
                	player = new Player(i + 200, j + 100, handler,ObjectType.Player);
                    handler.addObject( player );
                }
                if (red == 127 && green == 127 && blue == 127) { // if the pixel is grey 
                    handler.addObject( new Block( i * 32 - 400, j * 32 + 72, 2, ObjectType.Block, handler) );
                }
                if (red == 237 && green == 28 && blue == 36) { // if the pixel is red 
                    handler.addObject( new Food( i * 32 - 400, j * 32 + 72, ObjectType.Food, 3) );
                }
                if (red == 255 && green == 127 && blue == 39) { // if the pixel is orange 
                    handler.addObject( new Sleep( i * 32 - 400, j * 32 + 72, ObjectType.Sleep, 3) );
                }
                if (red == 255 && green == 201 && blue == 14) { // if the pixel is yellow 
                    handler.addObject( new Coin( i * 32 - 400, j * 32 + 72, ObjectType.Coin, 3) );
                }
                if (red == 34 && green == 177 && blue == 76) { // if the pixel is green 
                    handler.addObject( new Block( i * 32 - 400, j * 32 + 72, 3, ObjectType.Block, handler) );
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
    	int count = 0;
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double fps = 60.0;
        double ns = 1000000000 / fps;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int min = 1, sec = 30;
       // int updates = 0;
       // int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                updateFrame();
               // updates++;
                delta--;
            }
            
            //frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                if( sec < 10 ) {
                	countDown = "Time: " + min + ":0" + (sec);
                }else {
                	countDown = "Time: " + min + ":" + (sec);
                } 
                if( min == 0 && sec == 0 ) {
                	JOptionPane.showMessageDialog(null, "The Deadline Has Passed, \nBetter Luck Next Time!", "You Fail!", JOptionPane.PLAIN_MESSAGE);
                	//exit from level here
                }
                if( sec == 0 ){
                	min--;
                	sec = 59;
                }
                if( count == 5 ) {
                	player.setFoodCount( player.getFoodCount()-1 );
                	if( player.getFoodCount() == 0 )
                		JOptionPane.showMessageDialog(null, "You haven't eaten in a long time, \nYou don't have the strength to continue!", "You Lost!", JOptionPane.PLAIN_MESSAGE);
                		//exit from level here
                	count = 0;
                }
                sec--;
                count++;
                //frames = 0;
                //updates = 0;
            }
            render();
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
        g2d.setFont(font);
        /////////////////////////////////////////////////////////////////////////////
        // Graphics of the game
        //graphics.setColor(Color.BLACK);
        //graphics.fillRect(0,0, getWidth(), getHeight());
    	graphics.drawImage(level.getImage(), 0, 0, null);    	
        g2d.translate(cam.getXPos(), cam.getYPos() ); // begin of cam
        for (int i = 0; i < clouds.getImage().getWidth(null) * 10; i += clouds.getImage().getWidth(null)) {
        	 graphics.drawImage(clouds.getImage(), i*3, 0, this);
        }
        handler.render(graphics);
        //graphics.setColor(Color.CYAN);
        //graphics.fillRoundRect((int) (-cam.getXPos())+40, 25, 75, 35, 20, 20);
        //graphics.setColor(Color.BLACK);
        //graphics.drawRoundRect((int) (-cam.getXPos())+40, 25, 75, 35, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawString( countDown, -cam.getXPos()+30, 30 ); 
        g2d.drawString( "Food:  " + player.getFoodCount(), -cam.getXPos()+30, 55 ); 
        g2d.drawString( "Sleep: " + player.getSleepCount(), -cam.getXPos()+30, 80 ); 
        g2d.drawString( "Coins: " + player.getCoinCount(), -cam.getXPos()+30, 105 ); 
        //graphics.drawImage(food.getImage(), (int) (-cam.getXPos()+30), 40, null);  
        g2d.translate(cam.getXPos(), -cam.getYPos() ); //end of cam
        /////////////////////////////////////////////////////////////////////////////

        graphics.dispose();
        bufferStrategy.show();
    }

    public static Texture getInstance() {
    	return texture;
    }
    
    public void startTheGame() {
    	new Window(1000, 510, "Catch The Deadline", new GameEngine(levelNo));
    }
}