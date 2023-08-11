package edu.metrostate.ics372.p5;

public class Player {
	
	private PlayerEnum num;
	private SideEnum sideEnum;
	
	/**
	 * PlayerEnum.PlayerOne is the player that goes first,
	 * Playerenum.PlayerTwo is the second player
	 * @param num PlayerEnum representing the first or second player
	 */
	public Player(PlayerEnum num) {
		this.num = num;
	}

	/**
	 * @return a PlayerEnum of either PlayerEnum.PlayerOne or PlayerEnum.PlayerTwo
	 */
	public PlayerEnum getEnum() {
		return num;
	}
	
	/**
	 * SideEnum.Left is associated with Player One,
	 * SideEnum.Right is associated with Player Two
	 * @return a SideEnum associated with the Player
	 */
	public SideEnum getSideEnum() {
		return sideEnum;
	}
	
	/**
	 * SideEnum.Left is associated with Player One,
	 * SideEnum.Right is associated with Player Two
	 * @param sideEnum a SideEnum to connect either SideEnum.Left or SideEnum.Right
	 * with a Player
	 */
	public void setSideEnum(SideEnum sideEnum) {
		this.sideEnum = sideEnum;
	}

}
