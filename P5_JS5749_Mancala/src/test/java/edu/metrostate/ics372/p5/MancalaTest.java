package edu.metrostate.ics372.p5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MancalaTest {
	
	@Test
	void testConstructor() {
		Mancala mancala = new Mancala(1);
		assertNotNull(mancala);
	}
	
	@Test
	void testTwoConstructors() {
		Mancala mancala1 = new Mancala(1);
		assertNotNull(mancala1);
		Mancala mancala2 = new Mancala(1);
		assertNotNull(mancala2);
	}
	
	@Test
	void testManyConstructors() {
		for(int i = 0; i < 10; i++) {
			assertNotNull(new Mancala(1));
		}
	}
	
	@Test
	void testZeroConstructorPits() {
		Mancala mancala = new Mancala(0);
		Player one = mancala.getPlayerOne();
		Player two = mancala.getPlayerTwo();
		for(int i = 0; i < 6; i++) {
			assertEquals(0, mancala.getPit(one, (i + 1)));
			assertEquals(0, mancala.getPit(two, (i + 7)));
		}
		mancala = new Mancala(0);
		for(int i = 0; i < 6; i++) {
			assertEquals(0, mancala.getPit(one, (i + 1)));
			assertEquals(0, mancala.getPit(two, (i + 7)));
		}
	}
	
	@Test
	void testConstructorPits() {
		Mancala mancala = new Mancala(4);
		Player one = mancala.getPlayerOne();
		Player two = mancala.getPlayerTwo();
		for(int i = 0; i < 6; i++) {
			assertEquals(4, mancala.getPit(one, (i + 1)));
			assertEquals(4, mancala.getPit(two, (i + 7)));
		}
		mancala = new Mancala(10);
		for(int i = 0; i < 6; i++) {
			assertEquals(10, mancala.getPit(one, (i + 1)));
			assertEquals(10, mancala.getPit(two, (i + 7)));
		}
	}
	
	@Test
	void testConstructorPitsTwo() {
		Mancala mancala = new Mancala(1);
		Player one = mancala.getPlayerOne();
		Player two = mancala.getPlayerTwo();
		for(int i = 0; i < 6; i++) {
			assertEquals(1, mancala.getPit(one, (i + 1)));
			assertEquals(1, mancala.getPit(two, (i + 7)));
		}
		mancala = new Mancala(100);
		for(int i = 0; i < 6; i++) {
			assertEquals(100, mancala.getPit(one, (i + 1)));
			assertEquals(100, mancala.getPit(two, (i + 7)));
		}
	}
	
	@Test
	void testGetCurrentPlayer() {
		Mancala mancala = new Mancala(4);
		Player player = mancala.getCurrentPlayer();
		assertEquals(PlayerEnum.PlayerOne, player.getEnum());
		mancala.selectPit(1);
		player = mancala.getCurrentPlayer();
		assertEquals(PlayerEnum.PlayerTwo, player.getEnum());
	}
	
	@Test
	void testGetStore() {
		Mancala mancala = new Mancala(1);
		assertEquals(0, mancala.getStore(mancala.getPlayerOne()));
		mancala.selectPit(6);
		assertEquals(1, mancala.getPit(mancala.getPlayerOne(), 1));
		assertEquals(1, mancala.getPit(mancala.getPlayerOne(), 2));
		assertEquals(1, mancala.getPit(mancala.getPlayerOne(), 3));
		assertEquals(1, mancala.getPit(mancala.getPlayerOne(), 4));
		assertEquals(1, mancala.getPit(mancala.getPlayerOne(), 5));
		assertEquals(0, mancala.getPit(mancala.getPlayerOne(), 6));
		assertEquals(1, mancala.getStore(mancala.getPlayerOne()));
	}
	
	@Test
	void testSelectGetPit() {
		Mancala mancala = new Mancala(5);
		mancala.selectPit(1);
		assertEquals(0, mancala.getPit(mancala.getPlayerOne(), 1));
		assertEquals(6, mancala.getPit(mancala.getPlayerOne(), 2));
		assertEquals(6, mancala.getPit(mancala.getPlayerOne(), 3));
		assertEquals(6, mancala.getPit(mancala.getPlayerOne(), 4));
		assertEquals(6, mancala.getPit(mancala.getPlayerOne(), 5));
		assertEquals(6, mancala.getPit(mancala.getPlayerOne(), 6));
		assertEquals(PlayerEnum.PlayerTwo, mancala.getCurrentPlayer().getEnum());
		mancala.selectPit(7);
		assertEquals(0, mancala.getPit(mancala.getPlayerTwo(), 7));
		assertEquals(6, mancala.getPit(mancala.getPlayerTwo(), 8));
		assertEquals(6, mancala.getPit(mancala.getPlayerTwo(), 9));
		assertEquals(6, mancala.getPit(mancala.getPlayerTwo(), 10));
		assertEquals(6, mancala.getPit(mancala.getPlayerTwo(), 11));
		assertEquals(6, mancala.getPit(mancala.getPlayerTwo(), 12));
	}
	
	@Test
	void testAnotherTurn() {
		Mancala mancala = new Mancala(1);
		assertFalse(mancala.anotherTurn());
		mancala.selectPit(6);
		assertTrue(mancala.anotherTurn());
		mancala.selectPit(1);
		assertFalse(mancala.anotherTurn());
	}
		
	@Test
	void testGameOver() {
		Mancala mancala = new Mancala(1);
		mancala.selectPit(1);
		mancala.selectPit(7);
		mancala.selectPit(2);
		mancala.selectPit(8);
		mancala.selectPit(3);
		mancala.selectPit(9);
		mancala.selectPit(4);
		mancala.selectPit(5);
		mancala.selectPit(7);
		assertFalse(mancala.gameOver());
		mancala.selectPit(6);
		assertTrue(mancala.gameOver());
	}
	
	@Test
	void testPlayerScores() {
		Mancala mancala = new Mancala(4);
		assertEquals(0, mancala.getPlayerOneScore());
		assertEquals(0, mancala.getPlayerTwoScore());
		mancala.selectPit(3);
		assertTrue(mancala.anotherTurn());
		mancala.selectPit(2);
		assertEquals(1, mancala.getPlayerOneScore());
		mancala.selectPit(9);
		assertEquals(1, mancala.getPlayerTwoScore());
	}
	
	@Test
	void testLastSeedInPlayerEmptyPit() {
		Mancala mancala = new Mancala(1);
		assertEquals(0, mancala.getPlayerOneScore());
		assertEquals(0, mancala.getPlayerTwoScore());
		mancala.selectPit(2);
		mancala.selectPit(8);
		mancala.selectPit(1);
		assertEquals(2, mancala.getPlayerOneScore());
		mancala.selectPit(7);
		assertEquals(2, mancala.getPlayerTwoScore());
	}
	
	@Test
	void testOppositePitEmptyAndLastSeedDroppedInEmptyPit() {
		Mancala mancala = new Mancala(1);
		assertEquals(0, mancala.getPlayerOneScore());
		assertEquals(0, mancala.getPlayerTwoScore());
		mancala.selectPit(2);
		mancala.selectPit(11);
		mancala.selectPit(1);
		assertEquals(1, mancala.getPlayerOneScore());
	}
	
	@Test
	void testGameOverAllSeedsMovedToStore() {
		Mancala mancala = new Mancala(1);
		assertEquals(0, mancala.getPlayerOneScore());
		assertEquals(0, mancala.getPlayerTwoScore());
		assertFalse(mancala.gameOver());
		mancala.selectPit(1);
		mancala.selectPit(7);
		mancala.selectPit(2);
		mancala.selectPit(9);
		mancala.selectPit(3);
		mancala.selectPit(8);
		mancala.selectPit(4);
		mancala.selectPit(5);
		mancala.selectPit(9);
		mancala.selectPit(6);
		assertTrue(mancala.gameOver());
		assertEquals(3, mancala.getPlayerOneScore());
		assertEquals(9, mancala.getPlayerTwoScore());
	}
	
}
