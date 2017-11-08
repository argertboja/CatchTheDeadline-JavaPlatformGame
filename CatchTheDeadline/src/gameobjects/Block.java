package gameobjects;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import gameManager.Handler;
import gameManager.Texture;
import window.GameEngine;

public class Block extends GameObject {

	Texture texture = GameEngine.getInstance();
	ImageIcon books = new ImageIcon(getClass().getResource("/images/books.png"));
	private int blockType;
	private Handler handler;
	
    public Block(float x, float y, int blockType, ObjectType type, Handler handler) {
        super(x, y, type);
        this.blockType = blockType;
        this.handler = handler;
    }

    public void collisions( LinkedList<GameObject> objects ) {
    }


    @Override
    public void collisionDetector(LinkedList<GameObject> objects) {
        collisions(objects);
    }

    @Override
    public void render(Graphics graphics) {
    	if( blockType == 2 ) {
    		graphics.drawImage( books.getImage(), (int) posX, (int) posY, null );
        }
    }

    @Override
    public Rectangle objectBounds() {
        return new Rectangle((int) posX, (int) posY, 32, 32);
    }
}