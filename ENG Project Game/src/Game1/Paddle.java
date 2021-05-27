package Game1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * class for the paddle
 * 
 * ***Original coder Zayed, modified by Haroon & Hunter
 *
 */
public class Paddle {

	private int x, y; // positions
	private int vel = 0; // speed and direction of paddle
	private int speed = 5; // speed of the paddle movement
	private int width = 22, height = 85; // dimensions
	private int score = 0; // score for the player
	private Color color; // color of the paddle
	private boolean left;
	private boolean right;
	private boolean middle;// true if it's the left paddle

	/**
	 * create initial properties for the paddle
	 * 
	 * @param color - color of the paddle
	 * @param left  - boolean to know if it's the left paddle or not
	 */
	public Paddle(Color c, boolean left, boolean middle, boolean right) {
		// initial properties
		color = c;
		this.left = left;
		this.right = right;
		this.middle = middle;

		if (left) // different x values if right or left paddle
			x = 0;
		if (right)
			x = Game.WIDTH - width;
		if (middle) {
			x = Game.WIDTH/2 - width/2;
			vel = 2;
		}
		y = Game.HEIGHT / 2 - height / 2;

	}

	/**
	 * add a point to the player 
	 */
	public void addPoint() {
		score++;
	}
	public void setScore(int points) {
		score = points;
	}
	
	public int getScore() {
		return score;
	}

	/**
	 * Draw paddle (a rectangle), Draw score too
	 * 
	 * @param g - Graphics object used to draw everything
	 */
	public void draw(Graphics g) {

		// draw paddle
		g.setColor(color);
		g.fillRect(x, y, width, height);

		// draw score
		int sx; // x position of the string
		int padding = 25; // space between dotted line and score
		//*** made the score show up only for left or right paddles
		if (left) {
			String scoreText = Integer.toString(score);
			Font font = new Font("Roboto", Font.PLAIN, 50);
			int strWidth = g.getFontMetrics(font).stringWidth(scoreText); 
			// we need the width of the string so we can
			// center it properly (for perfectionists)
			sx = Game.WIDTH / 2 - padding - strWidth;
			g.setFont(font);
			g.drawString(scoreText, sx, 50);
		} 
		if (right) {
			String scoreText = Integer.toString(score);  
			Font font = new Font("Roboto", Font.PLAIN, 50);
			sx = Game.WIDTH / 2 + padding;
			g.setFont(font);
			g.drawString(scoreText, sx, 50);
		}
			
		
		
	}

	/**
	 * update position AND collision tests
	 * 
	 * @param b - the ball
	 */
	public void update(Ball b) {

		// update position
		y = Game.ensureRange(y + vel, 0, Game.HEIGHT - height);

		// collisions
		int ballX = b.getX();
		int ballY = b.getY();

		if (left) {

			if (ballX <= width + x && ballY + Ball.SIZE >= y && ballY <= y + height)
				b.changeXDir();

		} if (right) {

			if (ballX + Ball.SIZE >= x && ballY + Ball.SIZE >= y && ballY <= y + height)
				b.changeXDir();
		  

		}

	}
	public void updateObstacle(Ball b) 
	{
		 
		int ballX = b.getX();
		int ballY = b.getY();
		//System.out.print("initial y "+y);
		y = Game.ensureRange(y + vel, 0, Game.HEIGHT - height);
		//System.out.print("updated y "+y);
		if (middle)
		{
			if (y + height >= Game.HEIGHT || y <= 0) 
			{
				//System.out.print("changing  direction "+y);
				changeDir();
				y += vel * speed;
				//System.out.print("New y"+y);
			}
			if (ballX + Ball.SIZE <= x + width && ballX + Ball.SIZE >= x + width/2 && ballY + Ball.SIZE >= y && ballY <= y + height)
					b.changeXDir();
		}
		
	}

	public void reset() {
		// initial position
		if (left) // different x values if right or left paddle
			x = 0;
		if (right)
			x = Game.WIDTH - width;
		if (middle)
			x = Game.WIDTH/2 - width/2;
		y = Game.HEIGHT / 2 - height / 2;

	}	
		

	/**
	 * switches the direction
	 * 
	 * @param direction - -1 for up and 1 for down
	 */
	
	public void changeDir() {
		vel *= -1;
	}
	public void switchDirections(int direction) {
		vel = speed * direction;
	}

	/**
	 * stop moving the paddle
	 */
	public void stop() {
		vel = 0;
	}

	public int getPoint() {
		// TODO Auto-generated method stub
		return score;
	}

}