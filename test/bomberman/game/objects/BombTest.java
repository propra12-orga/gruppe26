package bomberman.game.objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class BombTest {

	@Test
	public void testBomb() {
		final Bomb track = new Bomb(5, 10, 5);
		assertEquals(track.getPosX(), 5);
		assertEquals(track.getPosY(), 10);
		assertEquals(track.getTimer(), 5);

		track.tick();
		assertTrue(track.isStillThere());
		assertEquals(track.getTimer(), 4);

		track.tick();
		assertTrue(track.isStillThere());
		assertEquals(track.getTimer(), 3);

		track.tick();
		assertTrue(track.isStillThere());
		assertEquals(track.getTimer(), 2);

		track.tick();
		assertTrue(track.isStillThere());
		assertEquals(track.getTimer(), 1);

		track.tick();
		assertFalse(track.isStillThere());
		assertEquals(track.getTimer(), 0);

		assertEquals(track.getPosX(), 5);
		assertEquals(track.getPosY(), 10);
	}

	@Test
	public void testBomb2() {
		final Bomb track = new Bomb(2, 2, 2);
		track.tick();
		assertTrue(track.isStillThere());
		track.tick();
		assertFalse(track.isStillThere());
		track.tick();
		assertFalse(track.isStillThere());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailConstructorBomb() {
		new Bomb(2, 2, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailConstructorBomb2() {
		new Bomb(-1, 2, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailConstructorBomb3() {
		new Bomb(2, -2, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailConstructorBomb4() {
		new Bomb(2, 2, -1);
	}

}
