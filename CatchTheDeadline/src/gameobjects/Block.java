package gameobjects;

import gameManager.GameObject;
import gameManager.ObjectType;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {

    public Block(float x, float y, ObjectType type) {
        super(x, y, type);
    }

    @Override  
    public void collisionDetector(LinkedList<GameObject> objects) {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.drawRect((int) posX, (int) posY, 38, 38);
    }

    @Override
    public Rectangle objectBounds() {
        return new Rectangle((int) posY, (int) posY, 38, 38);
    }
}