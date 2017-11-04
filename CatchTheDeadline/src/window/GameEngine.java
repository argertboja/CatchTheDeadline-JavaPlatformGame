package window;

import gameManager.GameObject;
import gameManager.Handler;
import gameManager.ObjectType;
import gameobjects.Block;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameEngine extends Canvas implements Runnable {

    private Thread thread;
    private boolean isRunning = false;
    private Handler handler;

    public static int WIDTH, HEIGHT;

    public void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        handler = new Handler();
        handler.level();
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

        /////////////////////////////////////////////////////////////////////////////
        // Graphichs of the game
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, getWidth(), getHeight());
        handler.render(graphics);
        /////////////////////////////////////////////////////////////////////////////

        graphics.dispose();
        bufferStrategy.show();
    }

    public void startTheGame() {
        new Window(1000, 500, "Catch The Deadline", new GameEngine());
    }


}
