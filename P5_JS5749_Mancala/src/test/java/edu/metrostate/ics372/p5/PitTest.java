package edu.metrostate.ics372.p5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PitTest {

	@Test
	void testIllegalArgumentPitNumberZero() {
		assertThrows(IllegalArgumentException.class, () -> {
					new Pit(-1);
				});
	}
	
	@Test
	void testIllegalArgumentPitNumberThirteen() {
		assertThrows(IllegalArgumentException.class, () -> {
					new Pit(13);
				});
	}
	
	@Test
	void testConstructor() {
		for(int i = 0; i < 6; i++) {
			assertEquals(i, new Pit(i).getPitNumber());
		}
	}
	
	@Test
	void testAddOneSeeds() {
		Pit pit = new Pit(1);
		pit.addSeed(new Seed());
		assertEquals(1, pit.getNumberOfSeeds());
	}
	
	@Test
	void testAddTwoSeeds() {
		Pit pit = new Pit(1);
		pit.addSeed(new Seed());
		pit.addSeed(new Seed());
		assertEquals(2, pit.getNumberOfSeeds());
	}
	
	@Test
	void testAddManySeeds() {
		Pit pit = new Pit(1);
		for(int i = 0; i < 10; i++) {
			pit.addSeed(new Seed());
		}
		assertEquals(10, pit.getNumberOfSeeds());
	}
	
	@Test
	void testGetOneSeed() {
		Pit pit = new Pit(1);
		pit.addSeed(new Seed());
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.addAll(pit.getSeeds());
		assertEquals(0, pit.getNumberOfSeeds());
		assertEquals(1, seedList.size());
	}
	
	@Test
	void testGetSeeds() {
		Pit pit = new Pit(1);
		for(int i = 0; i < 15; i++) {
			pit.addSeed(new Seed());
		}
		ArrayList<Seed> seedList = new ArrayList<>();
		seedList.addAll(pit.getSeeds());
		assertEquals(0, pit.getNumberOfSeeds());
		assertEquals(15, seedList.size());
	}
	
	@Test
	void testGetPitNumber() {
		Pit pit1 = new Pit(1);
		Pit pit2 = new Pit(2);
		assertEquals(1, pit1.getPitNumber());
		assertEquals(2, pit2.getPitNumber());
	}
	
	@Test
	void testGetNumberOfSeeds() {
		Pit pit = new Pit(1);
		pit.addSeed(new Seed());
		assertEquals(1, pit.getNumberOfSeeds());
		for(int i = 0; i < 20; i++) {
			pit.addSeed(new Seed());
		}
		assertEquals(21, pit.getNumberOfSeeds());
	}
	
	@Test
	void testGetSetSide() {
		Pit pit = new Pit(1);
		pit.setSide(SideEnum.Left);
		assertEquals(SideEnum.Left, pit.getSide());
	}
		
	@Test
	void testIsEmpty() {
		Pit pit = new Pit(1);
		assertTrue(pit.isEmpty());
	}
	
}
