package edu.metrostate.ics372.p5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
		
	private Side leftSide;
	private Side rightSide;
	private Player currentPlayer;
	private boolean anotherTurn;
	
	/**
	 * Constructor to create an object representing the Mancala board. The board
	 * consist of 12 pits and two stores. Passing two Player objects and an integer 
	 * specifying the initial number Seeds in each pit is required for the construction of the board.
	 * @param init an integer representing the number of initial Seeds in each pit at start of game
	 * @param playerOne a Player object representing player one 
	 * @param playerTwo a Player object representing player two
	 * @precondition both players cannot use the same Player object or Players have the same PlayerEnum
	 */
	public Board(int init, Player playerOne, Player playerTwo) {
		if(playerOne.getEnum() == playerTwo.getEnum()) throw new IllegalArgumentException();
		 this.leftSide = new Side(init);
		 this.rightSide = new Side(init);
		initializeSides(playerOne, playerTwo);
	}
	
	// private method: no Javadoc required
	private void initializeSides(Player playerOne, Player playerTwo) {
		this.leftSide.setSide(SideEnum.Left);
		Player leftPlayer = playerOne;
		leftPlayer.setSideEnum(SideEnum.Left);
		this.leftSide.setPlayer(leftPlayer);
		this.leftSide.getStore().setSide(SideEnum.Left);
		
		this.rightSide.setSide(SideEnum.Right);
		Player rightPlayer = playerTwo;
		rightPlayer.setSideEnum(SideEnum.Right);
		this.rightSide.setPlayer(rightPlayer);
		this.rightSide.getStore().setSide(SideEnum.Right);
		this.setPitSides();
	}
	
	// private method: no Javadoc required 
	private void setPitSides() {
		Pit[] leftPits = leftSide.getPits();
		Pit[] rightPits = rightSide.getPits();
		for(int i = 0; i < 6; i++) {
			leftPits[i].setSide(leftSide.getSide());
			rightPits[i].setSide(rightSide.getSide());
		}
	}
	
	/**
	 * contains pits 1-6
	 * @return Side object representing the side for Player One
	 */
	public Side getLeftSide() {
		return leftSide;
	}
	
	/**
	 * contains pits 7-12
	 * @return Side object representing the side for Player Two
	 */
	public Side getRightSide() {
		return rightSide;
	}
	
	/**
	 * the pit number represents the pit selected by the Player to remove the Seeds
	 * and the selectPit method then adds all seeds from the selected pit into the 
	 * following. 
	 * @param pitNum an integer representing the pit selected by the current Player 
	 * @return true if operation was completed
	 * @precondition pitNum must be between 1 and 12 inclusive, pit number is on
	 * current Player's side, and pit is not empty 
	 */
	public boolean selectPit(int pitNum) {
		if(pitNum < 1 || pitNum > 12) throw new IllegalArgumentException();
		Side side = getSideFromPitNum(pitNum); // get side from pit number
		int pitNumber = convertPitNum(pitNum); // convert from 1-12 to 0-5
		Pit selection = side.getPit(pitNumber); // return pit from converted pit number
		if(selection.isEmpty()) return false; // if selected pit is empty return false
		ArrayList<Seed> seeds = selection.getSeeds(); // get seeds from selected pit
		this.addSeeds(side, selection, seeds);
		return true;
	}
	
	// private method: no Javadoc required
	private Side getSideFromPitNum(int pitNum) {
		return (pitNum < 7) ? leftSide : rightSide;
	}
	
	// private method: no Javadoc required 
	private int convertPitNum(int pitNum) {
		Map<Integer, Integer> map = new HashMap<>();
		int key = 1;
		for(int i = 0; i < 6; i++) {
			map.put(key, i);
			map.put(key + 6, i);
			key++;
		}
		return map.get(pitNum);
	}
	
	// private method: no Javadoc required 
	private void addSeeds(Side side, Pit selection,  ArrayList<Seed> seeds) {
		
		// add seed to next pit 
		Pit nextPit = side.getPit(selection.getPitNumber()+ 1);
		ArrayList<Seed> seedList = side.addSeeds(nextPit, seeds);
		
		while(!seedList.isEmpty()) {
			side = (side.getSide() == SideEnum.Left) ? rightSide : leftSide;	
			seedList = side.addSeeds(side.getPit(0), seedList);			
		}
		
		// check if last seed was drop in current player's store 
		if(side.checkLastSeedIntoStore() && (side.getPlayer() == currentPlayer)){
			setAnotherTurn(true);
		}

		// check if last seed was dropped in empty pit on current player's side
		if(side.checkLastSeedIntoEmptyPit() && side.getPlayer() == currentPlayer ) {
			// move last seed to player's store and opposite side pit to player's store 
			Pit lastPit = side.getLastPit();
			Pit oppositePit = this.getOppositePit(lastPit);
			ArrayList<Seed> lastPitSeeds = lastPit.getSeeds();
			if(oppositePit != null) {
				ArrayList<Seed> oppositePitSeeds = oppositePit.getSeeds();
				side.addSeedsToStore(oppositePitSeeds);
			}
			side.addSeedsToStore(lastPitSeeds);
		}
	}
	
	// private method: no Javadoc required 
	private Pit getOppositePit(Pit pit) {
		Map<Pit, Pit> map = new HashMap<>();
		SideEnum side = pit.getSide();
		Side oppositeSide = (side == SideEnum.Left) ? rightSide : leftSide;
		Pit[] currentPits = (side == SideEnum.Left) ? leftSide.getPits() : rightSide.getPits();
		Pit[] oppositePits = oppositeSide.getPits();
		for(int i = 0; i < 6; i++) {
			map.put(currentPits[i],  oppositePits[5 - i]);
		}
		return map.get(pit);
	}
	
	/**
	 * @return true if either Side contains no Seeds (excluding stores)
	 */
	public boolean checkGameOver() {
		if(leftSide.sideIsEmpty() || rightSide.sideIsEmpty()) return true;
		return false;
	}
	
	/**
	 * @return a Player object representing the current Player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * @param currentPlayer a Player object representing the current Player
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	/**
	 * 
	 * @param another boolean true if current player gets another turn
	 */
	public void setAnotherTurn(boolean another) {
		this.anotherTurn = another;
	}
	
	/**
	 * 
	 * @return boolean true if current player gets another turn
	 */
	public boolean getAnotherTurn() {
		return anotherTurn;
	}
		
}
