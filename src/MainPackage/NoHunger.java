package MainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NoHunger extends GameObject{

	private Handler handler;
	private boolean invinc;

	
	public NoHunger(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		this.handler = handler;
		
		System.out.println("Active threads: " + java.lang.Thread.activeCount());
	}

	public void tick() {
		Collision();
	}
	
	
	
	public void Collision()
	{
		for(int i = 0; i < handler.objectList.size(); i++)
		{
			GameObject tempObject = handler.objectList.get(i);
			
			if(getBounds().intersects(tempObject.getBounds())) {
				if(tempObject.id == ID.Player)
				{
					SnakePlayer.invincible = true;
					
					TimerTask noInvinc = new TimerTask() {

						public void run() {
							SnakePlayer.invincible = false;
						}
						
					};
					
					Timer time = new Timer();
					
					time.schedule(noInvinc, 5000);
					
					spawn();
					
					handler.removeObject(this); 
				}
			}
		}
		
	}
	
	

	public void render(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect((int)x, (int)y, 30, 30);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 30, 30);
	}

	

}
