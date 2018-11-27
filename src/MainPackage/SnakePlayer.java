package MainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class SnakePlayer extends GameObject implements ImageObserver {
	
	private Handler handler;
	
	public static boolean invincible = false;
	public static double hunger = 1000;
	private Image temp;
	private Color bgColor;

	public SnakePlayer(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		this.handler = new Handler();
		
		Image img = null;
		
		Color bgColor = new Color(255,255,255);
		
		try {
			img = ImageIO.read(new File("/Users/jackengel/Desktop/hottyjohnny.png"));
		} catch (IOException e) {
			System.out.println("Player image exception");
			e.printStackTrace();
		}
		
		temp = img.getScaledInstance(50, 50, Image.SCALE_FAST);
		
	}
	
	public static void incHunger(int amount)
	{
		hunger += amount;
	}
	
	public static double getHunger()
	{
		return hunger;
	}

	public void tick() {
		this.x += velX;
		this.y += velY;
		
		x = SnakeGame.clamp((int) x, 0, (SnakeGame.WIDTH - 51));
		y = SnakeGame.clamp((int) y, 0, (SnakeGame.HEIGHT - 72));
		
		hunger = SnakeGame.clamp((int) hunger, 0, 1000);
		
		if(hunger <= 0)
		{
			removed = true;
		}
		
		Collision();
		
		if(!invincible)
			hunger--;
		
		if(!(velX == 0 && velY == 0)) {
			//	handler.addObject(new PlayerTrail((int)x, (int)y, null, handler, 50, 50, 1f, Color.WHITE));
		}
	}
	
	

	public void render(Graphics g) {
		g.drawImage(temp, (int)x, (int)y, bgColor, this);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 50, 50);
	}
	
	public void Collision()
	{
		
		
		
	}
	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return true;
	}

}
