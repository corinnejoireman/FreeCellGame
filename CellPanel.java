import javax.swing.*;
import java.awt.*;

/**
 * Project 8
 * Represents the GUI component for painting an image of a playing card.
 * CellPanel.java
 * @author Rinn Joireman, Bethany Reitsma, George Barker, Pengrui Wang
 *
 */
public class CellPanel extends AbstractPanel implements Panel{
    
    /**
     * Constructor that accepts a ViewInformer and a cell
     * @param c
     * @param view
     */
    public CellPanel(Cell c, ViewInformer view) {
    	super(c, view);
    }

    /**
     * Paints the card's face image if a card is present, otherwise, paints the back side image.
     */
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	Icon image;
    	if (cell.peekTop() == null){
    		image = Card.getBack();
    		g.setColor(Color.yellow);
    		int x = (getWidth() - image.getIconWidth()) / 2;
    		int y = (getHeight() - image.getIconHeight()) / 2;
    		g.drawRect(x, y, image.getIconWidth(), image.getIconHeight());
    	}
    	else {
    		Card card = cell.peekTop();
    		image = card.getImage();
    		int x = (getWidth() - image.getIconWidth()) / 2;
    		int y = (getHeight() - image.getIconHeight()) / 2;
    		image.paintIcon(this, g, x, y);
    	}
    }

}
