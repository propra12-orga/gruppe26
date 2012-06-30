package bomberman.game;

import static org.junit.Assert.*;

import org.junit.Test;

import bomberman.game.character.BomberHuman;

public class ControlsTest {
	final Board board3 = new Board(new int[][] { { 0, 0, 0 }, { 0, 1, 0 },
			{ 0, 0, 0 } });
	final Board board5 = new Board(new int[][] { { 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0 } });

	@Test
	public void testCanMoveFromZeroZero() {
		final Controls c = new Controls(board3, 50);
		final BomberHuman b = new BomberHuman(true, 10, 5);

		assertEquals(b.getSpeed(), 4);

		assertFalse(c.canMoveThere('s', b));
		assertFalse(c.canMoveThere('a', b));
		assertTrue(c.canMoveThere('w', b));
		assertTrue(c.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveFromABitOffset() {
		final Controls c = new Controls(board3, 50);
		final BomberHuman b = new BomberHuman(true, 14, 10);

		// do we really test this method?
		assertEquals(b.getPosX(), 14);
		assertEquals(b.getPosY(), 10);
		assertEquals(b.getSpeed(), 4);

		assertTrue(c.canMoveThere('s', b));
		assertTrue(c.canMoveThere('a', b));
		assertTrue(c.canMoveThere('w', b));
		assertTrue(c.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveFromABitOffset2() {
		final Controls c = new Controls(board3, 50);
		final BomberHuman b = new BomberHuman(true, 13, 8);

		assertEquals(b.getPosX(), 13);
		assertEquals(b.getPosY(), 8);
		b.boostSpeed(3);
		assertEquals(b.getSpeed(), 7);

		assertFalse(c.canMoveThere('s', b));
		assertFalse(c.canMoveThere('a', b));
		assertTrue(c.canMoveThere('w', b));
		assertTrue(c.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveInTopRightCorner() {
		final Controls c = new Controls(board3, 50);
		final BomberHuman b = new BomberHuman(true, 139, 144);

		assertEquals(b.getPosX(), 139);
		assertEquals(b.getPosY(), 144);
		b.boostSpeed(3);
		assertEquals(b.getSpeed(), 7);

		assertTrue(c.canMoveThere('s', b));
		assertTrue(c.canMoveThere('a', b));
		assertFalse(c.canMoveThere('w', b));
		assertFalse(c.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveAgainstCollision() {
		final Controls c = new Controls(board3, 50);
		final BomberHuman b = new BomberHuman(true, 40, 44);

		assertEquals(b.getPosX(), 40);
		assertEquals(b.getPosY(), 44);
		b.boostSpeed(3);
		assertEquals(b.getSpeed(), 7);

		assertTrue(c.canMoveThere('s', b));
		assertTrue(c.canMoveThere('a', b));
		assertFalse(c.canMoveThere('w', b));
		assertTrue(c.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveAgainstCollisionMidField() {
		final Controls c = new Controls(board5, 50);
		final BomberHuman b = new BomberHuman(true, 70, 144);

		assertEquals(b.getPosX(), 70);
		assertEquals(b.getPosY(), 144);
		b.boostSpeed(3);
		assertEquals(b.getSpeed(), 7);

		assertTrue(c.canMoveThere('s', b));
		assertTrue(c.canMoveThere('a', b));
		assertFalse(c.canMoveThere('w', b));
		assertTrue(c.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveAgainstCollisionMidFieldEdgeCase() {
		final Controls c = new Controls(board5, 50);
		final BomberHuman b = new BomberHuman(true, 70, 145);

		assertEquals(b.getPosX(), 70);
		assertEquals(b.getPosY(), 145);
		b.boostSpeed(3);
		assertEquals(b.getSpeed(), 7);

		assertTrue(c.canMoveThere('s', b));
		assertFalse(c.canMoveThere('a', b));
		assertFalse(c.canMoveThere('w', b));
		assertFalse(c.canMoveThere('d', b));
	}

	@Test
	public void testCanMoveDownwardsAgainstCollisionMidField() {
		final Controls c = new Controls(board3, 50);
		final BomberHuman b = new BomberHuman(true, 70, 105);

		assertEquals(b.getPosX(), 70);
		assertEquals(b.getPosY(), 105);
		b.boostSpeed(3);
		assertEquals(b.getSpeed(), 7);

		assertFalse(c.canMoveThere('s', b));
		assertTrue(c.canMoveThere('a', b));
		assertTrue(c.canMoveThere('w', b));
		assertTrue(c.canMoveThere('d', b));
	}

	@Test
	public void testGetArrayPosNegativeValues() {
		final Controls c = new Controls(board3, 50);
		assertEquals(c.getArrayPos(-1), -1);
		assertEquals(c.getArrayPos(-49), -1);
		assertEquals(c.getArrayPos(-5235346), -1);
	}

	@Test
	public void testGetArrayPosZero() {
		final Controls c = new Controls(board3, 50);
		assertEquals(c.getArrayPos(0), 0);
	}

	@Test
	public void testGetArrayPos() {
		final Controls c = new Controls(board3, 50);
		assertEquals(c.getArrayPos(49), 0);
		assertEquals(c.getArrayPos(50), 1);
		assertEquals(c.getArrayPos(51), 1);
		assertEquals(c.getArrayPos(99), 1);
		assertEquals(c.getArrayPos(100), 2);
		assertEquals(c.getArrayPos(149), 2);
		assertEquals(c.getArrayPos(150), 3);
		assertEquals(c.getArrayPos(499), 9);
		assertEquals(c.getArrayPos(500), 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMovementCausedByInvalidInput() {
		final Controls c = new Controls(new Board(5, 5), 50);
		final BomberHuman b = new BomberHuman(true, 0, 0);

		c.canMoveThere('y', b);
	}
}
