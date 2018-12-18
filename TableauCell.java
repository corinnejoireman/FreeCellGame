import java.util.*;

/**
 * Project 8
 * TableauCell.java
 * Designs a variable for a tableau cell that can hold many cards at once
 * @author Rinn Joireman, Bethany Reitsma, Pengrui Wang, George Barker
 */
public class TableauCell extends AbstractCell{
	
	public TableauCell() {
		super();
	}
	
	/**
	 * Gets the card currently occupying the free cell if there is one
	 * @return c
	 */
	public Card peekTop() {
		if (!this.isEmpty()) {
			return(cards.get(this.size()-1));
		}
		else {
			return null;
		}
	}

	/**
	 * @param Card c. The top card in the pile that wants to accept cards from this TtableauCell
	 * gets the final sequence of alternating color cards that decrease by one in the cell
	 * Thus, the return value will hold all cards that should be moved
	 * @return ArrayList<Card> holds all cards that should be moved from one pile to another
	 */
	public ArrayList<Card> getFinalSequence(Card c){
		
		//instantiates a new ArrayList variable
		ArrayList<Card> seq = new ArrayList<Card>();
		Card first = null;
		Card next = null;
		ArrayList<Card> backwards = new ArrayList<Card>();
		
		
		for (int i = cards.size()-1; i > -1; i --) {
			next = cards.get(i);
			
			if (backwards.isEmpty()) {
				backwards.add(next);
				first = next;
				if (!(c == null) && ! next.sameColor(c) && next.greaterByOne(c)) {
					break;
				}
			}
			else if(! first.sameColor(next) && first.greaterByOne(next)) {
					
				if (!(c == null) && ! next.sameColor(c) && next.greaterByOne(c)) {
						backwards.add(next);
						break;		
				}
				else {
					backwards.add(next);	
					first = next;
				}
			}
			else {
				break;
			}

		}	
		for (int i = backwards.size()-1; i > -1; i --) {
			seq.add(backwards.get(i));
		}
	
		return seq;
		
		}
	
	/**
	 * Returns the number of cards in the tableau cell
	 */
	public int size() {
		return cards.size();
	}
	
	/**
	 * Checks if a new card or card at the top of a stack can be added to itself
	 * @param Card c
	 * @return boolean
	 */
	public boolean canAddFrom(Cell fromPile) {
		//if stack is empty return true
		//else if card and ranks are valid return true
		//else return false
		
		//gets the top card in the pile moving cards from and top card of self
		Card fromTop = fromPile.peekTop();
		Card thisTop = this.peekTop();
		
		if (thisTop == null) {//added these two
			return true;
		}
		
		if (fromTop == null) {
			return false;
		}
		else {
			if (fromPile instanceof TableauCell) {
				ArrayList<Card> sequence = fromPile.getFinalSequence(thisTop);
				//System.out.println(sequence.size());
				fromTop = sequence.get(0);
			
			}
		
			if (fromTop.greaterByOne(thisTop) && ! thisTop.sameColor(fromTop)) {
				return true;
			}
		
			else {
				return false;
			}
		}
	}
	
	/**
	 * If fromPile is a foundation cell or free cell, try to add top card
	 * Else, get final alternating sequence from the tableau cell
	 * @param Cell fromPile
	 */
	public boolean addFrom(Cell fromPile) {
		
		ArrayList<Card> sequence = new ArrayList<Card>();
		Card card = null;
		Card topCard = this.peekTop();
		
		if (fromPile instanceof TableauCell) {
			sequence = fromPile.getFinalSequence(topCard);
			for (Card c : sequence) {
				cards.add(c);
				fromPile.remove(c);
			}
			return true;
		}
		else if (fromPile instanceof FreeCell) {
			card= fromPile.peekTop();
			cards.add(card);
			fromPile.remove(card);
			return true;
		}
		else {
			throw new IllegalArgumentException("Cannot remove from this stack");
		}
		
	}
	
	/**
	 * Checks first that the card specified to remove is in itself
	 * Then checks if each card after the specified card increases by rank and suit in 
	 * a logical order
	 * @param Card c
	 */
	public void remove(Card c) {
		cards.remove(c);
	}
	
	/**
	 * Checks card and potential following cards to see if they can be removed from the
	 * tableau cell
	 * @param Card c
	 * @return boolean representing if one can remove selected cards and potential
	 * following cards from the tableau cell
	 */
	
	public boolean isWon() {
		
		if (this.isEmpty()) {
			return true;	
		}
		Card first = cards.get(0);
		
		for (Card card : cards) {	
			if(first == card) {
			}
			else if (card.greaterByOne(first) && !first.sameColor(card)){
				first = card;
			}
			else {
				return false;
			}
		}
		
		return true;
	}


	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	public Card next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
