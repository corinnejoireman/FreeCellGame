import javax.swing.*;
import java.awt.*;

/**
* Project 8
* Panel.java
* The interface for the methods used by the Panel
* @author Bethany Reitsma, Rinn Joireman, George Barker, Pengrui Wang
*/
public interface Panel {

	/**
	 * Draws the panel on the GUI
	 * @param Graphics g
	 */
	public void paintComponent(Graphics g);
	
	/**
	 * Returns the panel that the Panel object displays
	 * @return Cell that Panel objecct displays
	 */
	public Cell getCell();
	
	/**
	 * Repaints the cell with new cards
	 */
	public void repaint();
}
