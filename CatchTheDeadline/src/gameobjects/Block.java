package gameobjects;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Block extends GameObject {

	ImageIcon ground = new ImageIcon(getClass().getResource("/images/on.png"));
	
    public Block(float x, float y, ObjectType type) {
        super(x, y, type);
    }

    @Override
    public void collisionDetector(LinkedList<GameObject> objects) {

    }

    @Override
    public void render(Graphics graphics) {
    	//Color myColour = new Color(255, 0, 0, 0);
        //graphics.setColor(myColour);
        graphics.setColor(Color.BLUE);
        graphics.drawRect((int) posX, (int) posY, 38, 38);
        //graphics.drawImage(ground.getImage(), 0, 0, null);
    }

    @Override
    public Rectangle objectBounds() {
        return new Rectangle((int) posX, (int) posY, 38, 38);
    }
}