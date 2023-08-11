package edu.metrostate.ics372.p5;

import java.util.HashMap;
import java.util.Map;

public class Mancala {
	private Player playerOne;
	private Player playerTwo;
	private Board board;
	
	public Mancala(int numSeeds){
		this.playerOne = new Player(PlayerEnum.PlayerOne);
		this.playerTwo = new Player(PlayerEnum.PlayerTwo);
		this.board = new Board(numSeeds, playerOne, playerTwo);
		this.board.setCurrentPlayer(playerOne);
	}
	
	/**
	 * It will accept all legal integer values but will only return true if the integer
	 * number corresponds to the current player's pits and the pit is empty.
	 * pit 
	 * @param pit an integer value representing the pit number for selection.
	 * @return true if pit selected on current players side and is not empty. 
	 */
	public boolean selectPit(int pit) {
		boolean seedsAdded = true;
		board.setAnotherTurn(false);
				
		// check player picked their own pit and not opponent's pit number
		Player currentPlayer = board.getCurrentPlayer();
		SideEnum side = currentPlayer.getSideEnum();
		SideEnum pitSide = getSideFromPitNumber(pit);
		if(side != pitSide) return false;
		
		seedsAdded = board.selectPit(pit);
		
		// switch players if not another turn or seedsAdded is true
		if(seedsAdded && !board.getAnotherTurn()) {
			board.setCurrentPlayer(getOppositePlayer());
		}
		
		return seedsAdded; 
	}
	
	/**
	 * @return Player object associated with taking the first turn
	 */
	public Player getPlayerOne() {
		return playerOne;
	}
	
	/**
	 * @return Player object associated with taking the second turn
	 */
	public Player getPlayerTwo() {
		return playerTwo;
	}
	
	/**
	 * @return returns Player object representing the current player
	 */
	public Player getCurrentPlayer() {
		return board.getCurrentPlayer();
	}
	
	/**
	 * @return boolean true if current player gets another turn
	 */
	public boolean anotherTurn() {
		return board.getAnotherTurn();
	}
	
	/**
	 * @param player Player object associated with the store 
	 * @return the store of the player passed
	 */
	public int getStore(Player player) {
		return (player.getEnum() == PlayerEnum.PlayerOne) ? getPlayerOneScore() : getPlayerTwoScore();
	}
	
	/**
	 * 
	 * @param player Player object associated with the pit requested
	 * @param pit an integer representing the pit 
	 * @return an integer representing the number of Seeds in the pit
	 * @precondition pit must be between 1 and 12 inclusive 
	 */
	public int getPit(Player player, int pit) {
		if(pit < 1 || pit > 12) throw new IllegalArgumentException();
		Side side = getSideFromPlayer(player);
		if(side.getSide() == SideEnum.Left) {
			return side.getPit(pit - 1).getNumberOfSeeds();
		}
		return side.getPit(pit - 7).getNumberOfSeeds();
		
	}
	
	// private method: no Javadoc required
	private Side getSideFromPlayer(Player player) {
		Side side = (player.getSideEnum() == SideEnum.Left) ? board.getLeftSide() : board.getRightSide();
		return side;
	}
	
	/**
	 * @return true if game is over
	 */
	public boolean gameOver() {
		boolean gameOver = false;
		if(board.checkGameOver()) {
			gameOver = true;
			// move rest of seeds into store
			Side side = (board.getLeftSide().sideIsEmpty()) ? board.getRightSide() : board.getLeftSide();
			Pit[] pits = side.getPits();
			for(int i = 0; i < 6; i++) {
				side.addSeedsToStore(pits[i].getSeeds());
			}
		}
		return gameOver;
	}
	
	// private method: no Javadoc required
	private SideEnum getSideFromPitNumber(int pitNum) {
		Map<Integer, SideEnum> map = new HashMap<>();
		for(int i = 1; i < 7; i++) {
			map.put(i, SideEnum.Left);
		}
		for(int i = 7; i < 13; i++) {
			map.put(i, SideEnum.Right);
		}
		return map.get(pitNum);
	}
	
	// private method: no Javadoc required
	private Player getOppositePlayer() {
		Player oppositePlayer = (getCurrentPlayer().getEnum() == PlayerEnum.PlayerOne) ? playerTwo : playerOne;
		return oppositePlayer;
	}
	
	/**
	 * once the system determines the game is over, the remaining Seeds are placed
	 * in the store of the non-empty side player
	 * @return an integer representing the number of Seeds in Player One's store
	 */
	public int getPlayerOneScore() {
		return board.getLeftSide().getStore().getNumberOfSeeds();
	}
	
	/**
	 * once the system determines the game is over, the remaining Seeds are placed
	 * in the store of the non-empty side player. 
	 * @return an integer representing the number of Seeds in Player Two's store
	 */
	public int getPlayerTwoScore() {
		return board.getRightSide().getStore().getNumberOfSeeds();
	}
	
	/**
	 * @return a String displaying each pit and store representing the Mancala board
	 */
	public String toString() {
		String leftOutput = "";
		String rightOutput = "";
		Pit[] leftPits = board.getLeftSide().getPits();
		Pit[] rightPits = board.getRightSide().getPits();
		for(int i = 0; i < 6; i++) {
			leftOutput += "Pit " + (i+1) + " [";
			leftOutput += leftPits[i].getNumberOfSeeds();
			leftOutput += "]\t";
		}
		for(int i = 12; i > 6; i--) {
			rightOutput += "Pit " + (i) + " [";
			rightOutput += rightPits[i - 7].getNumberOfSeeds();
			rightOutput +="]\t";
		}
		String leftStore = "Store [" + board.getLeftSide().getStore().getNumberOfSeeds() + "]";
		String rightStore = "Store [" + board.getRightSide().getStore().getNumberOfSeeds() + "]\t";
		return rightStore + rightOutput + "\n\t\t" + leftOutput + leftStore;
	}
	
}
