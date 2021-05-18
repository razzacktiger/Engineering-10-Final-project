package Game1;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import javax.swing.*;

public class PingPMain extends Canvas implements Runnable{

	private static final long serialVersionUID = 5884861145285286896L;

	//Main Function
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
	
	//main class standard constructor function thats required
	public PingPMain() {
		canvasSetup();
		initialize();
		new Window("Simple Ping Window", this);
	}
	
	public static initialize() {
		//initialize ball
		Ball ball = new Ball();
		
		//initialize three paddles:
		//Paddle leftpaddle = new Paddle();
		//Paddle rightPaddle = new Paddle();
		//Paddle middlePaddle = new Paddle();
		
		//initialize main menue
		menue = new 
	}
	
	//function for setting the dimensions
	public void canvasSetup() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}
	public void run() {
		
	}
	public void start() {
		gameThread = new Thread(this);
		gameThread.start();
		running = true;
	}
	
	public void stop() 
	{
		try 
		{
			gameThread.join();
			running = false;
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void draw() {
		//initialize drawing tools
		
		BufferStrategy buffer = this.getBufferStrategy();
		
		if (buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics G = buffer.getDrawGraphics();
		
		//filling the background
		fillBackground(G);
		//drawing the ball
		
		//drawing the paddles and score board
		
		// "dispose" or real drawing
	}
	
	public void fillBackground(Graphics G) {
		//Color Background 
		g.setColor(Color black)
		
		
	}
	public void update() {
	//update the ball
		
	//update the paddles
		
	
	}
}