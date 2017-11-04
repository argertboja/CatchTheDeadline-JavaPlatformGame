package gameManager;

import gameobjects.Block;
import window.GameEngine;

import java.awt.*;
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
        for (int i = 0; i < GameEngine.WIDTH + 38; i+= 38) {
            addObject(new Block(i, GameEngine.HEIGHT - 38, ObjectType.Block));
        }
    }
}
