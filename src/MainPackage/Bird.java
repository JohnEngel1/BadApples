package MainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Bird extends GameObject implements ImageObserver{

	private Handler handler;
	private Image temp;
	
	public Bird(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		this.handler = handler;
		
		Image img = null;
		
		try{
			img = ImageIO.read(new File("/Applications/squarePlayer.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	    temp = img.getScaledInstance(20, 20, Image.SCALE_FAST);
		
	}

	public void tick() {
				
		
		for(int  i = 0; i < handler.objectList.size(); i++)
		{
			GameObject tempObject = handler.objectList.get(i);
//			Go towards closest apple on screen
			if(tempObject.id == ID.Apple)
			{
				x += velX;
				y += velY;
				
				float diffX = x - tempObject.getX() - 8;
				float diffY = y - tempObject.getY() - 8;
				float distance = (float) Math.sqrt((x-tempObject.getX()) * (x-tempObject.getX()) + (y-tempObject.getY()) * (y-tempObject.getY()));
				
				
				velX = 3 * (float) (-1 / distance * diffX);
				velY = 3 * (float) (-1 / distance * diffY);
			}
		}
				
	}
	


	public void render(Graphics g) {
		
		g.drawImage(temp, (int)x, (int)y, this);
	}

	public Rectangle getBounds() {
		return new Rectangle ((int)x, (int)y, 20, 20);
	}

	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		if(velX == 0 && velY == 0) {
			return false;
		}
		return true;
	}
	
	

}
