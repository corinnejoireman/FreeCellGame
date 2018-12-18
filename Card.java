import javax.swing.*;

/**
 * Project 8
 * Card.java
 * Represents a playing card with a suit,
 * rank, image, and face up status.
 * @author lambertk, Bethany, Rinn, George, Pengrui
 *
 */
public class Card implements Comparable<Card>{

    private Suit suit;
    private int rank;
    private boolean faceUp;
    private Icon image;
    private static Icon CARD_BACK;

    /**
     * Constructor.
     * @param suit the card's suit
     * @param rank the card's rank
     */
    public Card(Suit suit, int rank){
    	this.suit = suit;
    	this.rank = rank;
    	faceUp = false;
    	image = getImageFromFile(rank, suit);
    	if (CARD_BACK == null)
    		CARD_BACK = getBackFromFile();
    }

    /**
     * Returns the card's face image if its face is up or its back side image otherwise.
     * @return the card's face image or the back side image
     */
    public Icon getImage(){
    	if (faceUp)
    	    return image;
    	else
    	    return CARD_BACK;
    }

    /**
     * Returns the back side image of a card.
     * @return the back side image of a card
     */
    public static Icon getBack(){
    	if (CARD_BACK == null)
    	    new Card(Suit.spade, 1);
    	return CARD_BACK;
    }

    /**
     * Turns the card over, negating its face up status.
     */
    public void turn(){
    	faceUp = ! faceUp;
    }

    private Icon getImageFromFile(int rank, Suit suit){
    	String fileName = "DECK/";
    	fileName += rank;
    	fileName += Character.toUpperCase(suit.toString().charAt(0));
    	fileName += ".GIF";
    	return new ImageIcon(getClass().getResource(fileName));
    }

    private Icon getBackFromFile(){
    	String fileName = "DECK/CARDBACK.GIF";
    	return new ImageIcon(getClass().getResource(fileName));
    }

    /**
     * Returns the card's face up status.
     * @return true if face up or false otherwise
     */
    public boolean isFaceUp(){
       return faceUp;
    }

    /**
     * Returns the card's suit.
     * @return the card's suit
     */
    public Suit getSuit(){
        return suit;
    }

    /**
     * Returns the card's rank
     * @return the card's rank
     */
    public int getRank(){
        return rank;
    }

    /**
     * Compares two cards with respect to rank
     * @return 0 if equal, less than 0 if less, greater than 0 if greater
     */
    public int compareTo(Card other){
        return this.rank - other.rank;
    }

    /**
     * Compares the two cards to see if they are the same color
     * @param c
     * @return true if they are the same color, and false otherwise
     */
    public boolean sameColor(Card c) {
    	Suit suit1 = c.getSuit();
    	Suit suit2 = this.getSuit();
    	if (suit1 == suit2) {
    		return true;
    	}
    	else if (suit1.toString() == "diamonds" && suit2.toString() == "hearts"){
    		return true;
    	}
    	else if (suit1.toString() == "hearts" && suit2.toString() == "diamonds"){
    		return true;
    	}
    	else if (suit1.toString() == "spades" && suit2.toString() == "clubs") {
    		return true;
    	}
    	else if (suit1.toString() == "clubs" && suit2.toString() == "spades") {
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    /**
     * Checks to see if card passed in is greater than self by one
     * @return true if the card is greater than one, or false otherwise
     */
    public boolean greaterByOne(Card c) {
    	if (this.getRank() == c.getRank()-1) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Returns the string representation of the card (rank of suit)
     * @return the string representation of the card
     */
    public String toString(){
        return rankToString(rank) + " of " + suit;
    }

    static private String rankToString(int rank){
        if (rank >= 2 && rank <= 10) return rank + "";
        else if (rank == 11) return "Jack";
        else if (rank == 12) return "Queen";
        else if (rank == 13) return "King";
        else return "Ace";
    }
}
