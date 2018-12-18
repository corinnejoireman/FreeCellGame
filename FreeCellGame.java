import java.util.*;

/**
 * Project 8
 * FreeCellGame.java
 * The model for the different cells, which creates the game
 * @author Rinn Joireman, Bethany Reitsma, George Barker, Pengrui Wang
 *
 */
public class FreeCellGame {

		private List<Cell> tableauCells = new ArrayList<Cell>();
		
		private List<Cell> freeCells = new ArrayList<Cell>();
		
		private List<Cell> foundationCells = new ArrayList<Cell>();
		
		private int count = 0;
		
		private int highCount;
	
		/**
		 * Instantiates FreeCellGame by creating new Cells and dealing cards to the cells
		 */
		public FreeCellGame(){
			for (int i = 1; i <= 8; i++) {
				tableauCells.add(new TableauCell());
			}
			for (int i = 1; i <= 4; i++) {
				freeCells.add(new FreeCell());
				foundationCells.add(new FoundationCell());			
			}
			this.newGame();
		}
	
		/**
		 * Shuffles the cards in the deck and then deals them to each tableau cell 
		 * until there are no more cards left in the deck
		 */
		public void newGame() {
				
			for ( Cell cell: tableauCells) {
				cell.clear();
			}
			for ( Cell cell: freeCells) {
				cell.clear();
			}
				
			for ( Cell cell: foundationCells) {
				cell.clear();
			}
				
			Deck deck = new Deck();
			deck.shuffle();
				
			for (Cell cell : tableauCells) {
				for (int i = 1; i <= 6; i++) {
					Card card = deck.deal();
					card.turn();
					cell.dealTo(card);
				}
			}
			for (int i = 0; i <= 3; i++) {
				Card card = deck.deal();
				card.turn();
				tableauCells.get(i).dealTo(card);
			}
			count = 0;
	}
		
		
		/**
		 * Takes the card(s) from the pile specified by the user and moves it(them) to the pile
		 * specified by the user
		 * @param Cell fromPile
		 * @param Cell toPile
		 * @param Card c 
		 */	
		public boolean makeMove(Cell fromPile, Cell toPile) {
			//declare parameters as Cell type
			//makes move if it can
			//if it can't it returns false
			
			if (fromPile.isEmpty()) {//added these lines
				return false;
			}
			if (toPile.canAddFrom(fromPile)){
				toPile.addFrom(fromPile);
				count++;
				return true;
			}
			else {
				return false;
			}
			
			
		}
		
		/**
		 * Counts the number of moves a player makes in the game
		 * @return
		 */
		public int getMoveCount() {
			return count;
		}
		
		/**
		 * Counts the number of moves a player makes in the game
		 * @return
		 */
		public int getHighMoveCount() {
			return highCount;
		}
		
		/**
		 * A method to get free cells of the game
		 * @return ArrayList<FreeCell> an array list of the free cells
		 */
		public Cell getFreeCell(int i){
			return freeCells.get(i);
		}
		
		/**
		 * A method to get Tableau cells of the game
		 * @return ArrayList<TableauCell> an array list of the Tableau cells
		 */
		public Cell getTableauCell(int i){
			return tableauCells.get(i);
		}
		
		/**
		 * A method to get foundation cells of the game
		 * @return ArrayList<FoundationCell> an array list of the foundation cells
		 */
		public Cell getFoundationCell(int i){
			return foundationCells.get(i);
		}
		
		
		/**
		 * If each pile in the tableau cell is ordered so that the each card can be played to its foundation cell,
		 * returns true, otherwise returns false
		 * @return true if the game can be won without the user taking more steps
		 */
		public boolean gameWon() {
			boolean isWon = true;
			for (Cell cell : tableauCells) {
				if (!cell.isWon()) {
					isWon = false;
				}
			}
			if (isWon) {
				if (highCount > count  || highCount == 0) {
					highCount = count;
				}
			}
			return isWon;
		}
		
		/**
		* If the free cells are full and there are no moves that can be made,
		* returns true, otherwise returns false
		* @return true if there are no more moves that can be made and the game cannot be won
		*/
		
		public boolean gameLost() {
			
			for (Cell cell : freeCells) {
				for (Cell c : foundationCells) {
					if (cell.canAddFrom(c)) {
						return false;
					}
				}
				if (cell.isEmpty()) {
					return false;
				}
			}
			for (Cell acell : tableauCells) {
				for (Cell ccell : freeCells) {
					if (acell.canAddFrom(ccell)){
						return false;
					}
				}
				for (Cell bcell: tableauCells) {
					if (acell.canAddFrom(bcell)) {
							return false;
					}
					else {
						for (Cell fcell : foundationCells) {
							if(fcell.canAddFrom(bcell)) {
								return false;
							}
						}
					}
				}
				
			}
			
		return true;
		}
	
		/**
		 * Looks at the free cells, tableau cells, and foundation cells, and determines whether
		 * there are any possible moves, telling you which card you should look at
		 * @return the top card of the pile that can be moved
		 */
		public String hint() {
			for (Cell cell : freeCells) {
				if (cell.isEmpty()) {
					return "A freeCell is empty!";
				}
				for (Cell c : foundationCells) {
					if (cell.canAddFrom(c)) {
						return "Look at the " + c.peekTop().toString();
					}
				}

			}
			for (Cell acell : tableauCells) {
				for (Cell ccell : freeCells) {
					if (acell.canAddFrom(ccell)){
						return "Look at the " + ccell.peekTop().toString();
					}
				}
				for (Cell bcell: tableauCells) {
					if (acell.canAddFrom(bcell)) {
						return "Look at the " + bcell.peekTop().toString();
					}
					else {
						for (Cell fcell : foundationCells) {
							if(fcell.canAddFrom(bcell)) {
								return "Look at the " + bcell.peekTop().toString();
							}
						}
					}
				}
			
			}
		
			return "There are no more possible moves";
		}
}
		