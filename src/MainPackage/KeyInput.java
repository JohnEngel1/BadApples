package MainPackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private boolean[] keyDown = new boolean[4];
	private Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler= handler;

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
	
		
		for(int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_W) { tempObject.setVelY(tempObject.speeds[0]); keyDown[0] = true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(tempObject.speeds[1]); keyDown[1] = true; }
				if(key == KeyEvent.VK_D) { tempObject.setVelX(tempObject.speeds[2]); keyDown[2] = true; }
				if(key == KeyEvent.VK_A) { tempObject.setVelX(tempObject.speeds[3]); keyDown[3] = true; }
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1] = false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) keyDown[2] = false;//tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) keyDown[3] = false; //tempObject.setVelX(0);
				
				//vertical move
				if(!keyDown[0] && !keyDown[1] ) tempObject.setVelY(0);
				//horiz move
				if(!keyDown[2] && !keyDown[3] ) tempObject.setVelX(0);

			}
		}
	}

}
