package Game1;

import java.awt.Color;
import java.awt.Graphics;

/**
 * class for the ball in the game
 * 
 * ***Original coder Zayed, modified by Haroon & Hunter
 *
 */
public class Ball {

	public static final int SIZE = 16;

	private int x, y; // position of top left corner of square
	private int xVel, yVel; // either 1 or -1
	private int speed; // speed of the ball

	/**
	 * constructor
	 */
	public Ball() {
		reset();
	}

	/**
	 * setup initial position and velocity
	 */
	public void reset() {
		// initial position
		x = Game.WIDTH / 2 - SIZE / 2;
		y = Game.HEIGHT / 2 - SIZE / 2;

		// initial random velocity
		//Math.Random() gives number between 0 and 1,
		//multiplying by 2 gives number between 0 and 2
		//subtracting by 1 gives number between -1 and 1
		xVel = Game.sign(Math.random() * 2.0 - 1);
		yVel = Game.sign(Math.random() * 2.0 - 1);
		
		//*** adjusted initial random speed
		speed = (int) (Math.random()*5 + 3);
	}

	/**
	 * Draw ball (a square)
	 * 
	 * @param g: Graphics object used to draw everything
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, SIZE, SIZE);
		
	}

	/**
	 * update position AND collision tests
	 * 
	 * @param lp: left paddle
	 * @param rp: right paddle
	 */
	

	public void update(Paddle lp, Paddle rp) 
	{
        
		// update position
		x += xVel * speed;
		y += yVel * speed;

		// collisions with the ceilings/floor
		if (y + SIZE >= Game.HEIGHT || y <= 0)
			changeYDir();

		// with walls

		if (x + SIZE >= Game.WIDTH) { // right wall
			lp.addPoint();
			reset();
		}
		if (x <= 0) { // left wall
			rp.addPoint();
			reset();
		}
	   
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	
	public int getY() {
		return y;
	}

	/**
	 * switch x direction IF ball going right, go left otherwise, go right
	 */
	public void changeXDir() {
		xVel *= -1;
	}

	/**
	 * switch y direction IF ball going up, go down otherwise, go up
	 */
	public void changeYDir() {
		yVel *= -1;
	}

}
