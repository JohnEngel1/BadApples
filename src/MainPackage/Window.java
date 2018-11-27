package MainPackage;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import java.awt.event.*;

public class Window extends Canvas{

	private static final long serialVersionUID = 1L;
	
	
	public Window(int width, int height, String title, SnakeGame game)
	{
		JFrame frame = new JFrame(title);
				

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
		
	}
	
	
	
	

}
