import java.util.*;

/**
 * Project 8
 * FreeCell.java
 * Designs a variable for a free cell that contains at most one card of any type
 * @author Rinn Joireman, George Barker, Bethany Reitsma, Pengrui Wang
 */


public class FreeCell extends AbstractCell{
		
	public FreeCell(){
		super();
	}

	/**
	 * Gets the card currently occupying the free cell if there is one
	 * @return c
	 */
	public Card peekTop() {
		
		if (!cards.isEmpty()) {
			return cards.get(0);
		}
		else {
			return null;
		}
	}
	
	// only to be used in Tester method
	public void add(Card c) {
		cards.add(c);
	}
	
	public boolean canAddFrom(Cell fromPile) {
		
		Card thisTop = this.peekTop();
		
		if(thisTop == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addFrom(Cell fromPile) {
		
		Card thatTop = fromPile.peekTop();
		fromPile.remove(thatTop);
		cards.add(thatTop);
		return true;
	}

	@Override
	public ArrayList<Card> getFinalSequence(Card c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWon() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
