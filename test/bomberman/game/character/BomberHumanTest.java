package bomberman.game.character;

import static org.junit.Assert.*;

import org.junit.Test;

public class BomberHumanTest {

	@Test
	public void testConstructor() {
		final BomberHuman b = new BomberHuman(true, 10, 12, 1);
		assertTrue(b.isHuman());
		assertEquals(b.getPosX(), 10);
		assertEquals(b.getPosY(), 12);
		assertEquals(b.getSpeed(), 1);

		final BomberHuman c = new BomberHuman(false, 500, 3487, 5);
		assertFalse(c.isHuman());
		assertEquals(c.getPosX(), 500);
		assertEquals(c.getPosY(), 3487);
		assertEquals(c.getSpeed(), 5);
	}

	@Test
	public void testMovement() {
		final BomberHuman b = new BomberHuman(false, 10, 10, 1);
		b.moveVertically(-5);
		assertEquals(b.getPosX(), 10);
		assertEquals(b.getPosY(), 5);

		b.moveHorizontally(2);
		assertEquals(b.getPosX(), 12);
		assertEquals(b.getPosY(), 5);
	}

	@Test
	public void testSpeedBooster() {
		final BomberHuman b = new BomberHuman(false, 10, 10, 1);

		b.boostSpeed(2);
		assertEquals(b.getSpeed(), 3);

		b.moveVertically(5);
		assertEquals(b.getPosX(), 10);
		assertEquals(b.getPosY(), 25);

		b.moveHorizontally(-3);
		assertEquals(b.getPosX(), 1);
		assertEquals(b.getPosY(), 25);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionalConstructor() {
		new BomberHuman(true, -1, 10, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionalConstructor2() {
		new BomberHuman(true, 12, -10, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionalConstructor3() {
		new BomberHuman(true, 5000, 10, -3);
	}
}
