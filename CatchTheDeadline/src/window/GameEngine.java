package window;

import accessManager.LogIn;
import database.DBInterface;
import gameManager.*;
import gameobjects.*;
import userinterface.MainMenu;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameEngine extends Canvas implements Runnable {

    private Thread thread, timer;
    private Player player;
    private boolean isRunning = false, playing = true, endgame = false;
    private Handler handler;
    private Camera cam;
    static Texture texture;
    public static int WIDTH, HEIGHT;
    private String countDown = "Time Left: 1:30";
    private Font font1 = new Font("Cooper Black", Font.BOLD, 24);
    private Font font2 = new Font("Cooper Black", Font.BOLD, 28);
    private static int lives = 3, oldLives = 3;
    private int scores = 0, min = 1, sec = 30;
    private int enemyId = 0;
    private static Window window;
    private static String username;
    private DBInterface db;
    private static int totalCoins = 0;
    private boolean eraserAct = false, psAct = false;
    
    private BufferedImage level1 = null, level2 = null, level3 = null;

    private ImageIcon level = new ImageIcon(getClass().getResource("/images/level.png"));
    private ImageIcon clouds = new ImageIcon(getClass().getResource("/images/clouds.png"));
    private ImageIcon food = new ImageIcon(getClass().getResource("/images/food.png"));
    private ImageIcon life = new ImageIcon(getClass().getResource("/images/life.gif"));
    
    private int levelNo = 1;
    //SoundManager sound = new SoundManager();
    
    public GameEngine(int levelNo, String username) {
        this.levelNo = levelNo;
        this.username = username;
      //  sound.start();
        db = new DBInterface();
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
        
        this.addKeyListener( new InputManager(handler, this) );    
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
                	player = new Player(i + 100, j + 100, handler,ObjectType.Player);
                    handler.addObject( player );
                }
                if (red == 127 && green == 127 && blue == 127) { // if the pixel is grey 
                    handler.addObject( new Block( i * 32 - 400, j * 32 + 72, 2, ObjectType.Block, handler) );
                }
                if (red == 237 && green == 28 && blue == 36) { // if the pixel is red 
                    handler.addObject( new Food( i * 32 - 400, j * 32 + 72, ObjectType.Food ) );
                }
                if (red == 255 && green == 127 && blue == 39) { // if the pixel is orange 
                    handler.addObject( new Sleep( i * 32 - 400, j * 32 + 72, ObjectType.Sleep ) );
                }
                if (red == 255 && green == 201 && blue == 14) { // if the pixel is yellow 
                    handler.addObject( new Coin( i * 32 - 400, j * 32 + 72, ObjectType.Coin ) );
                }
                if (red == 34 && green == 177 && blue == 76) { // if the pixel is dark green 
                    handler.addObject( new Block( i * 32 - 400, j * 32 + 72, 3, ObjectType.Block, handler) );
                }
                if (red == 0 && green == 255 && blue == 0) { // if the pixel is green
                    handler.addObject( new Exam( i * 32 - 400, j + 100, handler, ObjectType.Exam, enemyId, this ) );
                    enemyId++;
                }
                if (red == 255 && green == 242 && blue == 0) { // if the pixel is bright yellow
                    handler.addObject( new BlankSheet( i * 32 - 400, j * 32, handler, ObjectType.BlankSheet, enemyId, this ) );
                    enemyId++;
                }
                if (red == 163 && green == 73 && blue == 164) { // if the pixel is purple
                    handler.addObject( new Homework( i * 32 - 400, j * 32, handler, ObjectType.Homework, enemyId, this ) );
                    enemyId++;
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
    	int count = 0, count2 = 0;
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double fps = 60.0;
        double ns = 1000000000 / fps;
        double delta = 0;
        long timer = System.currentTimeMillis();
       // int updates = 0;
       // int frames = 0;
        while(isRunning && !endgame){
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
                	countDown = "Time Left: " + min + ":0" + (sec);
                }else {
                	countDown = "Time Left: " + min + ":" + (sec);
                } 
                if( min == 0 && sec == 0 ) {
                	JOptionPane.showMessageDialog(null, "The Deadline Has Passed, \nBetter Luck Next Time!", "You Fail!", JOptionPane.PLAIN_MESSAGE);
                    lives--;
                }
                if( sec == 0 ){
                	min--;
                	sec = 59;
                }
                if( count == 5 ) {
                	player.setFoodCount( player.getFoodCount()-1 );
                	//sound.stop();
                	count = 0;
                }
                if( count2 == 7  ) {
                	player.setSleepCount( player.getSleepCount()-1 );
                	count2 = 0;
                }
                if (playing) {
                    sec--;
                }
                count++;
                count2++;
                //frames = 0;
                //updates = 0;
            }
            try {
                render();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if( player.getFoodCount() < 0 ) {
                JOptionPane.showMessageDialog(null, "You haven't eaten in a long time, \nYou don't have the strength to continue!", "You Lost!", JOptionPane.PLAIN_MESSAGE);
                lives--;
            }
            if( player.getSleepCount() < 0 ) {
                JOptionPane.showMessageDialog(null, "You haven't rested in a long time, \nYou don't have the strength to continue!", "You Lost!", JOptionPane.PLAIN_MESSAGE);
                lives--;
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
        for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
            if (handler.objectLinkedList.get(i).getType() == ObjectType.Exam) {
                Exam exam = (Exam) handler.objectLinkedList.get(i);
                if (exam.getId() ==  player.getEnemyId()) {
                    handler.objectLinkedList.remove(exam);
                }
            }
        }
        for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
            if (handler.objectLinkedList.get(i).getType() == ObjectType.BlankSheet) {
                BlankSheet blankSheet = (BlankSheet) handler.objectLinkedList.get(i);
                if (blankSheet.getId() ==  player.getEnemyId()) {
                    handler.objectLinkedList.remove(blankSheet);
                }
            }
        }
        for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
            if (handler.objectLinkedList.get(i).getType() == ObjectType.Homework) {
            	Homework homework = (Homework) handler.objectLinkedList.get(i);
                if (homework.getId() ==  player.getEnemyId()) {
                    handler.objectLinkedList.remove(homework);
                }
            }
        }
        totalCoins = player.getCoinCount();
    }

    // Render Images as fast as a computer can
    private void render() throws InterruptedException {
        if (lives != oldLives) {
            int prevCoins = player.getCoinCount();
            for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
                handler.objectLinkedList.remove(i);
            }
            init();
            player.setCoinCount(prevCoins);
            oldLives--;
        }
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            // 3 is the most efficient number of buffers. A value larger than this
            // would reduce the performance of the game
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setFont(font1);
        /////////////////////////////////////////////////////////////////////////////
        // Graphics of the game
    	graphics.drawImage(level.getImage(), 0, 0, null);
        if (lives >= 3) {
            graphics.drawImage(life.getImage(), 200, 5, null);
        }
        if (lives >= 2) {
            graphics.drawImage(life.getImage(), 150, 5, null);
        }
        if (lives >= 1) {
            graphics.drawImage(life.getImage(), 100, 5, null);
        }
        if (lives == 0 || player.isLevelComplete() ) {
        	if( lives == 0 ) {
        		scores = player.getCoinCount() + 5 * player.getFoodCount() + 5 * player.getSleepCount() + min * 60 + sec;
        		JOptionPane.showMessageDialog(null, "You don't have anymore lives \nYour score: " + scores, "GAME OVER", JOptionPane.PLAIN_MESSAGE);
        	}
        	if( player.isLevelComplete() ) {
        		scores = player.getCoinCount() + 5 * player.getFoodCount() + 5 * player.getSleepCount() + min * 60 + sec;
        		JOptionPane.showMessageDialog(null, "You have finished this level! \nKeep up the good work \nYour score: " + scores, "Congratulations", JOptionPane.PLAIN_MESSAGE);
        	}
        	//window = new Window(1000, 510, "Catch The Deadline", new GameEngine(levelNo));
            // Save scores
            //scores = player.getCoinCount() + 5 * player.getFoodCount() + 5 * player.getSleepCount() + min * 60 + sec;
            player.setCoinCount(0);
            player.setFoodCount(3);
            player.setSleepCount(3);
            min = 1;
            sec = 30;
            lives = 3;
            oldLives = 3;
            //init();
            try {
                db.saveHighScores(LogIn.usernameValue, scores, player.getCoinCount());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            window.quitGame();
            endgame = true;
            thread.join();
        }
        g2d.translate(cam.getXPos(), cam.getYPos() ); // begin of cam
        for (int i = 0; i < clouds.getImage().getWidth(null) * 10; i += clouds.getImage().getWidth(null)) {
        	 graphics.drawImage(clouds.getImage(), i*3, 0, this);
        }
        handler.render(graphics);
        g2d.setColor(new Color(192, 3, 3));
        g2d.drawString( "Lives: ", -cam.getXPos()+18, 28 );
        g2d.setColor(new Color(53, 159, 196));
        g2d.drawString( countDown, -cam.getXPos()+80, 500 );
        g2d.setFont(font2);
        g2d.setColor(new Color(247, 223, 176));
        g2d.drawString( "" + player.getFoodCount(), -cam.getXPos() + 560, 500 );
        g2d.setColor(new Color(250, 204, 33));
        g2d.drawString( "" + player.getSleepCount(), -cam.getXPos() + 720, 500 );
        g2d.drawString( "" + player.getCoinCount(), -cam.getXPos() + 390, 500 );
        g2d.translate(cam.getXPos(), -cam.getYPos() ); //end of cam
        /////////////////////////////////////////////////////////////////////////////
        graphics.dispose();
        bufferStrategy.show();
    }

    public int getTotalCoins() {		
    	totalCoins = player.getCoinCount();		
    	return totalCoins;		
    }		

    public void setTotalCoins( int totalCoins ) {	
    	player.setCoinCount(totalCoins);		
    	this.totalCoins = totalCoins;		
    }	
    
    public boolean isEraserAct() {		
    	return eraserAct;		
    }		
    
    public void setEraserAct(boolean eraserAct) {		
    	this.eraserAct = eraserAct;		
    	System.out.println( isEraserAct() + " in SetEraser");		
    }		

    public boolean isPsAct() {		
    	return psAct;		
    }		

    public void setPsAct(boolean psAct) {		
    	this.psAct = psAct;		
    }	
    
    public static Texture getInstance() {
    	return texture;
    }
    
    public void startTheGame() {
    	window = new Window(1000, 510, "Catch The Deadline", new GameEngine(levelNo, username));
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
    
    public void setLives( int lives )
    {
    	this.lives = lives;
    }
    
    public int getLives()
    {
    	return lives;
    }
}