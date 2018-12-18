import java.util.*;

/**
 * Project 8
 * FoundationCell.java
 * Class of foundation cell that can hold up to 13 cards, same suit in ascending order
 * supports adding but not removing
 * @author Rinn Joireman, Bethany Reitsma, George Barker, Pengrui Wang 
*/

public class FoundationCell extends AbstractCell{
	
	public FoundationCell(){
		super();
	}
	
	/**
	 * Gets the top card currently occupying the foundation cell, if there is one
	 * @return c
	 */
	public Card peekTop() {
		if (!this.isEmpty()) {
			return(cards.get(cards.size()-1));
		}
		else {
			return(null);
		}
	}
	
	/**
	 * If card is an ace and foundation cell is empty return true
	 * Check if cards suit and rank are compatible with previous top card
	 * @param Card c
	 * @return boolean representing if card can be added to stack
	 */
	public boolean canAddFrom(Cell fromPile) {
		
		Card fromTop = fromPile.peekTop();
		Card thisTop = this.peekTop();
		
		if (fromTop == null) {
			return false;
		}
		else if(thisTop == null && fromTop.getRank()==1) {
			return true;
		}
		else if (thisTop == null && fromTop.getRank()!=1){
			return false;
		}
		else if(thisTop.getSuit() == fromTop.getSuit() && thisTop.getRank() ==fromTop.getRank()-1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean addFrom(Cell fromPile) {
		Card toAdd = null;
		if(this.canAddFrom(fromPile))
			toAdd = fromPile.peekTop();
			fromPile.remove(toAdd);
			cards.add(toAdd);
		return true;
		
	}

	public void remove() {
		throw new IllegalArgumentException("Cannot remove from Cell.");
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	public Card next() {
		// TODO Auto-generated method stub
		return null;
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
