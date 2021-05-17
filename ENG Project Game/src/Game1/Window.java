package Game1;

import javax.swing.JFrame;
public class Window {
	public Window(JFrame title, PingPMain game) 
	{ 
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.add(game);
	
	}
}
