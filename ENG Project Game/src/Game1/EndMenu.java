package Game1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * New class created for additional End Game menu in the game
 * 
 * ***Template borrowed from Original coder Zayed, 
 * modified by Haroon & Hunter
 *
 */
public class EndMenu extends MouseAdapter {
    public boolean active1; // true if main menu is displaying

	// Play button
	private Rectangle continueBtn; // Play Button
	private String continueTxt = "Play Again";
	private boolean cHighlight = false; // true if the mouse hovered over the Play button

	// Quit button
	private Rectangle quitBtn; // Quit Button
	private String quitTxt = "Quit";
	private boolean qHighlight = false; // true if the mouse hovered over the Quit button

	private Font font;

	/**
	 * constructor
	 * 
	 * @param game - the Game object
	 */

	public EndMenu(Game game) 
	{

		active1 = false; 
		if (active1 == true) //the game will start when active is set to true and thus the end menu is activated
		{
			game.stop();
			game.update();
		}

		// position and dimensions of each button
		int x, y, w, h;

		w = 300;
		h = 150;

		y = Game.HEIGHT / 2 - h / 2;

		x = Game.WIDTH / 4 - w / 2;
		continueBtn = new Rectangle(x, y, w, h);

		x = Game.WIDTH * 3 / 4 - w / 2;
		quitBtn = new Rectangle(x, y, w, h);

		font = new Font("Roboto", Font.PLAIN, 50);
	}

	/**
	 * Draw buttons (rectangles) and text in the Main Menu
	 * 
	 * @param g - Graphics object used to draw everything
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(font);

		// draw buttons
		g.setColor(Color.black);
		if (cHighlight)
			g.setColor(Color.white);
		g2d.fill(continueBtn);

		g.setColor(Color.black);
		if (qHighlight)
			g.setColor(Color.white);
		g2d.fill(quitBtn);

		// draw button borders
		g.setColor(Color.white);
		g2d.draw(continueBtn);
		g2d.draw(quitBtn);

		// draw text in buttons

		int strWidth, strHeight;

		// continue Button text
		strWidth = g.getFontMetrics(font).stringWidth(continueTxt);
		strHeight = g.getFontMetrics(font).getHeight();

		g.setColor(Color.green);
		g.drawString(continueTxt, (int) (continueBtn.getX() + continueBtn.getWidth() / 2 - strWidth / 2),
				(int) (continueBtn.getY() + continueBtn.getHeight() / 2 + strHeight / 4));

		// Quit Button text
		strWidth = g.getFontMetrics(font).stringWidth(quitTxt);
		strHeight = g.getFontMetrics(font).getHeight();

		g.setColor(Color.red);
		g.drawString(quitTxt, (int) (quitBtn.getX() + quitBtn.getWidth() / 2 - strWidth / 2),
				(int) (quitBtn.getY() + quitBtn.getHeight() / 2 + strHeight / 4));

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Point p = e.getPoint();

		if (continueBtn.contains(p))
			active1 = false;
		    
		    
		else if (quitBtn.contains(p))
			System.exit(0);
		
			
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		Point p = e.getPoint();

		// determine if mouse is hovering inside one of the buttons
		cHighlight = continueBtn.contains(p);
		qHighlight = quitBtn.contains(p);

	}

}

