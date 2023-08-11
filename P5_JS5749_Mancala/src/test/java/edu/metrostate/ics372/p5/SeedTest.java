package edu.metrostate.ics372.p5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SeedTest {

	@Test
	void testOneSeed() {
		Seed seed = new Seed();
		assertNotNull(seed);
	}
	
	@Test
	void testTwoSeeds() {
		Seed one = new Seed();
		Seed two = new Seed();
		Seed[] seedArr = {one, two};
		assertEquals(2, seedArr.length);
	}
	
	@Test
	void testThreeSeeds() {
		Seed one = new Seed();
		Seed two = new Seed();
		Seed three = new Seed();
		Seed[] seedArr = {one, two, three};
		assertEquals(3, seedArr.length);
	}
	
	@Test
	void testTenSeeds() {
		Seed[] seedArr = new Seed[10];
		for(int i = 0; i < 10; i++) {
			seedArr[i] = new Seed();
		}
		assertEquals(10, seedArr.length);
	}
	
	@Test
	void testManySeeds() {
		Seed[] seedArr = new Seed[100];
		for(int i = 0; i < 100; i++) {
			seedArr[i] = new Seed();
		}
		assertEquals(100, seedArr.length);
	}
	
}
