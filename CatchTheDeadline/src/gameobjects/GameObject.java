package gameobjects;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    // Properties
    protected float posX, posY;
    protected float velocityX = 0, velocityY = 0;
    protected ObjectType type;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected int facing = 1;

    public GameObject(float x, float y, ObjectType type) {
        posX = x;
        posY = y;
        this.type = type;
    }

    public abstract void collisionDetector(LinkedList<GameObject> objects);
    public abstract void render (Graphics graphics);
    public abstract Rectangle objectBounds();

    // Getters
    public float getPosX() {
        return posX;
    }
    public float getPosY() {
        return posY;
    }
    public float getVelocityX() {
        return velocityX;
    }
    public float getVelocityY() {
        return velocityX;
    }
    public ObjectType getType() {
        return type;
    }

    // Setters
    public void setPosX(float x) {
        posX = x;
    }
    public void setPosY (float y) {
        posY = y;
    }
    public void setVelocityX (float velX) {
        velocityX = velX;
    }
    public void setVelocityY (float velY) {
        velocityY = velY;
    }

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public int getFacing() {
        return facing;
    }
}
