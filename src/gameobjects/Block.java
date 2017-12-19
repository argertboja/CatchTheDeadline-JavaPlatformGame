package gameobjects;

import java.awt.*;

import java.util.LinkedList;

import javax.swing.ImageIcon;

import gameManager.Texture;
import window.GameEngine;
import gameManager.Handler;
public class Block extends GameObject {

	Texture texture = GameEngine.getInstance();
	ImageIcon books = new ImageIcon(getClass().getResource("/images/books.png"));
	ImageIcon box = new ImageIcon(getClass().getResource("/images/box.png"));
	ImageIcon note = new ImageIcon(getClass().getResource("/images/note.png"));
	private int blockType;
	private Handler handler;
	
    public Block(float x, float y, int blockType, ObjectType type, Handler handler) {
        super(x, y, type);
        this.blockType = blockType;
        this.handler = handler;
    }

    public void collisions( LinkedList<GameObject> objects ) {}
    
    @Override
    public void collisionDetector(LinkedList<GameObject> objects) {
    	collisions(objects);
    }

    @Override
    public void render(Graphics graphics) {
    	if( blockType == 2 ) {
    		graphics.drawImage( books.getImage(), (int) posX, (int) posY, null );
        }
    	if( blockType == 3 ) {
    		graphics.drawImage( box.getImage(), (int) posX, (int) posY, null );
    		graphics.drawImage( note.getImage(), (int) posX+35, (int) posY-150, null );
        }
    }

    @Override
    public Rectangle objectBounds() {
        return new Rectangle((int) posX, (int) posY, 32, 32);
    }
    
    public int getBlockType() {
    	return blockType;
    }
}