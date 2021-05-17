package Game1;
import java.awt.Canvas;
public class PingPMain extends Canvas implements Runnable{
	public static void main(String [] args) {
	new PingPMain();
	}
	//set the stand "final" size of the game
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH * 9/16;
	
	public boolean running = false; //game isn't running until true
	private Thread gameThread; //processing thread of game
	
	//add in the basic class variables:
	private Ball ball;
	private Paddle paddle1;
	private Paddle paddle2; 
	
	public static void main(String [] args) {
	
	}
	
	//main class standard constructor function thats required
	public PingPMain() {
		canvasSetup();
	}
	
	//function for setting the dimensions
	public canvasSetup() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}
	public void run() {
		
	}
	public void start() {
		
	}
}