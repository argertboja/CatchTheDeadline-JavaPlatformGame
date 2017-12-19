/**
 * 
 */
package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * @author Hareem Larik
 *
 */
public class Enemy extends GameObject {

	/**
	 * 
	 */
	public Enemy(float x, float y, ObjectType type) {
		super(x, y, type);
	}

	@Override
	public void collisionDetector(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle objectBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
