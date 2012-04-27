package bomberman.game.character;

import static org.junit.Assert.*;

import org.junit.Test;

public class BomberHumanTest {

	@Test
	public void testConstructor() {
		final BomberHuman b = new BomberHuman(true, 10, 12);
		assertTrue(b.isHuman());
		assertEquals(b.getPosX(), 10);
		assertEquals(b.getPosY(), 12);
		assertEquals(b.getSpeed(), 4);

		final BomberHuman c = new BomberHuman(false, 500, 3487);
		assertFalse(c.isHuman());
		assertEquals(c.getPosX(), 500);
		assertEquals(c.getPosY(), 3487);
		assertEquals(b.getSpeed(), 4);
	}

	@Test
	public void testMovement() {
		final BomberHuman b = new BomberHuman(false, 10, 10);

		assertEquals(b.getSpeed(), 4);

		b.moveVertically(-1);
		assertEquals(b.getPosX(), 10);
		assertEquals(b.getPosY(), 6);

		b.moveHorizontally(1);
		assertEquals(b.getPosX(), 14);
		assertEquals(b.getPosY(), 6);
	}

	@Test
	public void testSpeedBooster() {
		final BomberHuman b = new BomberHuman(false, 10, 10);

		b.boostSpeed(2);
		assertEquals(b.getSpeed(), 6);

		b.moveVertically(5);
		assertEquals(b.getPosX(), 10);
		assertEquals(b.getPosY(), 16);

		b.moveHorizontally(-1);
		assertEquals(b.getPosX(), 4);
		assertEquals(b.getPosY(), 16);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionalConstructor() {
		new BomberHuman(true, -1, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionalConstructor2() {
		new BomberHuman(true, 12, -10);
	}

}
