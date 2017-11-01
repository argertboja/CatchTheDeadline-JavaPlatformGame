package window;

import java.awt.*;

public class Game extends Canvas implements Runnable {

    private Thread thread;
    private boolean isRunning = false;

    public void createWindow() {
        new Window(1000, 500, "Catch The Deadline", new Game());
    }

    public void run() {

    }

    public synchronized void startGame() {
        if(isRunning) {
            return;
        }

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }


}
