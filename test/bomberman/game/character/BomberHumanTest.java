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
		assertEquals(b.getSpeed(), 1);

		final BomberHuman c = new BomberHuman(false, 500, 3487);
		assertFalse(c.isHuman());
		assertEquals(c.getPosX(), 500);
		assertEquals(c.getPosY(), 3487);
		assertEquals(b.getSpeed(), 1);
	}

	@Test
	public void testMovement() {
		final BomberHuman b = new BomberHuman(false, 10, 10);

		assertEquals(b.getSpeed(), 1);

		b.moveDown();
		assertEquals(b.getPosX(), 10);
		assertEquals(b.getPosY(), 9);

		b.moveRight();
		assertEquals(b.getPosX(), 11);
		assertEquals(b.getPosY(), 9);
	}

	@Test
	public void testSpeedBooster() {
		final BomberHuman b = new BomberHuman(false, 10, 10);

		b.boostSpeed(2);
		assertEquals(b.getSpeed(), 3);

		b.moveUp();
		assertEquals(b.getPosX(), 10);
		assertEquals(b.getPosY(), 13);

		b.moveLeft();
		assertEquals(b.getPosX(), 7);
		assertEquals(b.getPosY(), 13);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionalConstructor() {
		new BomberHuman(true, -1, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionalConstructor2() {
		new BomberHuman(true, 12, -10);
	}

	@Test
	public void testSpecifiedMovement() {
		final BomberHuman b = new BomberHuman(true, 0, 0);
		b.moveHorizontally(5);
		assertEquals(b.getPosX(), 5);

		b.moveHorizontally(10);
		assertEquals(b.getPosX(), 15);

		b.moveHorizontally(-5);
		assertEquals(b.getPosX(), 10);

		b.moveHorizontally(-15);
		assertEquals(b.getPosX(), -5);

		b.moveVertically(5);
		assertEquals(b.getPosY(), 5);

		b.moveVertically(10);
		assertEquals(b.getPosY(), 15);

		b.moveVertically(-5);
		assertEquals(b.getPosY(), 10);

		b.moveVertically(-15);
		assertEquals(b.getPosY(), -5);

	}

}
