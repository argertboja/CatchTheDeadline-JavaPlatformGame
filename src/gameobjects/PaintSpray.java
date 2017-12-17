package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import gameManager.Animation;
import gameManager.Texture;
import window.GameEngine;

public class PaintSpray extends GameObject {

	
	private Animation animation;
	private Texture texture = GameEngine.getInstance();
	public boolean touched = false;
	
	public PaintSpray( float x, float y, ObjectType type, int velX ) {
  		super(x, y, type);
  		this.velocityX = velX;
		animation = new Animation( 1, texture.paintSpraySpinning );
  	}

	@Override
	public void collisionDetector(LinkedList<GameObject> objects) {
		animation.runAnimation();
		posX += velocityX;
	}

	@Override
	public void render(Graphics graphics) {
		animation.drawAnimation(graphics, (int) posX, (int) posY, 30, 30);
	}

	@Override
	public Rectangle objectBounds() {
		return new Rectangle((int) posX, (int) posY, 30, 30);		
	}
}

