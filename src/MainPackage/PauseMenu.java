package MainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PauseMenu extends MouseAdapter{
	
	private SnakeGame game;
	private Handler handler;
	private Random r = new Random();
	
	public PauseMenu(SnakeGame game, Handler handler)
	{
		this.game = game;
		this.handler = handler;
	}
	
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		
		if(mouseOver(mx, my, SnakeGame.WIDTH/2 - 20, SnakeGame.HEIGHT/2 - 40, 100, 64) == true) {
			SnakeGame.gameState = STATE.play;
			handler.addObject(new Bird(r.nextInt(SnakeGame.WIDTH - 55) , r.nextInt(SnakeGame.HEIGHT - 55), ID.Bird, handler));
			handler.addObject(new SnakePlayer((SnakeGame.WIDTH/2-32), (SnakeGame.HEIGHT/2-32), ID.Player, handler));
			
			for(int i = 0; i < 3; i++)
			{
				handler.addObject(new Apple(r.nextInt(SnakeGame.WIDTH - 55) , r.nextInt(SnakeGame.HEIGHT - 55), ID.Apple, handler));
			}

		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width ) {
			if(my  > y && my < y + height) {
					return true;
				}else return false;
		}else return false;
		
		
		
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		Font f = new Font("Arial", Font.BOLD, 30);
		
		g.setColor(Color.RED);
		g.drawRect(SnakeGame.WIDTH / 2 - 20, SnakeGame.WIDTH / 2 - 40, 40, 40);
		
		g.setFont(f);
		g.setColor(Color.ORANGE);
		g.drawString("Play Game", SnakeGame.WIDTH / 2 - 20, SnakeGame.WIDTH / 2 - 40);
	}

}
