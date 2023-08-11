package edu.metrostate.ics372.p5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SideTest {
		
	@Test
	void testConstructor() {
		Side side2 = new Side(2);
		Pit[] testPits2 = side2.getPits();
		for(int i = 0; i < 6; i++) {
			assertEquals(2, testPits2[i].getNumberOfSeeds());
		}
		Side side4 = new Side(4);
		Pit[] testPits4 = side4.getPits();
		for(int i = 0; i < 6; i++) {
			assertEquals(4, testPits4[i].getNumberOfSeeds());
		}
	}
	
	@Test
	void testZeroSeedsConstructor() {
		Side side2 = new Side(0);
		Pit[] testPits2 = side2.getPits();
		for(int i = 0; i < 6; i++) {
			assertEquals(0, testPits2[i].getNumberOfSeeds());
		}
	}
	
	@Test
	void testGetPitNumbers() {
		Side sideL = new Side(1);
		Pit[] testPits = sideL.getPits();
		for(int i = 0; i < 6; i++) {
			assertEquals(i, testPits[i].getPitNumber());
		}
	}
	
	@Test
	void testGetPitObject() {
		Side side = new Side(1);
		Pit pit;
		for(int i = 0; i < 6; i++) {
			pit = side.getPit(i);
			assertEquals((i), pit.getPitNumber());
		}
	}
	
	@Test
	void testAddSeedsToStore() {
		Side side = new Side(1);
		// zero
		assertEquals(0, side.getStore().getNumberOfSeeds());
		// one
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.add(new Seed());
		side.addSeedsToStore(seedList);
		assertEquals(1, side.getStore().getNumberOfSeeds());
		// two
		seedList.add(new Seed());
		side.addSeedsToStore(seedList);
		assertEquals(2, side.getStore().getNumberOfSeeds());
		// many
		for(int i = 0; i < 20; i++){
			seedList.add(new Seed());
		}
		side.addSeedsToStore(seedList);
		assertEquals(22, side.getStore().getNumberOfSeeds());
	}
	
	@Test
	void testSetGetSide() {
		Side leftSide = new Side(1);
		Side rightSide = new Side(1);
		leftSide.setSide(SideEnum.Left);
		rightSide.setSide(SideEnum.Right);
		assertEquals(SideEnum.Left, leftSide.getSide());
		assertEquals(SideEnum.Right, rightSide.getSide());
	}
	
	@Test
	void testSetGetPlayer() {
		Side side = new Side(1);
		Player player = new Player(PlayerEnum.PlayerOne);
		side.setPlayer(player);
		assertEquals(PlayerEnum.PlayerOne, side.getPlayer().getEnum());
	}
	
	@Test
	void testAddOneSeedStartingAtPitZero() {
		Side sideL = new Side(1);
		Pit selection = new Pit(0);
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.add(new Seed());
		sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		assertEquals(2, pits[0].getNumberOfSeeds());
	}
	
	@Test
	void testAddOneSeedStartingAtPitFour() {
		Side sideL = new Side(1);
		Pit selection = new Pit(4);
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.add(new Seed());
		sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		assertEquals(2, pits[4].getNumberOfSeeds());
		for(int i = 0; i < 6; i++) {
			if(i != 4) assertEquals(1, pits[i].getNumberOfSeeds());
		}
	}
	
	@Test
	void testAddTwoSeedStartingAtPitOne() {
		Side sideL = new Side(1);
		Pit selection = new Pit(1);
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.add(new Seed());
		seedList.add(new Seed());
		sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		for(int i = 0; i < 6; i++) {
			if(i == 1) {
				assertEquals(2, pits[i].getNumberOfSeeds());
			} else if(i == 2) {
				assertEquals(2, pits[i].getNumberOfSeeds());
			}else {
				assertEquals(1, pits[i].getNumberOfSeeds());
			}
		}
	}
	
	@Test
	void testAddTwoSeedStartingAtPitFive() {
		Side sideL = new Side(1);
		Pit selection = new Pit(5);
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.add(new Seed());
		seedList.add(new Seed());
		sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		Pit store = sideL.getStore();
		assertEquals(2, pits[5].getNumberOfSeeds());
		assertEquals(1, store.getNumberOfSeeds());
		for(int i = 0; i < 5; i++) {
			assertEquals(1, pits[i].getNumberOfSeeds());
		}
	}
	
	@Test
	void testAddThreeSeedStartingAtPitFour() {
		Side sideL = new Side(1);
		Pit selection = new Pit(4);
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.add(new Seed());
		seedList.add(new Seed());
		seedList.add(new Seed());
		sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		Pit store = sideL.getStore();
		assertEquals(2, pits[4].getNumberOfSeeds());
		assertEquals(2, pits[5].getNumberOfSeeds());
		assertEquals(1, store.getNumberOfSeeds());
		for(int i = 0; i < 4; i++) {
			assertEquals(1, pits[i].getNumberOfSeeds());
		}
	}
		
	@Test
	void testAddSeedsToFivePitsStartingAtPitZero() {
		Side sideL = new Side(1);
		Pit selection = new Pit(0);
		ArrayList<Seed> seedList = new ArrayList<>();
		for(int i = 0; i < 5; i++) { 
			seedList.add(new Seed()); 
		}
		sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();		
		for(int i = 0; i < 5; i++) {
			assertEquals(2, pits[i].getNumberOfSeeds());
		}
		assertEquals(0, pits[6].getNumberOfSeeds());
	}
	
	@Test
	void testAddSixSeedsStartingAtPitZero() {
		Side sideL = new Side(1);
		Pit selection = new Pit(0);
		ArrayList<Seed> seedList = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			seedList.add(new Seed());
		}	
		ArrayList<Seed> seedsLeft = sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		for(int i = 0; i < 6; i++) {
			assertEquals(2, pits[i].getNumberOfSeeds());
		}
		Pit store = sideL.getStore();
		assertEquals(0, store.getNumberOfSeeds());
		assertEquals(0, seedsLeft.size());
	}
	
	@Test
	void testAddSevenSeedsStartingAtPitZero() {
		Side sideL = new Side(1);
		Pit selection = new Pit(0);
		ArrayList<Seed> seedList = new ArrayList<>();
		for(int i = 0; i < 7; i++) {
			seedList.add(new Seed());
		}	
		ArrayList<Seed> seedsLeft = sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		
		for(int i = 0; i < 5; i++) {
			assertEquals(2, pits[i].getNumberOfSeeds());
		}
		Pit store = sideL.getStore();
		assertEquals(1, store.getNumberOfSeeds());
		assertEquals(0, seedsLeft.size());
	}
	
	@Test
	void testAddSixSeedsStartingAtPitFive() {
		Side sideL = new Side(1);
		Pit selection = new Pit(5);
		ArrayList<Seed> seedList = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			seedList.add(new Seed());
		}	
		ArrayList<Seed> seedsLeft = sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		for(int i = 0; i < 4; i++) {
			assertEquals(1, pits[i].getNumberOfSeeds());
		}
		assertEquals(2, pits[5].getNumberOfSeeds());
		Pit store = sideL.getStore();
		assertEquals(1, store.getNumberOfSeeds());
		assertEquals(4, seedsLeft.size());// test 4 seeds are returned
	}
	
	@Test
	void testCheckSideEmpty() {
		Side sideL = new Side(1);
		assertFalse(sideL.sideIsEmpty());
		Pit[] pits = sideL.getPits();
		for(int i = 0; i < 6; i++) {
			pits[i].getSeeds();
		}
		assertTrue(sideL.sideIsEmpty());
	}

	@Test
	void testLastSeedIntoStore() {
		Side sideL = new Side(1);
		Pit selection = new Pit(5);
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.add(new Seed());
		seedList.add(new Seed());
		sideL.addSeeds(selection, seedList);
		Pit[] pits = sideL.getPits().clone();
		for(int i = 0; i < 4; i++) {
			assertEquals(1, pits[i].getNumberOfSeeds());
		}
		assertEquals(2, pits[5].getNumberOfSeeds());
		assertEquals(1, sideL.getStore().getNumberOfSeeds());
		assertTrue(sideL.checkLastSeedIntoStore());
	}
	
	@Test
	void testLastSeedIntoEmptyPit() {
		Side sideL = new Side(1);
		Pit[] pits = sideL.getPits().clone();
		for(int i = 0; i < 6; i++) {
			assertEquals(1, pits[i].getNumberOfSeeds());
		}
		pits[4].getSeeds();
		Pit selection = new Pit(4);
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.add(new Seed());
		sideL.addSeeds(selection, seedList);
		assertTrue(sideL.checkLastSeedIntoEmptyPit());
		Pit lastPit = sideL.getLastPit();
		assertEquals(4, lastPit.getPitNumber());
	}
	
}
