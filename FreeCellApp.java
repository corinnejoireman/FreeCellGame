import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Project 8
 * FreeCellApp.java
 * The main window for displaying the FreeCellGame. Instantiates the model and passes it to
 * the view
 * @author Rinn Joireman, Bethany Reitsma, George Barker, Pengrui Wang
 *
 */

public class FreeCellApp extends JFrame{

    public static void main(String[] args){
    	FreeCellGame g = new FreeCellGame();
        AppView theGUI = new AppView(g);
        theGUI.setSize(1750, 1750);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theGUI.setVisible(true);
     }

}
