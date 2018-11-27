package MainPackage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class SpeedUp extends GameObject implements ImageObserver{

	private Handler handler;
	private Image temp;
	
	public SpeedUp(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		this.handler = handler;
		
		Image img = null;
        temp = null;
		
		try {
			img = ImageIO.read(new File("/Users/jackengel/Desktop/hottyjohnny.png"));
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		temp = img.getScaledInstance(30, 30, Image.SCALE_FAST);
	}

	public void tick() {
		Collision();
	}

	public void render(Graphics g) {
		g.drawImage(temp, (int)x, (int)y, this);
	}

	public void Collision(){
		
		for(int i = 0; i < handler.objectList.size(); i++) {
			
			GameObject tempObject = handler.objectList.get(i);
			
			if(getBounds().intersects(tempObject.getBounds())) {
				if(tempObject.getID() == ID.Player)
				{
					tempObject.speeds[0] -= 3;
					tempObject.speeds[1] += 3;
					tempObject.speeds[2] += 3;
					tempObject.speeds[3] -= 3;
					
					TimerTask noInvinc = new TimerTask() {

						public void run() {
							tempObject.speeds[0] += 3;
							tempObject.speeds[1] -= 3;
							tempObject.speeds[2] -= 3;
							tempObject.speeds[3] += 3;
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
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 30, 30);
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

	public void spawn() {
		Random r = new Random();
		
		float s = r.nextFloat();
		
		if(s >= 0.40)
		{
			handler.addObject(new Apple(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.Apple, handler));
		}else if(s >= 0.25 && s < 0.40)
		{
			handler.addObject(new BadApple(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.BadApple, handler));
		}else if(s >= 0.15 && s < 0.25)
		{
			handler.addObject(new NoHunger(r.nextInt(SnakeGame.WIDTH - 30), r.nextInt(SnakeGame.HEIGHT - 40), ID.NoHunger, handler));
		}else
		{
			handler.addObject(new SpeedUp(r.nextInt(SnakeGame.WIDTH - 20), r.nextInt(SnakeGame.HEIGHT - 40), ID.SpeedUp, handler));
		}		
	}
	
	

}
