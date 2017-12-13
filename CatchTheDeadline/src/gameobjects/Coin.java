package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Coin extends PowerUps {
	
ImageIcon coin = new ImageIcon(getClass().getResource("/images/coin.png"));
	
    public Coin( float x, float y, ObjectType type, int value ){
          super( x, y, type, value );
          this.value = value;
    }
      
    @Override
    public void collisionDetector(LinkedList<GameObject> objects) {}
     
    @Override
    public void render(Graphics graphics) {
    	graphics.drawImage( coin.getImage(), (int) posX, (int) posY, null );
    }
      
    @Override
    public Rectangle objectBounds() {
    	return new Rectangle((int) posX, (int) posY, 32, 32);
    }
}