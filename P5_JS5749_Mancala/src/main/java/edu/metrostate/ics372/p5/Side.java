package edu.metrostate.ics372.p5;

import java.util.ArrayList;

public class Side {
	private Pit[] pits;
	private SideEnum side;
	private Player player;
	private boolean lastSeedIntoStore;
	private boolean lastSeedIntoEmptyPit;
	private Pit lastPit;
		
	/**
	 * creates a Side object with 7 pits, the last pit representing the store
	 * @param init an integer representing number of seeds to be placed in each 
	 * pit when side is created
	 */
	public Side(int init) {
		this.pits = new Pit[7];
		createPits(init);
	}
	
	// private method: no Javadoc required 
	private void createPits(int init) {
		for(int i = 0; i < 7; i++) {
			pits[i] = new Pit(i);
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < init; j++) {
				pits[i].addSeed(new Seed());
			}
		}
	}
		
	/**
	 * @return an array of Pit objects belonging to the side
	 */
	public Pit[] getPits() {
		return pits; 
	}
	
	/**
	 * method to get a specific Pit on the side of the board.
	 * @param pitNum an integer representing the pit number
	 * @return a Pit object specified by the pit number argument passed
	 * @precondition 0-6 inclusive
	 */
	public Pit getPit(int pitNum) {
		if(pitNum < 0 || pitNum > 6) throw new IllegalArgumentException();
		return pits[pitNum];
	}
	
	/**
	 * method to add seeds to the store
	 * @param seedList an ArrayList of Seed objects to be added to the store
	 */
	public void addSeedsToStore(ArrayList<Seed> seedList) {
		while(!seedList.isEmpty()) {
			pits[6].addSeed(seedList.remove(0));
		}
	}
	
	/*
	 * @return the Pit object representing the store
	 */
	public Pit getStore() {
		return pits[6];
	}
		
	/**
	 * SideEnum.Left belongs to Player One
	 * SideEnum.Right belongs to Player Two
	 * @return a SideEnum representing the side of the Mancala board the 
	 * Side object belongs to.
	 */
	public SideEnum getSide() {
		return side;
	}
	
	/**
	 * SideEnum.Left belongs to Player One
	 * SideEnum.Right belongs to Player Two
	 * @param side sets what side of the board the Side instance belongs to
	 */
	public void setSide(SideEnum side) {
		this.side = side;
	}
	
	/**
	 * get the Player assigned to the Side object
	 * @return the Player the Side instance belongs to
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * assign the Side object to the Player
	 * @param player the Player the Side instance belongs to
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Seed objects are only added to Pits on instance Side and returns whatever
	 * Seeds are left in the ArrayLists originally past to the method.
	 * @param pit a Pit object representing the first Pit to began adding Seeds
	 * @param seedList an ArrayList of Seed objects to be added to the Pits on the
	 * Side instance 
	 * @return any seeds left over after Seeds are added to the Pits
	 */
	public ArrayList<Seed> addSeeds(Pit pit, ArrayList<Seed> seedList) {
		this.setLastSeedIntoStore(false);
		this.setLastSeedIntoEmptyPit(false);
		int currentPit = pit.getPitNumber();
		while(!seedList.isEmpty() && currentPit < 7) {
			
			// check if last seed dropped is in an empty pit
			if(seedList.size() == 1 && pits[currentPit].isEmpty()) {
				this.setLastSeedIntoEmptyPit(true);
				this.setLastPit(pits[currentPit]);
			}
						
			// check if last seed dropped is in Store
			if(seedList.size() == 1 && currentPit == 6) {
				this.setLastSeedIntoStore(true);
			}
			
			pits[currentPit].addSeed(seedList.remove(0));
			currentPit++;
		}
		return seedList;
	}
	
	/**
	 * @return true if all Pits are empty
	 */
	public boolean sideIsEmpty() {
		for(int i = 0; i < 6; i++) {
			if(pits[i].getNumberOfSeeds() > 0) return false;
		}
		return true;
	}
	
	/**
	 * @return true if the last Seed object was dropped in the store
	 */
	public boolean checkLastSeedIntoStore() {
		return lastSeedIntoStore;
	}
	
	// private method: no Javadoc required 
	private void setLastSeedIntoStore(boolean lastSeedIntoStore) {
		this.lastSeedIntoStore = lastSeedIntoStore;
	}
	/**
	 * @return true if last Seed added was in an empty Pit
	 */
	public boolean checkLastSeedIntoEmptyPit() {
		return lastSeedIntoEmptyPit;
	}
	
	// private method: no Javadoc required
	private void setLastSeedIntoEmptyPit(boolean lastSeedIntoEmptyPit) {
		this.lastSeedIntoEmptyPit = lastSeedIntoEmptyPit;
	}
	
	// private method: no Javadoc required
	private void setLastPit(Pit lastPit) {
		this.lastPit = lastPit;
	}
	
	/**
	 * @return the Pit that received the last Seed object
	 */
	public Pit getLastPit() {
		return lastPit;
	}
	
}


