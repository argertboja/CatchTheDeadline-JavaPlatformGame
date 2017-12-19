package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class PowerUps extends GameObject {
		
	int value;
	
	public PowerUps( float x, float y, ObjectType type, int value ) {
		super( x, y, type );
		this.value = value;
	}
	  
	@Override
	public void collisionDetector(LinkedList<GameObject> objects) {}
	  
	@Override
	public void render(Graphics graphics) {}
	  
	@Override
	public Rectangle objectBounds() {
		 return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}