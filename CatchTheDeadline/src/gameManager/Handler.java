package gameManager;

import gameobjects.Block;
import window.GameEngine;
import gameobjects.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objectLinkedList = new LinkedList<GameObject>();

    private GameObject temp;

    public void updateFrame() {
        for(int i = 0; i < objectLinkedList.size(); i++) {
            temp = objectLinkedList.get(i);
            temp.collisionDetector(objectLinkedList);
        }
    }

    public void render(Graphics graphics) {
        for (int i = 0; i < objectLinkedList.size(); i++) {
            temp = objectLinkedList.get(i);
            temp.render(graphics);
        }
    }

    public void addObject (GameObject object) {
        objectLinkedList.add(object);
    }

    public void removeObject (GameObject object) {
        objectLinkedList.remove(object);
    }

    public void level() {
    	
        for (int i = 0; i < GameEngine.WIDTH*10; i+= 32) {
        	addObject(new Block( i, GameEngine.HEIGHT - 32, 1, ObjectType.Block, this));
        }
        for (int i = 0; i < GameEngine.WIDTH + 400; i+= 200) {
        	addObject(new Block(i, GameEngine.HEIGHT - 96, 1, ObjectType.Block, this));
        }
        for (int i = 0; i < GameEngine.HEIGHT; i+= 32) { // right wall
        	 addObject(new Block(GameEngine.WIDTH*3, i, 1, ObjectType.Block, this));
        }
        for (int i = 0; i < GameEngine.HEIGHT; i+= 32) { // left wall
        	addObject(new Block(0, i, 1, ObjectType.Block, this));
        }
    }
}