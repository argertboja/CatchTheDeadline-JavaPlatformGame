/**
 * 
 */
package gameobjects;

import gameManager.Animation;
import gameManager.BufferedImageLoader;
import gameManager.Texture;
import window.GameEngine;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Pen extends GameObject {

	private Animation animation;
	private Texture texture = GameEngine.getInstance();

	public Pen(float x, float y, ObjectType type, int velX) {
		super(x, y, type);
		this.velocityX = velX;
		animation = new Animation(1, texture.penSpinning);
	}


	@Override
	public void collisionDetector(LinkedList<GameObject> objects) {
		animation.runAnimation();
		posX += velocityX;
	}

	@Override
	public void render(Graphics graphics) {
		animation.drawAnimation(graphics, (int) posX, (int) posY, 35, 35);
	}

	@Override
	public Rectangle objectBounds() {
		return new Rectangle((int) posX, (int) posY, 35, 35);
	}
}
