package MainPackage;

import java.awt.*;

public class PlayerTrail extends GameObject{

	private float alpha = 1;
	private Handler handler;
	
	private int width, height;
	private Color color;
	private float life;
	
	
	public PlayerTrail(int x, int y, ID id, Handler handler, int width, int height, float life, Color color) {
		super(x, y, id, handler);
		
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.color = color;
		this.life = life;
	}

	
	public void tick() {
		if(alpha > life) {
			alpha -= (life - 0.001f);
		}else handler.removeObject(this);
		
	}

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		
		g2d.setComposite(makeTransparent(1));
		
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
		
	} 
	
	public Rectangle getBounds() {
		return null;
	}

}
