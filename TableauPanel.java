import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 * Project 8
 * TableauPanel.java
 * Represents the GUI component for painting an image of a tableau cell
 * @author Bethany Reitsma, Rinn Joireman, George Barker, Pengrui Wang
 * 
 */

public class TableauPanel extends AbstractPanel implements Panel{

    /**
     * Constructor that accepts a ViewInformer and a cell
     * @param c
     * @param view
     */
    public TableauPanel(Cell c, ViewInformer view) {
    	super(c, view);
    }

    /**
     * Paints the card's face image if a card is present, otherwise, paints the back side image.
     */
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	Icon image;
    	if (cell.isEmpty()){
    		image = Card.getBack();
    		g.setColor(Color.yellow);
    		int x = (getWidth() - image.getIconWidth()) / 2;
    		int y = (getHeight() - image.getIconHeight()) / 2;
    		g.drawRect(x, 0, image.getIconWidth(), image.getIconHeight());
    	}
    	else {
    		int cardHeight = 0;
    		for(Card c : cell) {
    			image = c.getImage();
    			int x = (getWidth() - image.getIconWidth()) / 2;
    			int y = cardHeight;
    			image.paintIcon(this, g, x, y);
    			cardHeight += (image.getIconHeight())/3;
    			
    		}
    	}
    }
    
}
