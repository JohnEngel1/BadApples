package MainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class HUD {
	
	public static int SCORE = 0;
	public static int LEVEL = 0;
	
	public static int LASTSCORE = 0;
	public static int LASTLEVEL = 0;
	
	private Random r;
	private Handler handler;
	
	
	public void tick()
	{
		
		if(SCORE % 1000 == 0 && SCORE != 0)
		{

			LEVEL++;

			for(int i = 0; i < handler.objectList.size(); i++)
			{
				GameObject tempObject = handler.objectList.get(i);
				
				if(tempObject.getID() == ID.Bird)
				{
					handler.removeObject(tempObject);
				}
			}
			
			for(int i = 0; i < LEVEL; i++)
			{
				handler.addObject(new Bird(r.nextInt(SnakeGame.WIDTH - 20), r.nextInt(SnakeGame.HEIGHT - 40), ID.Bird, handler));
			}
			
			
		}
	}
	
	public void render(Graphics g)
	{
		Font f = new Font("Arial", Font.PLAIN, 14);
		
		g.setFont(f);
		
		g.setColor(Color.WHITE);
		g.drawString("Score " + SCORE, 20, 20);
		
		g.setColor(Color.RED);
		g.drawString("Hunger: " + SnakePlayer.getHunger() / 10, 20, 40);
	}
	
	public static void incScore(int amount)
	{
		SCORE += amount;
	}

}
