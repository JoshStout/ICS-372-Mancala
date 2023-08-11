package edu.metrostate.ics372.p5;

import java.util.ArrayList;

public class Pit {
	private ArrayList<Seed> seedList;
	private int num;
	private SideEnum side;
	
	/**
	 * @param num an integer representing the pit number on one side of the board
	 * @precondition must be between 0 and 6 inclusive
	 */
	public Pit(int num) {
		if(num > 6 || num < 0) throw new IllegalArgumentException();
		this.num = num;
		seedList = new ArrayList<>();
	}
	
	/**
	 * @param seed the seed object to be added to the pit
	 */
	public void addSeed(Seed seed) {
		seedList.add(seed);
	}

	/**
	 * method takes all seeds in pit and return an 
	 * ArrayList of seed objects that were in the pit
	 * @return an ArrayList of seed objects
	 */
	public ArrayList<Seed> getSeeds() {
		ArrayList<Seed> seeds = new ArrayList<>();
		seeds.addAll(seedList);
		seedList.clear(); 
		return seeds;
	}
	
	/**
	 * All pit numbers within Side class are between 1-6 inclusive
	 * @return an integer representing the pit number
	 */
	public int getPitNumber() {
		return num;
	}
	
	/**
	 * @return an integer representing the number of seeds currently in the pit
	 */
	public int getNumberOfSeeds() {
		return seedList.size();
	}
	
	/**
	 * SideEnum.Left represents Player One side
	 * SideEnum.Right represents Player Two side
	 * @param side SideEnum representing the side of the Mancala board
	 * the pit belongs to
	 */
	public void setSide(SideEnum side) {
		this.side = side;
	}
	
	/**
	 * SideEnum.Left represents Player One side
	 * SideEnum.Right represents Player Two side
	 * @return a SideEnum representing the side of the Mancala board
	 * the pit belongs to
	 */
	public SideEnum getSide() {
		return side;
	}
	
	/**
	 * @return returns true of the pit contains no Seed objects
	 */
	public boolean isEmpty() {
		return getNumberOfSeeds() == 0;
	}
	
}
