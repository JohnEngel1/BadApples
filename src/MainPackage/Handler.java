package MainPackage;
import java.util.LinkedList;
import java.awt.Graphics;


public class Handler {
	
	LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	public void tick()
	{
		
		for(int i = 0; i < objectList.size(); i++)
		{
			GameObject tempObject = objectList.get(i);
			
			if(tempObject.removed == true) {
				removeObject(tempObject);
				if(tempObject.id == ID.Player)
				{
					HUD.LASTSCORE = HUD.SCORE;
					HUD.LASTLEVEL = HUD.LEVEL;
					
					SnakeGame.gameState = STATE.deathscreen;

					
					tempObject.removed = false;
					SnakePlayer.hunger = 1000;
					HUD.SCORE = 0;
					HUD.LEVEL = 1;
					
					new SnakeGame();
				}
			}
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < objectList.size(); i++)
		{
			GameObject tempObject = objectList.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject obj)
	{
		this.objectList.add(obj);
	}
	
	public void removeObject(GameObject obj)
	{
		this.objectList.remove(obj);
	}
}
