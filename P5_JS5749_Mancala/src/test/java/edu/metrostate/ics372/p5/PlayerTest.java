package edu.metrostate.ics372.p5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testConstructor() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		assertEquals(PlayerEnum.PlayerOne, playerOne.getEnum());
		assertEquals(PlayerEnum.PlayerTwo, playerTwo.getEnum());
	}
	
	@Test
	void testGetNullSideEnum() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		assertNull(playerOne.getSideEnum());
		assertNull(playerTwo.getSideEnum());
	}
	
	@Test
	void testGetSetSideEnum() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		playerOne.setSideEnum(SideEnum.Left);
		playerTwo.setSideEnum(SideEnum.Right);
		assertEquals(SideEnum.Left, playerOne.getSideEnum());
		assertEquals(SideEnum.Right, playerTwo.getSideEnum());
	}
	
}
