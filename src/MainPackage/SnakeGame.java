package MainPackage;

import java.awt.Canvas;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class SnakeGame extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	static int WIDTH = 500, HEIGHT = WIDTH / 10 * 9;
	private Thread thread;
	private boolean running = true;
	private Handler handler;
	private Random r;
	
	private HUD hud;
	private PauseMenu pauseMenu;
	private DeathScreen deathScreen;
	
	public static STATE gameState = STATE.pause;
	
	
	public SnakeGame()
	{
		r = new Random();
		
		handler = new Handler();
		hud = new HUD();
		pauseMenu = new PauseMenu(this, handler);
		deathScreen = new DeathScreen(this, handler);
		
		new Window(WIDTH, HEIGHT, "Snake", this);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new PauseMenu(this, handler));
		
		if(gameState == STATE.play) {
			
			handler.addObject(new SnakePlayer(WIDTH / 2 - 20, HEIGHT / 2 - 40, ID.Player, handler));
//
			for(int i = 0; i < 3; i++)
			{
				handler.addObject(new Apple(r.nextInt(SnakeGame.WIDTH - 20), r.nextInt(SnakeGame.HEIGHT - 40), ID.Apple, handler));
			}
		
			handler.addObject(new Bird(r.nextInt(SnakeGame.WIDTH - 20), r.nextInt(SnakeGame.HEIGHT - 40), ID.Bird, handler));
			
		}
		System.out.println("Active threads: " + java.lang.Thread.activeCount());

	}
	

	public synchronized void start() {
		thread = new Thread(this);
		thread.start(); //start() invokes the run() method on the thread
		running = true;
	}


	public void run() {
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while(delta >= 1) {
					tick();
					delta--;
				}
				if(running) 
					render();
					frames ++;
				
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("FPS: " + frames);		
					frames = 0;
				}
		}
		
		stop();
	}
	
	private void tick() {
		if(gameState == STATE.play) {
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);
		if(gameState == STATE.play) {
		hud.render(g);
		}else if(gameState == STATE.pause)
		{
			pauseMenu.render(g);
		}else
		{
			deathScreen.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max)
	{
		if(var >= max) {
			return var = max;
		}
		else if(var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	
    public synchronized void stop() {
		
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
    
    public static void main(String[] args)
	{
		new SnakeGame();
	}

}
