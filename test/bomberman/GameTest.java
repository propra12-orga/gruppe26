package bomberman;

import static org.junit.Assert.*;

import org.junit.Test;

import bomberman.game.character.BomberHuman;

public class GameTest {

	@Test
	public void testCanMoveFromZeroZero() {
		final Game g = new Game(3, 3);
		final BomberHuman b = new BomberHuman(true, 0, 0);

		assertEquals(b.getSpeed(), 4);

		assertFalse(g.canMoveThere('s', b));
		assertFalse(g.canMoveThere('a', b));
		assertTrue(g.canMoveThere('w', b));
		assertTrue(g.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveFromABitOffset() {
		final Game g = new Game(3, 3);
		final BomberHuman b = new BomberHuman(true, 4, 5);

		// do we really test this method?
		assertEquals(b.getPosX(), 4);
		assertEquals(b.getPosY(), 5);
		assertEquals(b.getSpeed(), 4);

		assertTrue(g.canMoveThere('s', b));
		assertTrue(g.canMoveThere('a', b));
		assertTrue(g.canMoveThere('w', b));
		assertTrue(g.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveFromABitOffset2() {
		final Game g = new Game(3, 3);
		final BomberHuman b = new BomberHuman(true, 3, 3);

		assertEquals(b.getPosX(), 3);
		assertEquals(b.getPosY(), 3);
		assertEquals(b.getSpeed(), 4);

		assertFalse(g.canMoveThere('s', b));
		assertFalse(g.canMoveThere('a', b));
		assertTrue(g.canMoveThere('w', b));
		assertTrue(g.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveInTopRightCorner() {
		final Game g = new Game(3, 3);
		final BomberHuman b = new BomberHuman(true, 149, 149);

		assertEquals(b.getPosX(), 149);
		assertEquals(b.getPosY(), 149);
		assertEquals(b.getSpeed(), 4);

		assertTrue(g.canMoveThere('s', b));
		assertTrue(g.canMoveThere('a', b));
		assertFalse(g.canMoveThere('w', b));
		assertFalse(g.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveAgainstCollision() {
		final Game g = new Game(3, 3);
		final BomberHuman b = new BomberHuman(true, 50, 49);

		assertEquals(b.getPosX(), 50);
		assertEquals(b.getPosY(), 49);
		assertEquals(b.getSpeed(), 4);

		assertTrue(g.canMoveThere('s', b));
		assertTrue(g.canMoveThere('a', b));
		assertFalse(g.canMoveThere('w', b));
		assertTrue(g.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveAgainstCollisionMidField() {
		final Game g = new Game(5, 5);
		final BomberHuman b = new BomberHuman(true, 70, 149);

		assertEquals(b.getPosX(), 70);
		assertEquals(b.getPosY(), 149);
		assertEquals(b.getSpeed(), 4);

		assertTrue(g.canMoveThere('s', b));
		assertTrue(g.canMoveThere('a', b));
		assertFalse(g.canMoveThere('w', b));
		assertTrue(g.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveAgainstCollisionMidFieldEdgeCase() {
		final Game g = new Game(5, 5);
		final BomberHuman b = new BomberHuman(true, 70, 150);

		assertEquals(b.getPosX(), 70);
		assertEquals(b.getPosY(), 150);
		assertEquals(b.getSpeed(), 4);

		assertTrue(g.canMoveThere('s', b));
		assertFalse(g.canMoveThere('a', b));
		assertFalse(g.canMoveThere('w', b));
		assertFalse(g.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveDownwardsAgainstCollisionMidField() {
		final Game g = new Game(5, 5);
		final BomberHuman b = new BomberHuman(true, 70, 100);

		assertEquals(b.getPosX(), 70);
		assertEquals(b.getPosY(), 100);
		assertEquals(b.getSpeed(), 4);

		assertFalse(g.canMoveThere('s', b));
		assertTrue(g.canMoveThere('a', b));
		assertTrue(g.canMoveThere('w', b));
		assertTrue(g.canMoveThere('d', b));
	}

	@Test
	public void testGetArrayPosNegativeValues() {
		final Game g = new Game(3, 3);
		assertEquals(g.getArrayPos(-1), -1);
		assertEquals(g.getArrayPos(-49), -1);
		assertEquals(g.getArrayPos(-5235346), -1);
	}

	@Test
	public void testGetArrayPosZero() {
		final Game g = new Game(3, 3);
		assertEquals(g.getArrayPos(0), 0);
	}

	@Test
	public void testGetArrayPos() {
		final Game g = new Game(3, 3);
		assertEquals(g.getArrayPos(49), 0);
		assertEquals(g.getArrayPos(50), 1);
		assertEquals(g.getArrayPos(51), 1);
		assertEquals(g.getArrayPos(99), 1);
		assertEquals(g.getArrayPos(100), 2);
		assertEquals(g.getArrayPos(149), 2);
		assertEquals(g.getArrayPos(150), 3);
		assertEquals(g.getArrayPos(499), 9);
		assertEquals(g.getArrayPos(500), 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMovementCausedByInvalidInput() {
		final Game g = new Game(5, 5);
		final BomberHuman b = new BomberHuman(true, 0, 0);

		g.canMoveThere('y', b);
	}

}
