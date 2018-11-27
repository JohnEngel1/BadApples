package MainPackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public abstract class GameObject {
	
	protected float x, y;
	protected float velX, velY;
	protected float[] speeds = {-5, 5, 5, -5};
	protected ID id;
	protected boolean removed;
	protected Handler handler;
	
	
	public GameObject(int x, int y, ID id, Handler handler)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.removed = false;
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public void spawn() {
		Random r = new Random();
		
		float s = r.nextFloat();
		
		if(s >= 0.40)
		{
			handler.addObject(new Apple(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.Apple, handler));
		}else if(s >= 0.15 && s < 0.40)
		{
			handler.addObject(new BadApple(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.BadApple, handler));
		}else if(s >= 0.075 && s < 0.15)
		{
			handler.addObject(new NoHunger(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.NoHunger, handler));
		}else
		{
			handler.addObject(new SpeedUp(r.nextInt(SnakeGame.WIDTH - 20), r.nextInt(SnakeGame.HEIGHT - 40), ID.SpeedUp, handler));
		}
	}
	
	
	//Setters for x, y
	public void setX(float xSet)
	{
		this.x = xSet;
	}
	
	public void setY(float ySet)
	{
		this.y = ySet;
	}
	
	//Getters for x, y
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	//Getters, setters for velX and velY
	public void setVelX(float velX)
	{
		this.velX = velX;
	}
	
	public void setVelY(float velY)
	{
		this.velY = velY;
	}
	
	public float getVelX()
	{
		return this.velX;
	}
	public float getVelY()
	{
		return this.velY;
	}
	
	public ID getID()
	{
		return this.id;
	}
}
