package MainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class BadApple extends GameObject{

	private Handler handler;
	private Random r;
	
	public BadApple(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.r = new Random();
		this.handler = handler;
		
	}

	public void tick() {
		collision();
	}
	
	public void collision()
	{
		for(int i = 0; i < handler.objectList.size(); i++)
		{
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.id == ID.Player)
			{
				if(getBounds().intersects(tempObject.getBounds())) {
					SnakePlayer.incHunger(-100);
				
					float s = r.nextFloat();
				
					if(s >= 0.25)
					{
						handler.addObject(new Apple(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.Apple, handler));
					}else
					{
						handler.addObject(new BadApple(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.BadApple, handler));
					}
				
			    	handler.objectList.remove(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.GREEN);
		g2d.drawOval((int)x, (int)y, 20, 20);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int)y, 20, 20);
	}
	
	

}
