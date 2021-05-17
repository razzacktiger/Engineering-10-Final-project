package Game1;

import javax.swing.JFrame;
public class Window {
	public Window(String title, PingPMain game) 
	{ 
	JFrame frame = new JFrame();
	// creates object of Window Jframe 
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// set up the close operation
	frame.setResizable(false);
	//The user cannot resize the window
	frame.add(game);
	//putting the running app in the frame
	frame.pack();
	// pack all of the components with a "perfect fit"
	frame.setLocationRelativeTo(null);
	// center the game to the screen
	frame.setVisible(true);
	// sets the window frame as visible
	
	game.start();
	//start the game
	}
}
