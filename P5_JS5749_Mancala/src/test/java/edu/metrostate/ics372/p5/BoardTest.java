package edu.metrostate.ics372.p5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void testConstructor() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(1, playerOne, playerTwo);
		assertNotNull(board.getLeftSide());
		assertNotNull(board.getRightSide());
	}
	
	@Test
	void testZeroInitConstructor() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(0, playerOne, playerTwo);
		Pit[] leftPit = board.getLeftSide().getPits();
		Pit[] rightPit = board.getRightSide().getPits();
		for(int i = 1; i < 4; i++) {
			assertEquals(0, leftPit[i].getNumberOfSeeds());
			assertEquals(0, rightPit[i].getNumberOfSeeds());
		}
	}
	
	@Test
	void testFourSeedsConstructor() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(4, playerOne, playerTwo);
		Pit[] leftPit = board.getLeftSide().getPits();
		Pit[] rightPit = board.getRightSide().getPits();
		for(int i = 1; i < 4; i++) {
			assertEquals(4, leftPit[i].getNumberOfSeeds());
			assertEquals(4, rightPit[i].getNumberOfSeeds());
		}
	}
	
	@Test
	void testSamePlayerConstructor() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		assertThrows(IllegalArgumentException.class, () -> {
			new Board(4, playerOne, playerOne);;
		});
	}
	
	@Test
	void testSamePlayerEnumConstructor() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerOne);
		assertThrows(IllegalArgumentException.class, () -> {
			new Board(4, playerOne, playerTwo);;
		});
		
	}
	
	@Test
	void testGetSides() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(4, playerOne, playerTwo);
		Side leftSide = board.getLeftSide();
		assertEquals(SideEnum.Left, leftSide.getSide());
		for(int i = 0; i < 6; i++) {
			Pit pitL = leftSide.getPit(i);
			assertEquals(4, pitL.getNumberOfSeeds());
		}
		Side rightSide = board.getRightSide();
		assertEquals(SideEnum.Right, rightSide.getSide());
		for(int i = 0; i < 6; i++) {
			Pit pitR = rightSide.getPit(i);
			assertEquals(4, pitR.getNumberOfSeeds());
		}
	}
		
	@Test 
	void testSelectIllegalPitNumber() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(4, playerOne, playerTwo);
		assertThrows(IllegalArgumentException.class, () -> {
					board.selectPit(-1);
				});
		assertThrows(IllegalArgumentException.class, () -> {
					board.selectPit(13);
				});
	}
	
	@Test
	void testSelectEmptyPit() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(1, playerOne, playerTwo);
		board.selectPit(1);
		assertFalse(board.selectPit(1));
	}
	
	@Test
	void testSelectPitOne() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(1, playerOne, playerTwo);
		board.selectPit(1);
		Side left = board.getLeftSide();
		Side right = board.getRightSide();
		Pit[] leftPits = left.getPits();
		Pit[] rightPits = right.getPits();
		assertEquals(0, leftPits[0].getNumberOfSeeds());
		assertEquals(2, leftPits[1].getNumberOfSeeds());
		for(int i = 2; i < 6; i++) {
			assertEquals(1, leftPits[i].getNumberOfSeeds());
		}
		for(int i = 0; i < 6; i++) {
			assertEquals(1, rightPits[i].getNumberOfSeeds());
		}
	}
	
	@Test
	void testManySelections() {
		
	}
	
	@Test
	void testCheckGameOver() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(1, playerOne, playerTwo);
		assertFalse(board.checkGameOver());
		for(int i = 1; i < 7; i++) {
			board.selectPit(i);
		}
		Side left = board.getLeftSide();
		Pit[] leftPits = left.getPits();
		for(int i = 0; i < 6; i++) {
			assertEquals(0, leftPits[i].getNumberOfSeeds());
		}
		assertTrue(board.checkGameOver());
		
	}
	
	@Test
	void testGetSetCurrentPlayer() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(1, playerOne, playerTwo);
		board.setCurrentPlayer(playerTwo);
		assertEquals(playerTwo, board.getCurrentPlayer());
	}
	
	@Test
	void testGetSetAnotherTurn() {
		Player playerOne = new Player(PlayerEnum.PlayerOne);
		Player playerTwo = new Player(PlayerEnum.PlayerTwo);
		Board board = new Board(1, playerOne, playerTwo);
		assertFalse(board.getAnotherTurn());
		board.setAnotherTurn(true);
		assertTrue(board.getAnotherTurn());
	}		

}
