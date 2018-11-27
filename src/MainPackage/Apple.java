package MainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Apple extends GameObject implements ImageObserver{
	
	private Handler handler;
	private Random r;
	private Image temp;

	public Apple(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		this.handler = handler;
		this.r = new Random(System.nanoTime());
		
		
		Image img = null;
		
		try{
			img = ImageIO.read(new File("/Applications/apple.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		temp = img.getScaledInstance(20, 20, Image.SCALE_FAST);
		
	}

	public void tick() {
		collision();
	}
	
	public void collision()
	{
		for(int i = 0; i < handler.objectList.size(); i++)
		{
			GameObject tempObject = handler.objectList.get(i);
//			Player Contact
			if(tempObject.id == ID.Player)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					HUD.incScore(100);
					SnakePlayer.incHunger(100);
					
					spawn();
					
					handler.removeObject(this);
				}
				
//				Bird Contact
			}else if(tempObject.id == ID.Bird)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					
					float s = r.nextFloat();
					
					if(s >= 0.5)
					{
						handler.addObject(new Apple(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.Apple, handler));
					}else
					{
						handler.addObject(new BadApple(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.BadApple, handler));
					}
					
					handler.removeObject(this);
				}
			}
		}
	}

	
	public void render(Graphics g) {	
		g.drawImage(temp, (int)x, (int)y, this);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 20, 20);
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

}
