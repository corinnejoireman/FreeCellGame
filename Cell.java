import java.util.*;

/**
 * Project 8
 * Cell.java
 * The interface for the methods used in the freecell game
 * @author Bethany Reitsma, Rinn Joireman, George Barker, Pengrui Wang
 */
public interface Cell extends Iterable<Card>{

	/**
	 * Takes Collection representing cell and finds the length of it
	 * @return integer representing size of Cell, amount of cards in Cell
	 * 
	 */
	public int size();
	
	/**
	 * clears the cell
	 */
	public void clear();
	
	/**
	 * @preconditions 
	 * Takes a card as a parameter and puts it and each card after it into a Stack of cards. 
	 * @param Card c
	 * @return
	 */
	public void remove(Card c);
	
	/**
	 * Checks if cell is empty
	 * @return boolean representing whether or not the list is empty
	 */
	public boolean isEmpty();
	
	/**
	 * Looks at the card at the top of the pile of cards
	 * @return
	 */
	public Card peekTop();
	
	/**
	 * Checks to see if you are able to add from the source cell
	 * @param source
	 * @return
	 */
	public boolean addFrom(Cell source);
	
	/**
	 * Checks to see if you can add from the source cell
	 * @param source
	 * @return
	 */
	public boolean canAddFrom(Cell source);
	
	/**
	 * Returns an iterator over cards
	 * @return
	 */
	public Iterator<Card> iterator();
	
	/**
	 * Sets the original cards in the tableau pile, only to be used in deal and in testing
	 * @param Card c
	 */
	public void dealTo(Card c);
	
	/**
	 * Checks if tableau cell is in ascending order
	 * @return boolean representation of whether or not the cell is in ascending order
	 */
	public boolean isWon();

	/**
	 * A method only used in TableauCell similar to peekTop, thus it is not bad to return an arrayList because the 
	 * entire  inner structure of the Cell is not revealed
	 * @param topCard
	 * @return ArrayList of moveable cards
	 */
	public ArrayList<Card> getFinalSequence(Card topCard);	
}
