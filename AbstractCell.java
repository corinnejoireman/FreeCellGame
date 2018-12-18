import java.util.*;

/**
 * Project 8
 * AbstractCell.java
 * Contains all the methods that can be utilized by each cell type
 * @author Bethany Reitsma, Rinn Joireman, George Barker, Pengrui Wang
 */

abstract class AbstractCell implements Cell{
	
	//instantiate variables
	protected List<Card> cards;

	/**
	 * Instantiates an ArrayList of cards
	 */
	public AbstractCell() {
		cards = new ArrayList<Card>();
	}
	
	/**
	 * Clears cell
	 */
	public void clear() {
		cards.clear();
	}
	
	/**
	 * Checks if cell is empty
	 * @return boolean representing if cell is empty or not
	 */
	public boolean isEmpty() {
			
		if(cards.size()==0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns the size of the ArrayList of cards
	 */
	public int size(){
		return(cards.size());
	}
	
	/**
	 * Throws an IllegalArgumentException when remove is run,
	 * to be overridden by other remove methods in other Cell classes
	 */
	public void remove(Card c) {
		cards.remove(c);
	}
	
	/**
	 * Individually prints out a cell
	 */
	public String toString() {
		
		String str = "";
		if(! cards.isEmpty()) {
			str += "\nContents bottom to top: ";
			for(Card thing : cards) {
				str += "\n";
				str += thing.toString();
			}
		}
		else {
			str = "Cell is empty";
		}
		return(str);
	}
	
	/**
	 * Creates an iterator over the ArrayList cards
	 */
	public Iterator<Card> iterator(){
		return cards.iterator();
	}
	
	/**
	 * Gets the card at the end of the ArrayList
	 */
	public Card peekTop() {
		return cards.get(this.size() - 1);
	}
	
	/**
	 * Adds the card(s) from the specified source to 
	 */
	public boolean addFrom(Cell source){
		if (this.canAddFrom(source)) {
			cards.add(source.peekTop());
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Tests to see if you can add from the source cell
	 */
	public boolean canAddFrom(Cell source) {
		return (! (source instanceof FoundationCell)) && (! source.isEmpty());
	}
	
	/**
	 * Sets the original cards in the tableau pile, only to be used in 
	 * deal and in testing
	 * @param Card c
	 */
	public void dealTo(Card c) {
		cards.add(c);
	}
}
