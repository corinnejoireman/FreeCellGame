import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.*;
/**
 * Project 8
 * AppView.java
 * The main window for displaying the game free cell
 * @author Rinn Joireman, Bethany Reitsma, Pengrui Wang, George Barker
 *
 */
public class AppView extends JFrame{
	
	Panel fromPanel = null;
	
	private FreeCellGame game;
	private JLabel moveLabel;
	private JLabel highMoveLabel;
	
    public AppView(FreeCellGame g){
    	
    	this.game = g;
    	this.setTitle("Free Cell Game");

        final AppViewInformer info = new AppViewInformer();

        // Create and set layout and constraints objects
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        Container c = getContentPane();
        c.setLayout(layout);
    	
    	//Makes Labels and Buttons
        JLabel freeCellLabel = new JLabel("Free Cell");
        JLabel foundationCellLabel = new JLabel("Foundation Cell");
        moveLabel = new JLabel("Move Count: " + game.getMoveCount());
        highMoveLabel = new JLabel("Best Game: --");
	    JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        	game.newGame();
        	moveLabel.setText("Move Count: " + game.getMoveCount());
        	AppView.this.repaint();
        }});
        JButton hintButton = new JButton("Hint :)");
        hintButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(AppView.this,
					    game.hint(),
					    "Here is a hint!",
					    JOptionPane.PLAIN_MESSAGE);
        	}
        });
        
        //Adds Labels and Buttons to window
        constraints.gridwidth = 4;
        c.add(freeCellLabel, constraints);
        constraints.gridx = 4;
        c.add(foundationCellLabel, constraints);
	    constraints.gridx=0;
	    constraints.gridy=3;
	    constraints.gridwidth=2;
	    c.add(newGameButton, constraints);
	    constraints.gridx=2;
	    c.add(moveLabel, constraints);
	    constraints.gridx=4;
	    c.add(highMoveLabel,constraints);
	    constraints.gridx=6;
	    c.add(hintButton, constraints);
       
	    //Adds FreePanels and FoundationPanels to window
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.weightx = 50;
	    constraints.weighty = 50;
	    constraints.gridx = 0;
    	constraints.gridy = 1;
    	constraints.gridwidth = 1;
	    for(int i = 0;  i < 4; i ++) {
	    	Cell p = game.getFreeCell(i);
	    	CellPanel fp = new CellPanel(p, info);
	    	c.add(fp, constraints);
	    	constraints.gridx++;
	    }
	    for(int i = 0; i < 4; i++) {
	    	Cell f = game.getFoundationCell(i);
	    	CellPanel cp = new CellPanel(f, info);
	    	c.add(cp, constraints);
	    	constraints.gridx++;
	    }
	    //Adds TableauPanels to the cells Panel
	    constraints.gridx = 0;
	    constraints.weighty = 100;
	    constraints.weightx = 100;
    	constraints.gridy = 2;
	    for(int i = 0; i < 8; i++) {
	    	Cell t = game.getTableauCell(i);
	    	TableauPanel tp = new TableauPanel(t, info);
	    	c.add(tp, constraints);
	    	constraints.gridx++;
	    }
    }
    
    class AppViewInformer implements ViewInformer{

		public void panelPressed(AbstractPanel panel) {
			
			if(game.gameLost()) {
				JOptionPane.showMessageDialog(AppView.this,
					    "You Lost The Game :(",
					    "Message",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			if(game.gameWon()) {
				highMoveLabel.setText("Best Game: " + game.getHighMoveCount());
				JOptionPane.showMessageDialog(AppView.this,
					    "You Won!",
					    "Message",
					    JOptionPane.PLAIN_MESSAGE);
			}
			
			if (panel == fromPanel) {
				fromPanel = null;
			}
			else if (fromPanel == null) {
				fromPanel = panel; 	
			}
			else {
				Cell fromPile = fromPanel.getCell();
				Cell toPile = panel.getCell();
				if (game.makeMove(fromPile,toPile)) {
					panel.repaint();
					moveLabel.setText("Move Count: " + game.getMoveCount());
					fromPanel.repaint();
					fromPanel = null;
				}
				else {
					fromPanel = null;
					JOptionPane.showMessageDialog(AppView.this,
						    "Invalid Move",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		}
    }
}   