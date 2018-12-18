import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Project 8
 * AbstractPanel.java
 * Contains all the methods that can be utilized by each panel type
 * @author Bethany Reitsma, Rinn Joireman, George Barker, Pengrui Wang
 */


abstract class AbstractPanel extends JPanel implements Panel{
	
	/**
	 * PanelListener Class that acts as a MouseAdapter and  calls panelPressed when 
	 * the mouse is pressed
	 */
	protected class PanelListener extends MouseAdapter {
		
		public void mousePressed(MouseEvent e) {
			v.panelPressed(AbstractPanel.this);
		}
	}

	protected Cell cell;
	private ViewInformer v;
	private Color background = new Color(17, 130, 29);

	/**
     * Constructor for an empty panel, displays a wire frame. 
     */
    public AbstractPanel(){
        cell = null;
    }
    
    /**
     * Constructor that accepts a ViewInformer and a cell
     * @param c
     * @param view
     */
    public AbstractPanel(Cell c, ViewInformer view) {
    	cell = c;
        setBackground(background);
        v = view;
        PanelListener p = new PanelListener();
        this.addMouseListener(p);
    }
    /**
     * A method that returns the cell that the panel represents
     * @return Cell c - the cell that the panel displays
     */
    public Cell getCell(){
    	return cell;
    }
    
    /**
     * Paints the card's face image if a card is present, otherwise, paints 
     * the back side image.
     */
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	Icon image;
    	if (cell == null){
    		image = Card.getBack();
    		g.setColor(Color.yellow);
    		int x = (getWidth() - image.getIconWidth()) / 2;
    		int y = (getHeight() - image.getIconHeight()) / 2;
    		g.drawRect(x, 0, image.getIconWidth(), image.getIconHeight());
    	}
    }
    
        
}
