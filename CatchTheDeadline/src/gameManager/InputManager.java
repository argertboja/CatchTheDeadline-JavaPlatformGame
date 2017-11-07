package gameManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import gameobjects.*;

public class InputManager extends KeyAdapter {
	
	Handler handler;
	
	public InputManager( Handler handler ) {
		this.handler = handler;
	}
	
	public void keyPressed( KeyEvent event) {
		int key = event.getKeyCode();
		
		for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
			GameObject temp = handler.objectLinkedList.get(i);
			
			if( temp.getType() == ObjectType.Player ){
				if( (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) && !temp.isJumping() ) {
					temp.setVelocityY(-7);
					temp.setJumping(true);
				}
				if( key == KeyEvent.VK_DOWN )
					temp.setVelocityY(5);
				if( key == KeyEvent.VK_RIGHT )
					temp.setVelocityX(5);
				if( key == KeyEvent.VK_LEFT )
					temp.setVelocityX(-5);
			}
		}
		if( key == KeyEvent.VK_ESCAPE ) {
			System.exit(1);
		}
	}
	
	public void keyReleased( KeyEvent event) {
		int key = event.getKeyCode();
		
		for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
			GameObject temp = handler.objectLinkedList.get(i);
			
			if( temp.getType() == ObjectType.Player ){
				//if( key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP )
					//temp.setVelocityY(0);
				if( key == KeyEvent.VK_DOWN )
					temp.setVelocityY(0);
				if( key == KeyEvent.VK_RIGHT )
					temp.setVelocityX(0);
				if( key == KeyEvent.VK_LEFT )
					temp.setVelocityX(0);
			}
		}
		
		if( key == KeyEvent.VK_ESCAPE ) {
			System.exit(1);
		}
	}
	
	
}
