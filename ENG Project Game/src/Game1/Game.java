package Game1;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

/**
 * class that manages the game, drawing and updating physics
 * 
 *  *** Original coder Zayed, modified by Haroon & Hunter
 *  *** Three asterisks or stars such as "***" in the comments denote Haroon and Hunter's comments/modifications
 *  *** Also, the position, x,y 
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public final static int WIDTH = 1000;
	public final static int HEIGHT = WIDTH * 9 / 16; // 16:9 aspect ratio

	public boolean running = false; // true if the game is running
	private Thread gameThread; // thread where the game is updated AND drawn (single thread game)

	// ball object -> the thing that bounces on the walls and paddles
	private Ball ball1;
	private Ball ball2; //*** created a second Ball object to make the game a bit more fun.

	// the paddles
	
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	//***added a third middle Paddle or Obstacle object
	private Paddle middlePaddle;

	public MainMenu menu;//Main Menu object
	public EndMenu endMenu;//*** End Menu object

	/**
	 * constructor
	 */
	public Game() {

		canvasSetup();

		new Window("Not So Simple Pong", this);

		initialise();

		this.addKeyListener(new KeyInput(leftPaddle, rightPaddle));
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		//*** listening for the end Menu as well
		this.addMouseListener(endMenu);
		this.addMouseMotionListener(endMenu);
		this.setFocusable(true);

	}

	/**
	 * initialize all our game objects
	 */
	private void initialise() 
	{
		// Initialize Ball object
		ball1 = new Ball();
		ball2 = new Ball();

		// Initialize paddle objects
		leftPaddle = new Paddle(Color.green, true, false, false);
		rightPaddle = new Paddle(Color.red, false, false, true);
		middlePaddle = new Paddle(Color.blue, false, true, false);
		// initialize main menu
		menu = new MainMenu(this);
		//*** 
		endMenu = new EndMenu(this);
	}

	/**
	 * just to setup the canvas to our desired settings and sizes
	 */
	private void canvasSetup() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}

	/**
	 * Game loop
	 */
	@Override
	public void run() {
		// so you can keep your sanity, I won't explain the game loop... you're welcome
		// I have a video on this game loop tho, check it out

		this.requestFocus();

		// game timer
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update();
				delta--;
				draw();
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}

		stop();
	}

	/**
	 * start the thread and the game
	 */
	public synchronized void start() 
	{
		gameThread = new Thread(this);
		/*
		 * since "this" is the "Game" Class you are in right now and it implements the
		 * Runnable Interface we can give it to a thread constructor. That thread with
		 * call it's "run" method which this class inherited (it's directly above)
		 */
		gameThread.start(); // start thread
		running = true;
	}

	/**
	 * Stop the thread and the game
	 */
	public void stop() 
	{
		try {
			gameThread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * draw the back and all the objects
	 */
	public void draw() 
	{
		// Initialize drawing tools first before drawing

		BufferStrategy buffer = this.getBufferStrategy(); // extract buffer so we can use them
		// a buffer is basically like a blank canvas we can draw on

		if (buffer == null) { // if it does not exist, we can't draw! So create it please
			this.createBufferStrategy(3); // Creating a Triple Buffer
			/*
			 * triple buffering basically means we have 3 different canvases this is used to
			 * improve performance but the drawbacks are the more buffers, the more memory
			 * needed so if you get like a memory error or something, put 2 instead of 3.
			 * 
			 * BufferStrategy:
			 * https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferStrategy.html
			 */

			return;
		}

		Graphics g = buffer.getDrawGraphics(); // extract drawing tool from the buffers
		/*
		 * Graphics is class used to draw rectangles, ovals and all sorts of shapes and
		 * pictures so it's a tool used to draw on a buffer
		 * 
		 * Graphics: https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
		 */

		// draw background
		drawBackground(g);

		// draw main menu contents
		if (menu.active) {
			menu.draw(g);
		}
		//*** draw end menu components
		if (endMenu.active1) 
		{
			 endMenu.draw(g);
		}
		
		

		// draw ball
		ball1.draw(g);
		ball2.draw(g);

		// draw paddles (score will be drawn with them)
		leftPaddle.draw(g);
		rightPaddle.draw(g);
		middlePaddle.draw(g);
		

		// actually draw
		g.dispose(); // Disposes of this graphics context and releases any system resources that it
						// is using
		buffer.show(); // actually shows us the 3 beautiful rectangles we drew...LOL

	}

	/**
	 * draw the background
	 * 
	 * @param g - tool to draw
	 */
	private void drawBackground(Graphics g) {
		// black background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Dotted line in the middle
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g; // a more complex Graphics class used to draw Objects (as in give in an Object
		// in parameter and not dimensions or coordinates)
		// How to make a dotted line:
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
		g2d.setStroke(dashed);
		g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
	}

	/**
	 * update settings and move all objects
	 */
	public void update() {
        //*** 
		if (!menu.active && !endMenu.active1) {
			// update ball (movements)
			ball1.update(leftPaddle, rightPaddle);

			// update paddles (movements)
			leftPaddle.update(ball1);
			rightPaddle.update(ball1);
			middlePaddle.updateObstacle(ball1);
			ball2.update(leftPaddle, rightPaddle);

			// update paddles (movements)
			leftPaddle.update(ball2);
			rightPaddle.update(ball2);
			//*** We execute the update function for the middle paddle.
			middlePaddle.updateObstacle(ball2);
			/* ***Checking if either paddle reaches a score past 11. 
			   Once that occurs, all positons and score are resent 
			   and the end menu sets to true and thus appears */
			if ((rightPaddle.getPoint() >= 11 || leftPaddle.getPoint() >= 11)) {
				rightPaddle.setScore(0);
				leftPaddle.setScore(0);
				middlePaddle.reset();
				ball1.reset();
				ball2.reset();
				rightPaddle.reset();
				leftPaddle.reset();
				endMenu.active1 = true;
			}
		}
			
			/*
			 * 	//***this contains some failed code				
			 * public boolean checkScore() {
				if ((rightPaddle.getPoint() == 11 || leftPaddle.getPoint() == 11)) {
					endMenu.active1 = true;
				return true;
				}
					*/
	}

	/**
	 * used to keep the value between the min and max
	 * 
	 * @param value - integer of the value we have
	 * @param min   - minimum integer
	 * @param max   - maximum integer
	 * @return: the value if value is between minimum and max, minimum is returned
	 *          if value is smaller than minimum, maximum is returned if value is
	 *          bigger than maximum
	 */
	public static int ensureRange(int value, int min, int max) {
		return Math.min(Math.max(value, min), max);
	}

	/**
	 * returns the sign (either 1 or -1) of the input
	 * 
	 * @param d - a double for the input
	 * @return 1 or -1
	 */
	public static int sign(double d) 
	{
		if (d <= 0)
			return -1;

		return 1;
	}

	/**
	 * start of the program
	 */
	public static void main(String[] args) {
		new Game();
	}

}
